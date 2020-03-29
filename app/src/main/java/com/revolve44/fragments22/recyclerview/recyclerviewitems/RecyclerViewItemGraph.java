package com.revolve44.fragments22.recyclerview.recyclerviewitems;
import com.revolve44.fragments22.recyclerview.RecyclerViewItem;

import java.util.LinkedHashMap;


public class RecyclerViewItemGraph extends RecyclerViewItem {
    private LinkedHashMap<Long, Float> dataMap;
    private float nominalPower;

    public RecyclerViewItemGraph(LinkedHashMap<Long, Float> dataMap, float nominalPower) {
        this.type = RecyclerViewItem.TYPE_GRAPH;
        this.dataMap = dataMap;
        this.nominalPower = nominalPower;
    }

    public LinkedHashMap<Long, Float> getGraphData(){
        return dataMap;
    }

    public float getNominalPower() { return nominalPower;}
}
