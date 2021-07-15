package pl.apzumi.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.apzumi.task.dto.PostDto;
import pl.apzumi.task.dto.PostUpdateDto;
import pl.apzumi.task.service.PostService;

import java.net.ConnectException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PutMapping("/fetch")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fetchAllPosts() throws ConnectException {
        postService.fetchData();
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getFilteredPosts(@RequestParam(required = false) String title) {
        return postService.getFilteredPostsByTitle(title);
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PatchMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePostWithDto(
            @PathVariable Long id,
            @Validated @RequestBody PostUpdateDto postUpdateDto) {
        postService.updatePostWithDto(id, postUpdateDto);
    }
}