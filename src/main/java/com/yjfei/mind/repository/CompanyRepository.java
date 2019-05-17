package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.project.CompanyTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends Neo4jRepository<CompanyTag, Long> {

    @Query("MERGE(c:Company{ company:{company}.company }) return c")
    CompanyTag addCompany(@Param("company") CompanyTag company);

}
