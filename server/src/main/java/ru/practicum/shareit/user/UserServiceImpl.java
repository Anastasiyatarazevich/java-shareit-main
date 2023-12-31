package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.item.ItemRepository;

import javax.transaction.Transactional;
import java.util.List;

import static ru.practicum.shareit.user.UserMapper.toUser;
import static ru.practicum.shareit.user.UserMapper.toUserDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<User> getUsers() {
        return List.copyOf(userRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long userId) {
        return toUserDto(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Transactional
    @Override
    public UserDto addUser(UserDto user) {
        return toUserDto(userRepository.save(toUser(user)));
    }

    @Override
    public UserDto updateUser(Long userId, UserDto user) {
        User updUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        if (user.getName() != null) {
            updUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            updUser.setEmail(user.getEmail());
        }
        return toUserDto(userRepository.save(updUser));
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        int page = 1 / 20;
        Pageable pageable = PageRequest.of(page, 20);
        if (userRepository.existsById(userId)) {
            itemRepository.deleteAll(itemRepository.findAllByOwnerIdOrderById(userId, pageable));
            userRepository.deleteById(userId);
        }
    }


}