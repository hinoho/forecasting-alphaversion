package com.revolve44.fragments22.recyclerview;

import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.revolve44.fragments22.R;

import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView temp;
    private TextView wind;
    private GraphView graphView;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        temp = itemView.findViewById(R.id.temperature_card);
        wind = itemView.findViewById(R.id.wind_card);
        graphView = itemView.findViewById(R.id.graph_card);
        if(graphView != null) {
            graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(
                    itemView.getContext(), new SimpleDateFormat("HH:mm")
            ));
        }
    }

    public void setTempData(float temperature, float speed_of_wind){
        temp.setText(temperature+ " ℃");
        wind.setText(speed_of_wind + " m/s");
    }

    public void setGraphData(DataPoint[] dataPointsPreview){
        graphView.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointsPreview);
        graphView.addSeries(series);
    }
}
