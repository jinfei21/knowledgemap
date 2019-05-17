package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.supplier.ScaleTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ScaleRepository extends Neo4jRepository<ScaleTag, Long> {

    @Query("MERGE(s:Scale{ scale:{scale}.scale }) return s")
    ScaleTag addScale(@Param("scale") ScaleTag scale);

}
