package com.yjfei.mind.repository;

import com.yjfei.mind.entity.node.portfolio.EmotionTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface EmotionRepository extends Neo4jRepository<EmotionTag, Long> {

    @Query("MERGE(e:Emotion{ emotion:{emotion}.emotion }) return e")
    EmotionTag addEmotion(@Param("emotion") EmotionTag emotion);

}
