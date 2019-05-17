package com.tezign.mind.entity.node.portfolio;


import com.tezign.mind.common.NodeLabel;
import com.tezign.mind.entity.BaseNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NodeEntity(label = NodeLabel.Node_Emotion)
public class EmotionTag extends BaseNode {

    private String emotion;
}
