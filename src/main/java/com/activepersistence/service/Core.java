package com.activepersistence.service;

import com.activepersistence.service.arel.Entity;
import static java.beans.Introspector.decapitalize;

public interface Core<T> {

    public Class<T> getEntityClass();

    public default String getPrimaryKey() {
        return "id";
    }

    public default String getAlias() {
        return decapitalize(getEntityClass().getSimpleName());
    }

    public default Entity getArelEntity() {
        return new Entity(getEntityClass(), getAlias());
    }

    public default Relation<T> getRelation() {
        return new Relation((Base) this);
    }

    public default String getPrimaryKeyAttr() {
        return getAlias() + "." + getPrimaryKey();
    }

}