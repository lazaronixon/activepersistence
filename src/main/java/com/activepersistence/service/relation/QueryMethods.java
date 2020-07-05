package com.activepersistence.service.relation;

import com.activepersistence.service.Relation;
import static java.util.Arrays.asList;

public interface QueryMethods<T> {

    public enum ValidUnscopingValues {
        WHERE, SELECT, GROUP, ORDER, LOCK, LIMIT, OFFSET, JOINS, INCLUDES, EAGER_LOADS, FROM, HAVING
    }

    public Relation<T> thiz();

    public Relation<T> spawn();

    public Values getValues();

    public default Relation<T> select(String... fields) {
        return spawn().select_(fields);
    }

    public default Relation<T> select_(String... fields) {
        getValues().getSelectValues().addAll(asList(fields));
        getValues().setConstructor(true);
        return thiz();
    }

    public default Relation<T> joins(String value) {
        return spawn().joins_(value);
    }

    public default Relation<T> joins_(String value) {
        getValues().getJoinsValues().add(value); return thiz();
    }

    public default Relation<T> where(String conditions) {
        return spawn().where_(conditions);
    }

    public default Relation<T> where_(String conditions) {
        getValues().getWhereValues().add(conditions); return thiz();
    }

    public default Relation<T> group(String... fields) {
        return spawn().group_(fields);
    }

    public default Relation<T> group_(String... fields) {
        getValues().getGroupValues().addAll(asList(fields)); return thiz();
    }

    public default Relation<T> having(String conditions) {
        return spawn().having_(conditions);
    }

    public default Relation<T> having_(String conditions) {
        getValues().getHavingValues().add(conditions); return thiz();
    }

    public default Relation<T> order(String... fields) {
        return spawn().order_(fields);
    }

    public default Relation<T> order_(String... fields) {
        getValues().getOrderValues().addAll(asList(fields)); return thiz();
    }

    public default Relation<T> limit(int limit) {
        return spawn().limit_(limit);
    }

    public default Relation<T> limit_(int limit) {
        getValues().setLimitValue(limit); return thiz();
    }

    public default Relation<T> offset(int offset) {
        return spawn().offset_(offset);
    }

    public default Relation<T> offset_(int offset) {
        getValues().setOffsetValue(offset); return thiz();
    }

    public default Relation<T> distinct() {
        return spawn().distinct_(true);
    }

    public default Relation<T> distinct(boolean value) {
        return spawn().distinct_(value);
    }

    public default Relation<T> distinct_(boolean value) {
        getValues().setDistinctValue(value); return thiz();
    }

    public default Relation<T> none() {
        return spawn().none_();
    }

    public default Relation<T> none_() {
        thiz().where_("1=0"); return thiz();
    }

    public default Relation<T> includes(String... includes) {
        return spawn().includes_(includes);
    }

    public default Relation<T> includes_(String... includes) {
        getValues().getIncludesValues().addAll(asList(includes)); return thiz();
    }

    public default Relation<T> eagerLoads(String... eagerLoads) {
        return spawn().eagerLoads_(eagerLoads);
    }

    public default Relation<T> eagerLoads_(String... eagerLoads) {
        getValues().getEagerLoadsValues().addAll(asList(eagerLoads)); return thiz();
    }

    public default Relation<T> lock() {
        return spawn().lock_(true);
    }

    public default Relation<T> lock(boolean value) {
        return spawn().lock_(value);
    }

    public default Relation<T> lock_(boolean value) {
        getValues().setLockValue(value); return thiz();
    }

    public default Relation<T> from(String value) {
        return spawn().from_(value);
    }

    public default Relation<T> from_(String value) {
        getValues().setFromClause(value); return thiz();
    }

    public default Relation<T> unscope(ValidUnscopingValues... values) {
        return spawn().unscope_(values);
    }

    public default Relation<T> unscope_(ValidUnscopingValues... values) {
        for (ValidUnscopingValues value : values) {
            switch (value) {
                case SELECT:
                    getValues().getSelectValues().clear();
                    getValues().setConstructor(false);
                case FROM:
                    getValues().setFromClause(null);
                case JOINS:
                    getValues().getJoinsValues().clear();
                case WHERE:
                    getValues().getWhereValues().clear();
                case GROUP:
                    getValues().getGroupValues().clear();
                case HAVING:
                    getValues().getHavingValues().clear();
                case ORDER:
                    getValues().getOrderValues().clear();
                case LIMIT:
                    getValues().setLimitValue(0);
                case OFFSET:
                    getValues().setOffsetValue(0);
                case INCLUDES:
                    getValues().getIncludesValues().clear();
                case EAGER_LOADS:
                    getValues().getEagerLoadsValues().clear();
                case LOCK:
                    getValues().setLockValue(false);
            }
        }
        return thiz();
    }

    public default Relation<T> reselect(String... fields) {
        return spawn().unscope(ValidUnscopingValues.SELECT).select(fields);
    }

    public default Relation<T> rewhere(String conditions) {
        return spawn().unscope(ValidUnscopingValues.WHERE).where(conditions);
    }

    public default Relation<T> reorder(String... fields) {
        return spawn().unscope(ValidUnscopingValues.ORDER).order(fields);
    }

}
