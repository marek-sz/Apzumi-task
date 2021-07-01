package pl.apzumi.task.repository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCriteria {
    private String title;
}
