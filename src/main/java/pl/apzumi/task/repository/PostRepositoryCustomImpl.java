package pl.apzumi.task.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import pl.apzumi.task.domain.PostEntity;
import pl.apzumi.task.domain.QPostEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateAllExceptEditedAndDeletedByUser(List<PostEntity> posts) {
        QPostEntity qPostEntity = QPostEntity.postEntity;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        posts
                .forEach(postEntity ->
                        jpaQueryFactory
                                .update(qPostEntity)
                                .where(qPostEntity.id.eq(postEntity.getId())
                                        .and(qPostEntity.modifiedByUser.eq(false)))
                                .set(qPostEntity.title, postEntity.getTitle())
                                .set(qPostEntity.body, postEntity.getBody())
                                .execute()
                );
    }
}