package pl.apzumi.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.apzumi.task.dto.PostDto;
import pl.apzumi.task.service.PostFetchService;
import pl.apzumi.task.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostFetchService postFetchService;
    private final PostService postService;

    @GetMapping("/fetch")
    public void fetchAllPosts() {
        postFetchService.fetchData();
    }

    @GetMapping("/posts")
    public List<PostDto> getFilteredPosts(@RequestParam String name) {
        return postService.getFilteredPostsByTitle(name);
    }

}
