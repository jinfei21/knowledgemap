package com.tezign.mind.entity.node;


import com.tezign.mind.common.NodeLabel;
import com.tezign.mind.common.RelType;
import com.tezign.mind.entity.BaseNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;


@NodeEntity(label = NodeLabel.Node_Supplier)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierEntity extends BaseNode {

    @Property
    private String name;        //设计师名称

    @Property
    private String gender;     //设计师性别

    @Property
    private Long sid;         //设计师ID



    /**
     * 一对多关系
     */
    @Relationship(type= RelType.Rel_Relate,direction = Relationship.OUTGOING)
    private List<PortfolioEntity> examples;

    /**
     * 一对多关系
     */
    @Relationship(type= RelType.Rel_Relate,direction = Relationship.OUTGOING)
    private List<ProjectEntity> projects;
}


