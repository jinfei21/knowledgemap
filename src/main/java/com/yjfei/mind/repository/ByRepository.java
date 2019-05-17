package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.project.ByTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ByRepository extends Neo4jRepository<ByTag, Long> {

    @Query("MERGE(b:By{ medium:{by}.medium }) return b")
    ByTag addBy(@Param("by") ByTag by);

}
