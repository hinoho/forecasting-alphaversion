package com.revolve44.fragments22.recyclerview.recyclerviewitems;

import com.jjoe64.graphview.series.DataPoint;
import com.revolve44.fragments22.recyclerview.RecyclerViewItem;


public class RecyclerViewItemGraph extends RecyclerViewItem {
    private DataPoint[] dataPoints;

    public RecyclerViewItemGraph(DataPoint[] dataPoint) {
        this.type = RecyclerViewItem.TYPE_GRAPH;
        this.dataPoints = dataPoint;
    }

    public DataPoint[] getGraphData(){
        return dataPoints;
    }
}
