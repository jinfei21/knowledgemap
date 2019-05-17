package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.PortfolioEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface PortfolioRepository extends Neo4jRepository<PortfolioEntity, Long> {

    @Query("MERGE(p:Portfolio{ eid:{portfolio}.eid,name:{portfolio}.name }) return p")
    PortfolioEntity addPortfolio(@Param("portfolio") PortfolioEntity portfolio);

    PortfolioEntity findByEid(Long eid);

    Long deleteByEid(Long eid);

}
