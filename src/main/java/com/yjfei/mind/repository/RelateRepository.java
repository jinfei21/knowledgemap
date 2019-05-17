package com.yjfei.mind.repository;

import com.yjfei.mind.entity.ref.Relate;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface RelateRepository extends Neo4jRepository<Relate, Long> {




    @Query("MERGE({id:{relate}.startNode.id})-[r:REF{ name:{relate}.name}]->({id:{relate}.endNode.id}) return r")
    Relate addRelate(@Param("relate") Relate relate);


    @Query("MERGE({id:{startId}})-[r:REF]->({ id:{endId}}) return r")
    Relate findRelate(@Param("startId") Long startId, @Param("endId") Long endId);

}
