package pl.apzumi.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.apzumi.task.service.PostFetchService;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostFetchService postFetchService;

    @GetMapping("/fetch")
    public void fetchAllPosts() {
        postFetchService.getPosts();
    }

    @GetMapping("/posts")
    public void getAllPosts() {
        postFetchService.getPosts();
    }

}
