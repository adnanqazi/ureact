package com.aq.ppmtool.services;

import com.aq.ppmtool.domain.Backlog;
import com.aq.ppmtool.domain.ProjectTask;
import com.aq.ppmtool.repositories.BacklogRepository;
import com.aq.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        //PTs to be added to a specific project, i.e. not null project therefore backlog exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        projectTask.setBacklog(backlog);
        //set the backlog to the project task
        Integer backlogSequence = backlog.getPTSequence();
        backlogSequence++;
        projectTask.setProjectSequence(projectIdentifier +"-" + backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //project sequence should begin from 0 and strictly grow one after the other, removing deleted ones
        //init in sequence to 0 ensures this (Bl.PTSequence)
        //update the BL sequence

        //initial priority when priority null
        if(projectTask.getPriority() == null || projectTask.getPriority() == 0){
            projectTask.setPriority(3);
        }
        //initial status when status is null
        if(projectTask.getStatus() == null || projectTask.getStatus() == ""){
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);

    }
}
