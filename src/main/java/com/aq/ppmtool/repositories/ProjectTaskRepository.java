package com.aq.ppmtool.repositories;

import com.aq.ppmtool.domain.Backlog;
import com.aq.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
