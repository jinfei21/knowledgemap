package com.tezign.mind.repository;

import com.tezign.mind.entity.BaseNode;
import com.tezign.mind.entity.ref.Relate;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface RelateRepository extends Neo4jRepository<Relate, Long> {




    @Query("MERGE({id:{relate}.startNode.id})-[r:Relate{ name:{relate}.name}]->({ id:{relate}.endNode.id}) return r")
    Relate addRelate(@Param("relate") Relate relate);


    @Query("MERGE({id:{relate}.startNode.id})-[r:Relate]->({ id:{relate}.endNode.id}) return r")
    Relate findRelate(@Param("startNode")BaseNode startNode, @Param("endNode") BaseNode endNode);

}
