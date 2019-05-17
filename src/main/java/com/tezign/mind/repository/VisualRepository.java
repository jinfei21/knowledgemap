package com.tezign.mind.repository;

import com.tezign.mind.entity.node.portfolio.VisualTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface VisualRepository extends Neo4jRepository<VisualTag, Long> {

    @Query("MERGE(v:Visual{ visualStyle:{visual}.visualStyle }) return v")
    VisualTag addVisual(@Param("visual") VisualTag visual);

}
