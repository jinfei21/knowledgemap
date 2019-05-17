package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.portfolio.CreativeTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface CreativeRepository extends Neo4jRepository<CreativeTag, Long> {

    @Query("MERGE(c:Creative{ creativeType:{creative}.creativeType }) return c")
    CreativeTag addCreative(@Param("creative") CreativeTag creative);

}
