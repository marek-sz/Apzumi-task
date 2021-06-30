package pl.apzumi.task.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PostEntity {

    @Id
    private Long id;
    private Long userId;
    private String title;
    private String body;

}