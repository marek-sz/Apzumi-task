package pl.apzumi.task.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pl.apzumi.task.dto.PostDto;

import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class PostFetchService {
    public static final String BASE_API_URL = "https://jsonplaceholder.typicode.com/posts";

    public List<PostDto> getPosts() throws ConnectException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PostDto[]> responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(BASE_API_URL, PostDto[].class);
        } catch (Exception e) {
            throw new ConnectException("Unable to establish connection with " + BASE_API_URL);
        }
        PostDto[] postsArray = responseEntity.getBody();
        assert postsArray != null;
        return Arrays.stream(postsArray)
                .collect(Collectors.toList());
    }
}