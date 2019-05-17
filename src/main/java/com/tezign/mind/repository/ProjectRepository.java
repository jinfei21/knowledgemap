package com.tezign.mind.repository;

import com.tezign.mind.entity.node.ProjectEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends Neo4jRepository<ProjectEntity, Long> {


    @Query("MERGE(p:Project{ pid:{project}.pid,name:{project}.name }) return p")
    ProjectEntity addProject(@Param("project") ProjectEntity project);


    ProjectEntity findByPid(Long pId);

    Long deleteByPid(Long pId);

}
