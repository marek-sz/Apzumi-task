package pl.apzumi.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.apzumi.task.dto.PostDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostFetchService {
    public static final String BASE_API_URL = "https://jsonplaceholder.typicode.com/posts";
    private RestTemplate restTemplate = new RestTemplate();

    public List<PostDto> getPosts() {
        ResponseEntity<PostDto[]> responseEntity = restTemplate.getForEntity(BASE_API_URL, PostDto[].class);
        PostDto[] postsArray = responseEntity.getBody();
        assert postsArray != null;
        List<PostDto> collect = Arrays.stream(postsArray)
                .collect(Collectors.toList());
        log.info(collect.toString());
        return collect;
    }

}
