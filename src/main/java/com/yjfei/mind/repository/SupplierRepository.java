package com.yjfei.mind.repository;

import com.yjfei.mind.entity.BaseNode;
import com.yjfei.mind.entity.node.PortfolioEntity;
import com.yjfei.mind.entity.node.SupplierEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends Neo4jRepository<SupplierEntity, Long> {



//    @Query("MERGE(s:Supplier{  name:{name},sex:{sex},impact:{impact},experience:{experience}  }) return s")
//    SupplierEntity addSupplier(@Param("name") String name,@Param("sex") String sex,@Param("impact") String impact,@Param("experience") String experience);


    @Query("MERGE(s:Supplier{ name:{supplier}.name,sid:{supplier}.sid,gender:{supplier}.gender }) return s")
    SupplierEntity addSupplier(@Param("supplier") SupplierEntity supplier);


    SupplierEntity findBySid(@Param("sid") Long sid);

    Long deleteBySid(Long sid);


    @Query("MATCH (p:Project{pid:{pid}})-[]->(b) with * MATCH (b)<-[]-(pl:Project) with * MATCH (pf:Portfolio)-[]->(pl) return pf")
    List<PortfolioEntity> findSimilarPortfolioByPid(@Param("pid") Long pid);


    @Query("MATCH (p:Project{pid:{pid}})-[]->(b) with * MATCH (b)<-[]-(pl:Project) with * MATCH (s:Supplier)-[]->(pl) return s")
    List<SupplierEntity>  findRightSupplierByPid(@Param("pid") Long pid);


    @Query("MATCH (p:Portfolio{eid:{eid}})-[]->(b) with * MATCH (b)<-[]-(pl:Portfolio) with * MATCH(s:Supplier)-[]->(pl) return s")
    List<SupplierEntity> findRightSupplierByEid(@Param("eid") Long eid);


    @Query("MATCH (p:Project{pid:{pid}})-[]->(b) with * MATCH (b)<-[]-(pl:Project) with * MATCH (:Portfolio)-[]->(pr) return pr ")
    List<BaseNode> findPortfolioTagByPid(@Param("pid") Long pid);


    /**
     * Jaccard相似度定义
     * J（A,B） = [A & B] / [A || B]   A与B的交集除以A与B的并集
     *
     */

    @Query("MATCH (p:Project{pid:{pid}})-[]->(tag)<-[]-(other:Project) WITH p,other,COUNT(tag) AS intersection MATCH (p)-[:REF]->(ptag) WITH p,other,intersection,COLLECT(ptag) as s1 MATCH(other)-[:REF]->(otag) WITH p,other,intersection,s1,COLLECT(otag) AS s2 WITH p,other,intersection,s1+filter(x IN s2 WHERE NOT x IN s1) AS s3,s1,s2 WITH p,other,s1,s2,s3,intersection,((1.0*intersection)/SIZE(s3)) AS jaccard MATCH (other)<-[]-(pf:Portfolio) WITH DISTINCT(pf),jaccard return pf ORDER BY jaccard DESC")
    List<PortfolioEntity> searchSimilarPortfolioByPid(@Param("pid") Long pid);


    @Query("MATCH (p:Project{pid:1})-[]->(tag)<-[]-(other:Project) WITH p,other,COUNT(tag) AS intersection MATCH (p)-[:REF]->(ptag) WITH p,other,intersection,COLLECT(ptag) as s1 MATCH(other)-[:REF]->(otag) WITH p,other,intersection,s1,COLLECT(otag) AS s2 WITH p,other,intersection,s1+filter(x IN s2 WHERE NOT x IN s1) AS s3,s1,s2 WITH p,other,s1,s2,s3,intersection,((1.0*intersection)/SIZE(s3)) AS jaccard MATCH (other)<-[]-(s:Supplier) WITH DISTINCT(s),jaccard return s ORDER BY jaccard DESC")
    List<SupplierEntity> searchSimiliarSupplierByPid(@Param("pid") Long pid);


    @Query("MATCH (p:Portfolio{eid:{eid}})-[]->(tag)<-[]-(other:Portfolio) WITH p,other,COUNT(tag) AS intersection MATCH (p)-[:REF]->(ptag) WITH p,other,intersection,COLLECT(ptag) as s1 MATCH(other)-[:REF]->(otag) WITH p,other,intersection,s1,COLLECT(otag) AS s2 WITH p,other,intersection,s1+filter(x IN s2 WHERE NOT x IN s1) AS s3,s1,s2 WITH p,other,s1,s2,s3,intersection,((1.0*intersection)/SIZE(s3)) AS jaccard MATCH (other)<-[]-(s:Supplier) WITH DISTINCT(s),jaccard return s ORDER BY jaccard DESC")
    List<SupplierEntity> searchSimiliarSupplierByEid(@Param("eid") Long eid);


    @Query("MATCH (s:Supplier{sid:{sid}})-[]->(tag)<-[]-(other:Supplier) WITH s,other,COUNT(tag) AS intersection MATCH (s)-[:REF]->(ptag) WITH s,other,intersection,COLLECT(ptag) as s1 MATCH(other)-[:REF]->(otag) WITH s,other,intersection,s1,COLLECT(otag) AS s2 WITH s,other,intersection,s1+filter(x IN s2 WHERE NOT x IN s1) AS s3,s1,s2 WITH s,other,s1,s2,s3,intersection,((1.0*intersection)/SIZE(s3)) AS jaccard WITH DISTINCT(other),jaccard return other ORDER BY jaccard DESC")
    List<SupplierEntity> searchSimiliarSupplierBySid(@Param("sid") Long sid);

}
