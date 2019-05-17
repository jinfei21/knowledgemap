package com.tezign.mind.entity.node;

import com.tezign.mind.common.NodeLabel;
import com.tezign.mind.entity.BaseNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NodeEntity(label = NodeLabel.Node_Portfolio)
public class PortfolioEntity extends BaseNode {


    @Property
    private String name;       //案例名称

    @Property
    private Long eid;          //案例ID

    @Property
    private String type;       //案例类型

}
