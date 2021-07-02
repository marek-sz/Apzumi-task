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
import pl.apzumi.task.mappers.PostMapper;
import pl.apzumi.task.repository.PostRepository;

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
    @Disabled
    void deletePost() {
    }

    @Test
    @Disabled
    void updatePost() {
    }
}