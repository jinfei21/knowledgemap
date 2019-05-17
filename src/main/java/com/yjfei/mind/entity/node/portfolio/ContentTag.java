package com.yjfei.mind.entity.node.portfolio;


import com.yjfei.mind.common.NodeLabel;
import com.yjfei.mind.entity.BaseNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NodeEntity(label = NodeLabel.Node_Content)
public class ContentTag extends BaseNode {

    private String content;
}
