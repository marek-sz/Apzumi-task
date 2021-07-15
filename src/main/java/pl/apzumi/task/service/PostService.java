package pl.apzumi.task.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.apzumi.task.domain.PostEntity;
import pl.apzumi.task.domain.QPostEntity;
import pl.apzumi.task.dto.PostDto;
import pl.apzumi.task.dto.PostUpdateDto;
import pl.apzumi.task.mappers.PostMapper;
import pl.apzumi.task.repository.PostRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
        BooleanExpression booleanExpression = QPostEntity.postEntity.title.containsIgnoreCase(title);
        List<PostEntity> list = new ArrayList<>();
        postRepository.findAll(booleanExpression).forEach(list::add);
        return postMapper.mapToDtos(list);
    }

    public void deletePost(Long id) {
        checkIfPostExists(id);
        postRepository.deleteById(id);
    }

    public void updatePostWithDto(Long id, PostUpdateDto postUpdateDto) {
        PostEntity postEntity = checkIfPostExists(id);
        postEntity.setTitle(postUpdateDto.getTitle());
        postEntity.setBody(postUpdateDto.getBody());
        postEntity.setModifiedByUser(true);
    }

    private PostEntity checkIfPostExists(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Resource with id " + id + " not found"));
    }

}