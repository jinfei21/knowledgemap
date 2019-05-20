package com.yjfei.mind.repository;

import com.yjfei.mind.entity.ref.Relate;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface RelateRepository extends Neo4jRepository<Relate, Long> {



    @Query("MATCH(:Supplier{sid:{startId}})-[r:REF]->(:Portfolio{eid:{endId}}) return r")
    Relate findSupplierToPortfolioRelate(@Param("startId") Long startId, @Param("endId") Long endId);


    @Query("MATCH(:Supplier{sid:{startId}})-[r:REF]->(:Project{pid:{endId}}) return r")
    Relate findSupplierToProjectRelate(@Param("startId") Long startId, @Param("endId") Long endId);


    @Query("MATCH(:Portfolio{eid:{startId}})-[r:REF]->(:Project{pid:{endId}}) return r")
    Relate findPortfolioToProjectRelate(@Param("startId") Long startId, @Param("endId") Long endId);

}
