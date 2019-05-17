package com.tezign.mind.entity.node;


import com.tezign.mind.common.NodeLabel;
import com.tezign.mind.entity.BaseNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label = NodeLabel.Node_Project)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectEntity extends BaseNode {

    @Property
    private String name;    //项目名称

    @Property
    private Long pid;      //项目ID

}
