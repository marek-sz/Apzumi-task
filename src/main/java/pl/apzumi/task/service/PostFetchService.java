package pl.apzumi.task.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pl.apzumi.task.domain.PostEntity;
import pl.apzumi.task.dto.PostDto;
import pl.apzumi.task.mappers.PostMapper;
import pl.apzumi.task.repository.PostRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PostFetchService extends AbstractPostService {
    public static final String BASE_API_URL = "https://jsonplaceholder.typicode.com/posts";
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Scheduled(cron = "* * * * * ?")
    @Transactional
    public void fetchData() {
        List<PostEntity> posts = postMapper.mapToEntities(getPosts());
        if (postRepository.count() == 0) {
            postRepository.saveAll(posts);
            log.info("Data inserted");
        } else {
            postRepository.updateAllExceptEditedAndDeletedByUser(posts);
            log.info("Data updated");
        }
    }

    private List<PostDto> getPosts() {
        // TODO: 2021-06-30 add try with resources
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PostDto[]> responseEntity = restTemplate.getForEntity(BASE_API_URL, PostDto[].class);
        PostDto[] postsArray = responseEntity.getBody();
        assert postsArray != null;
        return Arrays.stream(postsArray)
                .collect(Collectors.toList());
    }
}