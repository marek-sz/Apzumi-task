package pl.apzumi.task.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.apzumi.task.domain.PostEntity;
import pl.apzumi.task.dto.PostDto;
import pl.apzumi.task.mappers.PostMapper;
import pl.apzumi.task.repository.PostRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<PostDto> getFilteredPostsByTitle(String title) {
        List<PostEntity> filteredPosts = postRepository.getFilteredPosts(title);
        return postMapper.mapToDtos(filteredPosts);
    }
}
