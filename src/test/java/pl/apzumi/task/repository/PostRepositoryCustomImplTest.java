package pl.apzumi.task.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import pl.apzumi.task.domain.PostEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
@TestPropertySource(
        locations = "classpath:test.properties")
public class PostRepositoryCustomImplTest {

    @Autowired
    private PostRepository postRepository;
    private List<PostEntity> fetchedFromPublicApi;
    private List<PostEntity> dbRecords;
    private PostEntity postFromApi1;
    private PostEntity postFromApi2;
    private PostEntity postFromApi3;
    private PostEntity postFromDb1;
    private PostEntity postFromDb2;

    @BeforeAll
    public void init() {
        fetchedFromPublicApi = new ArrayList<>();
        postFromApi1 = PostEntity.builder()
                .id(1L)
                .userId(1L)
                .title("new title")
                .body("new body")
                .modifiedByUser(false)
                .build();
        postFromApi2 = PostEntity.builder()
                .id(2L)
                .userId(1L)
                .title("title2")
                .body("body2")
                .modifiedByUser(false)
                .build();
        postFromApi3 = PostEntity.builder()
                .id(3L)
                .userId(1L)
                .title("title3")
                .body("body3")
                .modifiedByUser(false)
                .build();
        fetchedFromPublicApi.add(postFromApi1);
        fetchedFromPublicApi.add(postFromApi2);
        fetchedFromPublicApi.add(postFromApi3);

        dbRecords = new ArrayList<>();
        postFromDb1 = PostEntity.builder()
                .id(1L)
                .userId(1L)
                .title("title1")
                .body("body1")
                .modifiedByUser(false)
                .build();
        postFromDb2 = PostEntity.builder()
                .id(2L)
                .userId(1L)
                .title("updated title")
                .body("updated body")
                .modifiedByUser(true)
                .build();
        dbRecords.add(postFromDb1);
        dbRecords.add(postFromDb2);
        postRepository.saveAll(dbRecords);
    }

    @Test
    public void shouldUpdatePostsExceptModifiedByUser() {
        postRepository.updateAllExceptEditedAndDeletedByUser(fetchedFromPublicApi);
        assertEquals(2, postRepository.count());
        assertTrue(postRepository.findAll().contains(postFromApi1));
        assertTrue(postRepository.findAll().contains(postFromDb2));
    }

    @Test
    void shouldReturnOnePostWhenGetPostsIsCalledWithParameter() {
        List<PostEntity> posts = postRepository.getPosts("title1");
        assertEquals(1, posts.size());
        assertTrue(posts.contains(postFromDb1));
    }

}