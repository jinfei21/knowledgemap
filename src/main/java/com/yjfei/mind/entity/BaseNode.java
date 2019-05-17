package com.yjfei.mind.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

@Data
public class BaseNode {


    @Id
    @GeneratedValue
    protected Long id;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this.id == null) {
            // For newly created entity, id will be null
            return false;
        }

        BaseNode node = (BaseNode) obj;
        return this.id.equals(node.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }
}
