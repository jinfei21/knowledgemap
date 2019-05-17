package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.supplier.StyleTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface StyleRepository extends Neo4jRepository<StyleTag, Long> {

    @Query("MERGE(s:Style{ style:{style}.style }) return s")
    StyleTag addStyle(@Param("style") StyleTag style);

}
