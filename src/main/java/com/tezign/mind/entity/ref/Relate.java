package com.tezign.mind.entity.ref;


import com.tezign.mind.common.RelType;
import com.tezign.mind.entity.BaseNode;
import com.tezign.mind.entity.BaseRef;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@Data
@NoArgsConstructor
@RelationshipEntity(type = RelType.Rel_Relate)
public class Relate extends BaseRef {


    public Relate(BaseNode startNode,String name,BaseNode endNode){
        this.name = name;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @StartNode
    private BaseNode startNode;

    @EndNode
    private BaseNode endNode;

}
