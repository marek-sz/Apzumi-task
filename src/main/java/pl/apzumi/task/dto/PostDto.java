package pl.apzumi.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {

    private Long id;

    @JsonIgnore
    private Long userId;
    private String title;
    private String body;
    private boolean modifiedByUser;

}