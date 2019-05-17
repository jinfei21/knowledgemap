package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.supplier.ExpertTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ExportRepository extends Neo4jRepository<ExpertTag, Long> {

    @Query("MERGE(e:Expert{ expertAt:{expert}.expertAt }) return e")
    ExpertTag addExpert(@Param("expert") ExpertTag expert);

}
