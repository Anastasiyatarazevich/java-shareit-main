package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private Boolean available;

    private Long requestId;

    private List<CommentDto> comments;
}
