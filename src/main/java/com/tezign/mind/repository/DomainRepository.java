package com.tezign.mind.repository;

import com.tezign.mind.entity.node.project.DomainTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface DomainRepository extends Neo4jRepository<DomainTag, Long> {

    @Query("MERGE(d:Domain{ domain:{domain}.domain }) return d")
    DomainTag addDomain(@Param("domain") DomainTag domain);

}
