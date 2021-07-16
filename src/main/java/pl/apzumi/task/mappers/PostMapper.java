package pl.apzumi.task.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.apzumi.task.domain.PostEntity;
import pl.apzumi.task.dto.PostDto;

@Component
public class PostMapper implements AbstractMapper<PostDto, PostEntity> {
    @Override
    public PostDto mapToDto(PostEntity postEntity) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostEntity mapToEntity(PostDto postDto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postDto, PostEntity.class);
    }
}