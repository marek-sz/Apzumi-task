package pl.apzumi.task.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.apzumi.task.domain.PostEntity;
import pl.apzumi.task.dto.PostDto;
import pl.apzumi.task.mappers.PostMapper;
import pl.apzumi.task.repository.PostRepository;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<PostDto> getFilteredPostsByTitle(String title) {
        if (title == null || title.isBlank()) {
            return postMapper.mapToDtos(postRepository.findAll());
        }
        return postMapper.mapToDtos(postRepository.getPosts(title));
    }

    public void deletePost(Long id) {
        checkIfPostExists(id);
        postRepository.deleteById(id);
    }

    public void updatePost(Long id, String title, String body) {
        PostEntity postEntity = checkIfPostExists(id);

        if (title != null &&
                title.isBlank() &&
                !Objects.equals(postEntity.getTitle(), title)) {
            postEntity.setTitle(title);
        }
        if (body != null &&
                body.isBlank() &&
                !Objects.equals(postEntity.getBody(), body)) {
            postEntity.setBody(body);
        }
        postEntity.setModifiedByUser(true);
    }

    private PostEntity checkIfPostExists(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Resource with id " + id + " not found"));
    }

    public void updatePostWithDto(Long id, PostDto postDto) {
        PostEntity postEntity = checkIfPostExists(id);
        postEntity.setModifiedByUser(true);
        postEntity.setTitle(postDto.getTitle());
        postEntity.setBody(postDto.getBody());

    }
}