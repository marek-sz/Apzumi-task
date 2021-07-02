package pl.apzumi.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.apzumi.task.dto.PostDto;
import pl.apzumi.task.service.PostFetchService;
import pl.apzumi.task.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostFetchService postFetchService;
    private final PostService postService;

    @PutMapping("/fetch")
    public void fetchAllPosts() {
        postFetchService.fetchData();
    }

    @GetMapping("/posts")
    public List<PostDto> getFilteredPosts(@RequestParam(required = false) String title) {
        return postService.getFilteredPostsByTitle(title);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PutMapping("/posts/{id}")
    public void updatePost(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String body) {
        postService.updatePost(id, title, body);
    }
}