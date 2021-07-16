package pl.apzumi.task.mappers;

import java.util.List;
import java.util.stream.Collectors;

public interface AbstractMapper<T, E> {

    public abstract T mapToDto(E e);

    public abstract E mapToEntity(T t);

    public default List<T> mapToDtos(final List<E> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public default List<E> mapToEntities(final List<T> list) {
        return list.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }
}