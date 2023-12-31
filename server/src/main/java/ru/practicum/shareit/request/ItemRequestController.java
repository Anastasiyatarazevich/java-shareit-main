package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {

    private final ItemRequestsService requestService;

    @PostMapping()
    public ItemRequestDto addRequest(@RequestHeader("X-Sharer-User-Id") Long id,
                                     @RequestBody ItemRequestDto itemRequestDto) {
        return requestService.addRequest(id, itemRequestDto);
    }

    @Validated
    @GetMapping()
    public List<ItemRequestWithItems> getOwnRequests(@RequestHeader("X-Sharer-User-Id") Long id,
                                                     @RequestParam(defaultValue = "0") int from,
                                                     @RequestParam(defaultValue = "10") int size) throws ValidationException {

        return requestService.getOwnRequests(id, from, size);
    }

    @Validated
    @GetMapping("/all")
    public List<ItemRequestWithItems> getAllRequests(@RequestHeader("X-Sharer-User-Id") Long id,
                                                     @RequestParam(defaultValue = "0") int from,
                                                     @RequestParam(defaultValue = "10") int size) throws ValidationException {
        return requestService.getAll(id, from, size);
    }

    @GetMapping("/{requestId}")
    public ItemRequestWithItems getRequestById(@RequestHeader("X-Sharer-User-Id") Long id,
                                               @PathVariable Long requestId) {
        return requestService.getRequestById(id, requestId);
    }

}
