package pl.apzumi.task.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostUpdateDto {
    // TODO: 2021-07-12 extract fields to properties?
    @NotEmpty(message = "Field cannot be empty")
    private String title;
    @NotEmpty(message = "Field cannot be empty")
    private String body;
}