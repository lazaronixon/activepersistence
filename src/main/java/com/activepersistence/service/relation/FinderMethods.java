package com.activepersistence.service.relation;

import com.activepersistence.service.Relation;
import java.util.List;
import java.util.Optional;

public interface FinderMethods<T> {

    public Relation<T> thiz();

    public String getPrimaryKey();

    public default T take() {
        return thiz().limit(1).fetchOne();
    }

    public default Optional<T> takeOrFail() {
        return thiz().limit(1).fetchOneOrFail();
    }

    public default List<T> take(int limit) {
        return thiz().limit(limit).fetch();
    }

    public default T first() {
        return thiz().order(getPrimaryKey()).take();
    }

    public default Optional<T> firstOrFail() {
        return thiz().order(getPrimaryKey()).takeOrFail();
    }

    public default List<T> first(int limit) {
        return thiz().order(getPrimaryKey()).take(limit);
    }

    public default T last() {
        return thiz().order(getPrimaryKey() + " DESC").take();
    }

    public default Optional<T> lastOrFail() {
        return thiz().order(getPrimaryKey() + " DESC").takeOrFail();
    }

    public default List<T> last(int limit) {
        return thiz().order(getPrimaryKey() + " DESC").take(limit);
    }

    public default Optional<T> find(Object id) {
        return thiz().where(getPrimaryKey() + " = :_id").bind("_id", id).takeOrFail();
    }

    public default List<T> find(List<Object> ids) {
        return thiz().where(getPrimaryKey() + " IN :_ids").bind("_ids", ids).fetch();
    }

    public default T findBy(String conditions) {
        return thiz().where(conditions).take();
    }

    public default Optional<T> findByOrFail(String conditions, Object... params) {
        return thiz().where(conditions).takeOrFail();
    }

    public default boolean exists(String conditions) {
        return thiz().where(conditions).exists();
    }

    public default boolean exists() {
        return thiz().limit(1).fetchExists();
    }
}
