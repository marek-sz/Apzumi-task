package pl.apzumi.task.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import pl.apzumi.task.domain.PostEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class PostRepositoryCustomImplTest {
//czy updateują się poprawne pola
    //stworzyć schemat db z przykładowymi wartościami

    @Autowired
    private PostRepository postRepository;

    @Test
    void shouldProperlyUpdatePosts() {
        //given
        List<PostEntity> list = new ArrayList<>();
        PostEntity updatedPost = PostEntity.builder()
                .id(1L)
                .userId(1L)
                .title("updated title")
                .body("updated body")
                .build();
        list.add(updatedPost);

        postRepository.updateAllExceptEditedAndDeletedByUser(list);
    }
}