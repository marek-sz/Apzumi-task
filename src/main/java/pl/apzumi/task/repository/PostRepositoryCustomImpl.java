package pl.apzumi.task.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import pl.apzumi.task.domain.PostEntity;
import pl.apzumi.task.domain.QPostEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    // TODO: 2021-07-01 Criteria api for update??
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

    @Override
    public List<PostEntity> getFilteredPosts(String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PostEntity> cq = cb.createQuery(PostEntity.class);
        Root<PostEntity> postEntityRoot = cq.from(PostEntity.class);
        cq.select(postEntityRoot)
                .where(cb.like(postEntityRoot.get("title"), title));
        return entityManager.createQuery(cq).getResultList();
    }
}