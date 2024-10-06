package com.riwi.RiwiTech.domain.ports.service;

import com.riwi.RiwiTech.application.dtos.requests.TaskRequest;
import com.riwi.RiwiTech.application.dtos.responses.TaskResponse;
import com.riwi.RiwiTech.domain.entities.Task;

public interface ITaskService extends
        Create<TaskRequest,TaskRequest>,
        Update<TaskRequest, Task,Long>,
        Delete<Long>,
        ReadAll<TaskResponse>,
        ReadById<TaskResponse, Long>{
}
