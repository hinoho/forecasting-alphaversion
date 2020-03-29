package com.revolve44.fragments22.recyclerview;

import android.view.View;
import android.widget.TextView;

import com.revolve44.fragments22.R;

import java.util.LinkedHashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView temp;
    private TextView wind;
    private GraphView view;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        temp = itemView.findViewById(R.id.temperature_card);
        wind = itemView.findViewById(R.id.wind_card);
        view = itemView.findViewById(R.id.graphview);
    }

    public void setTempData(float temperature, float speed_of_wind){
        temp.setText(temperature+ " ℃");
        wind.setText(speed_of_wind + " m/s");
    }

    public void setGraphData(LinkedHashMap<Long, Float> dataMap, float nominalPower){
        view.setData(dataMap);
        view.setNominalPower(nominalPower);
        view.invalidate();
    }
}
