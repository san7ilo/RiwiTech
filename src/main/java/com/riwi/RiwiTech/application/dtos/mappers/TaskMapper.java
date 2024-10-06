package com.riwi.RiwiTech.application.dtos.mappers;

import com.riwi.RiwiTech.application.dtos.requests.TaskRequest;
import com.riwi.RiwiTech.application.dtos.responses.TaskResponse;
import com.riwi.RiwiTech.domain.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task toEntity(TaskRequest taskRequest);
    TaskResponse toResponseDto(Task task);
}
