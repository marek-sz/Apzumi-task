package pl.apzumi.task.repository;

import pl.apzumi.task.domain.PostEntity;

import java.util.List;

public interface PostRepositoryCustom {

    void updateAllExceptEditedAndDeletedByUser(List<PostEntity> posts);

    List<PostEntity> getPosts(String title);
}