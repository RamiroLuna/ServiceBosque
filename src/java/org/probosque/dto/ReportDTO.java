package org.probosque.dto;

import java.util.ArrayList;

public class ReportDTO {

    private ArrayList<ColumnDTO> fields;
    private OrderDTO order;
    private FilterDTO filter;
    private GraphDTO graph;

    public ArrayList<ColumnDTO> getFields() {
        return fields;
    }

    public void setFields(ArrayList<ColumnDTO> fields) {
        this.fields = fields;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public FilterDTO getFilter() {
        return filter;
    }

    public void setFilter(FilterDTO filter) {
        this.filter = filter;
    }

    public GraphDTO getGraph() {
        return graph;
    }

    public void setGraph(GraphDTO graph) {
        this.graph = graph;
    }
}