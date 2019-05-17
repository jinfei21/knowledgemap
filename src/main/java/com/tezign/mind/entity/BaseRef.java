package com.tezign.mind.entity;


import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;

import java.time.LocalTime;

@Data
public class BaseRef {

    @Id
    @GeneratedValue
    protected Long id;


    @Property
    protected String name;


    @Property
    protected String createTime = LocalTime.now().toString();


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

        BaseRef ref = (BaseRef) obj;
        return this.id.equals(ref.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }


}
