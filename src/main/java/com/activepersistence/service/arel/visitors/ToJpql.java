package com.activepersistence.service.arel.visitors;

import com.activepersistence.service.arel.Entity;
import com.activepersistence.service.arel.nodes.Constructor;
import com.activepersistence.service.arel.nodes.Distinct;
import com.activepersistence.service.arel.nodes.SelectCore;
import com.activepersistence.service.arel.nodes.SelectStatement;
import com.activepersistence.service.arel.nodes.SqlLiteral;
import java.util.List;

public class ToJpql extends Visitor {

    public StringBuilder visitEntity(Entity o, StringBuilder collector) {
        return collector.append(o.getSimpleName()).append(" ").append(o.getAlias());
    }

    public StringBuilder visitSelectStatement(SelectStatement o, StringBuilder collector) {
        collector = visitSelectCore(o.getCore(), collector);

        if (!o.getOrders().isEmpty()) {
            collector.append(" ORDER BY ");
            collectNodesFor(o.getOrders(), collector, "");
        }

        return collector;
    }

    public StringBuilder visitSelectCore(SelectCore o, StringBuilder collector) {
        collector.append("SELECT");
        collector = maybeVisit(o.getSetQuantifier(), collector);

        if (o.getSetConstructor() != null) {
            visit(o.getSetConstructor(), collector);
        } else {
            collectNodesFor(o.getProjections(), collector, " ");
        }

        collector.append(" FROM ");
        collector = visit(o.getSource(), collector);

        collectNodesFor(o.getJoins(), collector, " ", " ");
        collectNodesFor(o.getWheres(), collector, " WHERE ", " AND ");
        collectNodesFor(o.getGroups(), collector, " GROUP BY ");
        collectNodesFor(o.getHavings(), collector, " HAVING ", " AND ");
        return collector;
    }

    public StringBuilder visitDistinct(Distinct o, StringBuilder collector) {
        return collector.append("DISTINCT");
    }

    public StringBuilder visitConstructor(Constructor o, StringBuilder collector) {
        collector.append(" new ");
        collector.append(o.getClassName()).append("(");
        collectNodesFor(o.getProjections(), collector, "");
        collector.append(")");
        return collector;
    }

    public StringBuilder visitSqlLiteral(SqlLiteral o, StringBuilder collector) {
        return collector.append(o);
    }

    private StringBuilder maybeVisit(Object thing, StringBuilder collector) {
        return thing != null ? visit(thing, collector.append(" ")) : collector;
    }

    private void collectNodesFor(List nodes, StringBuilder collector, String spacer, String connector) {
        if (!nodes.isEmpty()) collector.append(spacer); injectJoin(nodes, collector, connector);
    }

    private void collectNodesFor(List nodes, StringBuilder collector, String spacer) {
        collectNodesFor(nodes, collector, spacer, ", ");
    }

    private void injectJoin(List list, StringBuilder collector, String joinStr) {
        for(int i = 0; i < list.size(); i++) { if (i != 0) collector.append(joinStr); collector = visit(list.get(i), collector); }
    }
}