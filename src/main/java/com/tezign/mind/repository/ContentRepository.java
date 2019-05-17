package com.tezign.mind.repository;

import com.tezign.mind.entity.node.portfolio.ContentTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends Neo4jRepository<ContentTag, Long> {

    @Query("MERGE(c:Content{ content:{content}.content }) return c")
    ContentTag addContent(@Param("content") ContentTag content);

}
