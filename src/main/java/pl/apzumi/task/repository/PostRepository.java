package pl.apzumi.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.apzumi.task.domain.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long>, PostRepositoryCustom {
}