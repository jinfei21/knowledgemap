package com.tezign.mind.repository;

import com.tezign.mind.entity.node.project.ToTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ToRepository extends Neo4jRepository<ToTag, Long> {

    @Query("MERGE(t:To{ purpose:{to}.purpose }) return t")
    ToTag addTo(@Param("to") ToTag to);

}
