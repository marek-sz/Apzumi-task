package pl.apzumi.task.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.apzumi.task.domain.PostEntity;
import pl.apzumi.task.domain.QPostEntity;
import pl.apzumi.task.mappers.PostMapper;
import pl.apzumi.task.repository.PostRepository;

import javax.persistence.EntityNotFoundException;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostMapper postMapper;
    @Mock
    private PostFetchService postFetchService;

    private AutoCloseable autoCloseable;

    private PostService postService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        postService = new PostService(postFetchService, postRepository, postMapper);
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
        verify(postRepository).findAll();
    }

    @Test
    void shouldCallRepositoryGetPostsWhenPassingAnArgument() {
        //when
        postService.getFilteredPostsByTitle("null");
        //then
        BooleanExpression booleanExpression = QPostEntity.postEntity.title.containsIgnoreCase("null");
        verify(postRepository).findAll(booleanExpression);

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
        when(postRepository.findById(id)).thenReturn(ofNullable(post));

        // when
        postService.deletePost(id);

        // then
        verify(postRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenTryingDeleteNonExistingPost() {
        assertThrows(EntityNotFoundException.class, () -> postService.deletePost(999L));
    }

}