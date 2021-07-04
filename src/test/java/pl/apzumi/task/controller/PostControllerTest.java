package pl.apzumi.task.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.apzumi.task.service.PostService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostController postController;

    @MockBean
    private PostService postService;

    @Test
    void shouldCallGetMethod() throws Exception {
        String url = "/posts";
        mockMvc.perform(
                get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldCallGetMethodWithParameter() throws Exception {
        String url = "/posts";
        mockMvc.perform(
                get(url).requestAttr("title", anyString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldDeletePost() throws Exception {
        String url = "/posts/1";
        mockMvc.perform(
                delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdatePost() throws Exception {
        String url = "/posts/2";
        mockMvc.perform(
                put(url)
                        .param("title", "title")
                        .param("body", "body")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}