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

    @BeforeAll
    public void init() {
        fetchedFromPublicApi = new ArrayList<>();
        PostEntity post1 = PostEntity.builder()
                .id(1L)
                .userId(1L)
                .title("title1")
                .body("body1")
                .modifiedByUser(false)
                .build();
        PostEntity post2 = PostEntity.builder()
                .id(2L)
                .userId(1L)
                .title("title2")
                .body("body2")
                .modifiedByUser(false)
                .build();
        PostEntity post3 = PostEntity.builder()
                .id(3L)
                .userId(1L)
                .title("title3")
                .body("body3")
                .modifiedByUser(false)
                .build();
        fetchedFromPublicApi.add(post1);
        fetchedFromPublicApi.add(post2);
        fetchedFromPublicApi.add(post3);
    }

    @Test
    public void shouldUpdatePostsExceptModifiedByUser() {
        List<PostEntity> dbRecords = new ArrayList<>();
        PostEntity post1 = PostEntity.builder()
                .id(1L)
                .userId(1L)
                .title("title1")
                .body("body1")
                .modifiedByUser(false)
                .build();
        PostEntity post2 = PostEntity.builder()
                .id(2L)
                .userId(1L)
                .title("updated title")
                .body("updated body")
                .modifiedByUser(true)
                .build();
        dbRecords.add(post1);
        dbRecords.add(post2);
        postRepository.saveAll(dbRecords);

        postRepository.updateAllExceptEditedAndDeletedByUser(fetchedFromPublicApi);

        assertEquals(2, postRepository.count());

        postRepository.findAll().forEach(System.out::println);
    }
}