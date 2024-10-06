package com.riwi.RiwiTech.application.services.impl;


import com.riwi.RiwiTech.application.dtos.mappers.TaskMapper;
import com.riwi.RiwiTech.application.dtos.requests.TaskRequest;
import com.riwi.RiwiTech.application.dtos.responses.TaskResponse;
import com.riwi.RiwiTech.application.services.generic.*;
import com.riwi.RiwiTech.infrastructure.persistence.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements Create<TaskRequest, TaskRequest>, Update<TaskRequest, Task, Long>, Delete<Long>, ReadAll<TaskResponse>, ReadById<TaskResponse, Long> {

    @Autowired
    TaskRepository taskRepository;


    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)){
            throw  new EntityNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponse> readAll() {
        List<Task> task = taskRepository.findAll();
        return task.stream()
                .map(TaskMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskResponse> readById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Task no found"));

        TaskResponse response = TaskMapper.INSTANCE.toResponseDto(task);

        return Optional.of(response);
    }

    @Override
    public TaskRequest create(TaskRequest taskRequest) {
        var taskEntity = TaskMapper.INSTANCE.toEntity(taskRequest);

        taskRepository.save(taskEntity);

        return taskRequest;
    }


    @Override
    public Task update(TaskRequest taskRequest, Long id) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        // Verificamos si la tarea existe
        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            // Usar el mapper para actualizar la tarea
            Task updatedTask = TaskMapper.INSTANCE.toEntity(taskRequest);

            existingTask.setDescription(updatedTask.getDescription());

            return taskRepository.save(existingTask);
        }
        throw new EntityNotFoundException("Task not found");
    }
}
