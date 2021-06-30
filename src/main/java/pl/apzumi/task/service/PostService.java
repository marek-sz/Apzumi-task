package pl.apzumi.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.apzumi.task.domain.PostEntity;

@Service
@Slf4j
public class PostService {
    private RestTemplate restTemplate = new RestTemplate();

    public PostEntity getPosts() {
        String allPosts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
        log.info(allPosts);
        return null;
    }
}
