package com.revolve44.fragments22.ui.home;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;

import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.revolve44.fragments22.MainActivity;
import com.revolve44.fragments22.R;

import com.jjoe64.graphview.GraphView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;



public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Buttons
        Button button = root.findViewById(R.id.button_home);

        //TextViews
        final TextView real_output = root.findViewById(R.id.real_output);
        final TextView city = root.findViewById(R.id.city);
        final TextView temp = root.findViewById(R.id.temp4);
        final TextView wind = root.findViewById(R.id.wind4);
        final GraphView graphView = root.findViewById(R.id.graph);
        graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(
                getActivity(), new SimpleDateFormat("HH:mm")
        ));

        //TextView realoutput = root.findViewById(R.id.real_output);

        //imageView
        final ImageView lamp= root.findViewById(R.id.lamp);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        ((MainActivity) Objects.requireNonNull(getActivity())).runforecast();

        ((MainActivity)getActivity()).runforecast();
        MainActivity activity = (MainActivity) getActivity();
        Float myDataFromActivity = activity.getMyData();
        String myDataFromActivity2 = activity.getMyData2();
        Float temperature = activity.getMyData3();
        Float speed_of_wind = activity.getMyData4();
        LinkedHashSet<DataPoint> forecastData = activity.getMyData5();
        //все данные из запроса, на всякий случай
        DataPoint[] dataPoints = forecastData.toArray(new DataPoint[0]);
        //данные за последний день
        DataPoint[] dataPointsPreview = Arrays.copyOfRange(dataPoints, 0, 7);

//        Float myDataFromActivity4 = activity.getMyData4();
//        Power.setText(""+myDataFromActivity);
//        City.setText(myDataFromActivity2);
//        CurrOutPut.setText(""+myDataFromActivity3);
//        CurrTemp.setText(""+ myDataFromActivity4);

        real_output.setText(""+myDataFromActivity+" Watts");
        temp.setText(temperature+ " ℃");
        wind.setText(speed_of_wind + " m/s");
        city.setText(myDataFromActivity2);
        graphView.removeAllSeries();
        if (forecastData.size() > 0) {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointsPreview);
            graphView.addSeries(series);
        }



        button.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v)
            {
                // do something
                //lamp.setVisibility(View.VISIBLE);
                ((MainActivity) Objects.requireNonNull(getActivity())).runforecast();
            }
        });
        return root;
    }

}