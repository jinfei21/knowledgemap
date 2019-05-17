package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.project.BudgetTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface BudgetRepository extends Neo4jRepository<BudgetTag, Long> {

    @Query("MERGE(b:Budget{ budget:{tag}.budget }) return b")
    BudgetTag addBudget(@Param("tag") BudgetTag budget);

}
