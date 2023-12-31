package ru.practicum.shareit.request;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.user.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemRequestMapperTest {

    @Test
    void toRequestDto() {
        ItemRequest itemRequest = ItemRequest.builder().id(1L).description("Test item request").requestor(User.builder().build()).created(Timestamp.valueOf(LocalDateTime.now())).build();
        ItemRequestDto itemRequestDto = ItemRequestMapper.toRequestDto(itemRequest);
        assertEquals(itemRequest.getId(), itemRequestDto.getId());
        assertEquals(itemRequest.getDescription(), itemRequestDto.getDescription());
        assertEquals(itemRequest.getRequestor().getId(), itemRequestDto.getRequestor().getId());
        assertEquals(itemRequest.getRequestor().getName(), itemRequestDto.getRequestor().getName());
        assertEquals(itemRequest.getRequestor().getEmail(), itemRequestDto.getRequestor().getEmail());
        assertEquals(itemRequest.getCreated(), itemRequestDto.getCreated());
    }

    @Test
    void toRequest() {
        ItemRequestDto itemRequestDto = ItemRequestDto.builder().id(1L).description("Test item request").requestor(ItemRequestDto.User.builder().build()).created(Timestamp.valueOf(LocalDateTime.now())).build();
        ItemRequest itemRequest = ItemRequestMapper.toRequest(itemRequestDto);
        assertEquals(itemRequestDto.getId(), itemRequest.getId());
        assertEquals(itemRequestDto.getDescription(), itemRequest.getDescription());
        assertEquals(itemRequestDto.getRequestor().getId(), itemRequest.getRequestor().getId());
        assertEquals(itemRequestDto.getRequestor().getName(), itemRequest.getRequestor().getName());
        assertEquals(itemRequestDto.getRequestor().getEmail(), itemRequest.getRequestor().getEmail());
        assertEquals(itemRequestDto.getCreated(), itemRequest.getCreated());
    }

    @Test
    void toRequestWithItems() {
        ItemRequest itemRequest = ItemRequest.builder().id(1L).description("Test item request").created(Timestamp.valueOf(LocalDateTime.now())).build();
        ItemRequestWithItems requestWithItems = ItemRequestMapper.toRequestWithItems(itemRequest);
        assertEquals(itemRequest.getId(), requestWithItems.getId());
        assertEquals(itemRequest.getCreated(), requestWithItems.getCreated());
        assertEquals(itemRequest.getDescription(), requestWithItems.getDescription());
    }
}
