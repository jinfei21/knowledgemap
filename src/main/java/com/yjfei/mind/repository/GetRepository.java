package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.project.GetTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface GetRepository extends Neo4jRepository<GetTag, Long> {

    @Query("MERGE(g:Get{ portraint:{get}.portraint }) return g")
    GetTag addGet(@Param("get") GetTag get);

}
