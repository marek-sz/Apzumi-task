package pl.apzumi.task.controller;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.apzumi.task.dto.PostDto;
import pl.apzumi.task.service.PostService;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @MockBean
    private PostController postController;
    @MockBean
    private PostService postService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllPostsWhenGetMappingIsInvoked() throws Exception {
        PostDto post = PostDto.builder()
                .id(1L)
                .userId(1L)
                .title("sunt aut facere")
                .body("quia et suscipit")
                .modifiedByUser(false)
                .build();
        when(postService.getFilteredPostsByTitle(null))
                .thenReturn(Lists.newArrayList(post));

        mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(1)));
    }

}