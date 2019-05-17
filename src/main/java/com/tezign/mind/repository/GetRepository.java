package com.tezign.mind.repository;

import com.tezign.mind.entity.node.project.GetTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface GetRepository extends Neo4jRepository<GetTag, Long> {

    @Query("MERGE(g:Get{ portraint:{get}.portraint }) return g")
    GetTag addGet(@Param("get") GetTag get);

}
