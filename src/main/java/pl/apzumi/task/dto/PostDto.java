package pl.apzumi.task.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {

    private Long id;
    private Long userId;
    private String title;
    private String body;
    private boolean modifiedByUser;

}