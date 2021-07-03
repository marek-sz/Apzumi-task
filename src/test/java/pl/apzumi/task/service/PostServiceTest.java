package pl.apzumi.task.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pl.apzumi.task.domain.PostEntity;
import pl.apzumi.task.mappers.PostMapper;
import pl.apzumi.task.repository.PostRepository;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostMapper postMapper;

    private AutoCloseable autoCloseable;

    private PostService postService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        postService = new PostService(postRepository, postMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldCallRepositoryFindAllWhenPassingNullAsAnArgument() {
        //when
        postService.getFilteredPostsByTitle(null);
        //then
        Mockito.verify(postRepository).findAll();
    }

    @Test
    void shouldCallRepositoryGetPostsWhenPassingAnArgument() {
        //when
        postService.getFilteredPostsByTitle("null");
        //then
        Mockito.verify(postRepository).getPosts("null");
    }

    @Test
    void shouldDeletePostWhenDoesExists() {
        // given
        PostEntity post = PostEntity.builder()
                .id(1L)
                .userId(1L)
                .title("title1")
                .body("body1")
                .modifiedByUser(false)
                .build();
        Long id = 1L;
        Mockito.when(postRepository.findById(id)).thenReturn(ofNullable(post));

        // when
        postService.deletePost(id);

        // then
        Mockito.verify(postRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenTryingDeleteNonExistingPost() {
        assertThrows(ResponseStatusException.class, () -> postService.deletePost(999L));
    }

    @Test
    @Disabled
    void updateExistingPost() {
    }

}