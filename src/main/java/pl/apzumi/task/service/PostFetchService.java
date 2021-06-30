package pl.apzumi.task.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.apzumi.task.dto.PostDto;
import pl.apzumi.task.repository.PostRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PostFetchService {
    public static final String BASE_API_URL = "https://jsonplaceholder.typicode.com/posts";
    private final PostRepository postRepository;

    @Scheduled(cron = "* * * * * ?")
    public void fetchData() {
//        postRepository.saveAllExceptEditedAndDeletedByUser();

        log.info("Not implemented");
    }

    public List<PostDto> getPosts() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PostDto[]> responseEntity = restTemplate.getForEntity(BASE_API_URL, PostDto[].class);
        PostDto[] postsArray = responseEntity.getBody();
        assert postsArray != null;
        List<PostDto> postsList = Arrays.stream(postsArray)
                .collect(Collectors.toList());
        log.info(postsList.toString());
        return postsList;
    }

}