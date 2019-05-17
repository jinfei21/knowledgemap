package com.tezign.mind.repository;

import com.tezign.mind.entity.node.supplier.CategoryTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends Neo4jRepository<CategoryTag, Long> {

    @Query("MERGE(c:Category{ category:{category}.category }) return c")
    CategoryTag addCategory(@Param("category") CategoryTag category);

}
