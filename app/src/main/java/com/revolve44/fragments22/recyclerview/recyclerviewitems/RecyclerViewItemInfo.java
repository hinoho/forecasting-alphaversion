package com.revolve44.fragments22.recyclerview.recyclerviewitems;

import com.revolve44.fragments22.recyclerview.RecyclerViewItem;

public class RecyclerViewItemInfo extends RecyclerViewItem {
    private float temperature;
    private float speed_of_wind;

    public RecyclerViewItemInfo(float temperature, float speed_of_wind) {
        this.type = RecyclerViewItem.TYPE_INFO;
        this.temperature = temperature;
        this.speed_of_wind = speed_of_wind;
    }

    public float getTempData(){
        return temperature;
    }

    public float getWindData(){
        return speed_of_wind;
    }
}
