package com.activepersistence.service;

import com.activepersistence.service.relation.QueryMethods.ValidUnscopingValues;
import java.util.List;
import java.util.function.Supplier;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public interface Querying<T> {

    public EntityManager getEntityManager();

    public Class<T> getEntityClass();

    public default Relation<T> all() {
        return relation().all();
    }

    public default Relation<T> unscoped() {
        return relation().unscoped();
    }

    public default Relation<T> merge(Relation other) {
        return all().merge(other);
    }

    //<editor-fold defaultstate="collapsed" desc="Bindings">
    public default Relation<T> bind(int position, Object value) {
        return all().bind(position, value);
    }

    public default Relation<T> bind(String name, Object value) {
        return all().bind(name, value);
    }

    public default Relation<T> bind(int position, com.activepersistence.model.Base model) {
        return all().bind(position, model.getId());
    }

    public default Relation<T> bind(String name, com.activepersistence.model.Base model) {
        return all().bind(name, model.getId());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Calculation">
    public default Object count() {
        return all().count();
    }

    public default Object count(String field) {
        return all().count(field);
    }

    public default Object minimum(String field) {
        return all().minimum(field);
    }

    public default Object maximum(String field) {
        return all().maximum(field);
    }

    public default Object average(String field) {
        return all().average(field);
    }

    public default Object sum(String field) {
        return all().sum(field);
    }

    public default List pluck(String... field) {
        return all().pluck(field);
    }

    public default List<Object> ids() {
        return all().ids();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FinderMethods">
    public default T take() {
        return all().take();
    }

    public default T takeOrFail() {
        return all().takeOrFail();
    }

    public default T first() {
        return all().first();
    }

    public default T firstOrFail() {
        return all().firstOrFail();
    }

    public default T last() {
        return all().last();
    }

    public default T lastOrFail() {
        return all().lastOrFail();
    }

    public default List<T> take(int limit) {
        return all().take(limit);
    }

    public default List<T> first(int limit) {
        return all().first(limit);
    }

    public default List<T> last(int limit) {
        return all().last(limit);
    }

    public default T find(Object id) {
        return all().find(id);
    }

    public default List<T> find(List<Object> ids) {
        return all().find(ids);
    }

    public default T findBy(String conditions) {
        return all().findBy(conditions);
    }

    public default T findByOrFail(String conditions) {
        return all().findByOrFail(conditions);
    }

    public default boolean exists() {
        return all().exists();
    }

    public default boolean exists(String conditions) {
        return all().exists(conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="QueryMethods">
    public default Relation<T> where(String conditions) {
        return all().where(conditions);
    }

    public default Relation<T> order(String... values) {
        return all().order(values);
    }

    public default Relation<T> limit(int value) {
        return all().limit(value);
    }

    public default Relation<T> offset(int value) {
        return all().offset(value);
    }

    public default Relation<T> select(String... values) {
        return all().select(values);
    }

    public default Relation<T> joins(String value) {
        return all().joins(value);
    }

    public default Relation<T> group(String... values) {
        return all().group(values);
    }

    public default Relation<T> having(String conditions) {
        return all().having(conditions);
    }

    public default Relation<T> distinct() {
        return all().distinct(true);
    }

    public default Relation<T> distinct(boolean value) {
        return all().distinct(value);
    }

    public default Relation<T> includes(String... values) {
        return all().includes(values);
    }

    public default Relation<T> eagerLoads(String... values) {
        return all().eagerLoads(values);
    }

    public default Relation<T> unscope(ValidUnscopingValues... values) {
        return all().unscope(values);
    }

    public default Relation<T> except(ValidUnscopingValues... values) {
        return all().except(values);
    }

    public default Relation<T> only(ValidUnscopingValues... values) {
        return all().only(values);
    }

    public default Relation<T> reselect(String... values) {
        return all().reselect(values);
    }

    public default Relation<T> rewhere(String condition) {
        return all().rewhere(condition);
    }

    public default Relation<T> reorder(String... fields) {
        return all().reorder(fields);
    }

    public default Relation<T> lock() {
        return all().lock();
    }

    public default Relation<T> lock(boolean value) {
        return all().lock(value);
    }

    public default Relation<T> from(String from) {
        return all().from(from);
    }

    public default Relation<T> none() {
        return all().none();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Relation">
    public default T findOrCreateBy(String conditions, Supplier<T> resource) {
        return all().findOrCreateBy(conditions, resource);
    }

    public default T findOrInitializeBy(String conditions, Supplier<T> resource) {
        return all().findOrInitializeBy(conditions, resource);
    }

    public default List<T> destroyAll() {
        return all().destroyAll();
    }

    public default List<T> destroyBy(String conditions) {
        return all().destroyBy(conditions);
    }

    public default int deleteAll() {
        return all().deleteAll();
    }

    public default int deleteBy(String conditions) {
        return all().deleteBy(conditions);
    }

    public default int updateAll(String updates) {
        return all().updateAll(updates);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Quering">
    public default Query buildNativeQuery(String qlString) {
        return getEntityManager().createNativeQuery(qlString, getEntityClass());
    }

    public default Query buildNativeQuery(String qlString, Class resultClass) {
        return getEntityManager().createNativeQuery(qlString, resultClass);
    }

    public default Query buildNativeQuery_(String sqlQuery) {
        return getEntityManager().createNativeQuery(sqlQuery);
    }

    public default TypedQuery<T> buildQuery(String qlString) {
        return getEntityManager().createQuery(qlString, getEntityClass());
    }

    public default <R> TypedQuery<R> buildQuery(String qlString, Class<R> resultClass) {
        return getEntityManager().createQuery(qlString, resultClass);
    }

    public default Query buildQuery_(String qlString) {
        return getEntityManager().createQuery(qlString);
    }
    //</editor-fold>

    private Relation<T> relation() {
        return new Relation((Base) this);
    }

}
