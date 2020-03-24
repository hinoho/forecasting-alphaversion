package com.revolve44.fragments22.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.series.DataPoint;
import com.revolve44.fragments22.MainActivity;
import com.revolve44.fragments22.R;

import com.revolve44.fragments22.recyclerview.RecyclerViewDataAdapter;
import com.revolve44.fragments22.recyclerview.RecyclerViewItem;
import com.revolve44.fragments22.recyclerview.recyclerviewitems.RecyclerViewItemGraph;
import com.revolve44.fragments22.recyclerview.recyclerviewitems.RecyclerViewItemInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private ArrayList<RecyclerViewItem> itemList = null;

    private RecyclerView recyclerView;
    private View root;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        //Buttons
        Button button = root.findViewById(R.id.button_home);

        //TextViews
        final TextView realOutputTextView = root.findViewById(R.id.real_output);
        final TextView cityTextView = root.findViewById(R.id.city);

        final ImageView lamp = root.findViewById(R.id.lamp);

        ((MainActivity) Objects.requireNonNull(getActivity())).runforecast();
        ((MainActivity)getActivity()).runforecast();
        MainActivity activity = (MainActivity) getActivity();
        Float currentPower = activity.getCurrentPowerData();
        String city = activity.getCityData();

        realOutputTextView.setText("" + currentPower + " Watts");
        cityTextView.setText(city);

        //recycler view
        if(currentPower != 0) {
            initControls();
            recyclerView = root.findViewById(R.id.recycler_view);
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            RecyclerViewDataAdapter recyclerViewAdapter = new RecyclerViewDataAdapter(itemList);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setLayoutManager(layoutManager);
        }

        button.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v)
            {
                ((MainActivity) Objects.requireNonNull(getActivity())).runforecast();
            }
        });
        return root;
    }

    private void initControls()
    {
        if(recyclerView == null)
        {
            recyclerView = root.findViewById(R.id.recycler_view);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
        }
        if(itemList == null)
        {
            itemList = new ArrayList<>();
            MainActivity activity = (MainActivity) getActivity();
            Float temperature = activity.getTempData();
            Float speedOfWind = activity.getWindData();
            RecyclerViewItemInfo info = new RecyclerViewItemInfo(temperature, speedOfWind);
            itemList.add(info);

            LinkedHashSet<DataPoint> forecastData = activity.getDataPointsData();
            //все данные из запроса, на всякий случай
            DataPoint[] dataPoints = forecastData.toArray(new DataPoint[0]);
            //данные за последний день
            DataPoint[] dataPointsPreview = Arrays.copyOfRange(dataPoints, 0, 7);
            RecyclerViewItemGraph graph = new RecyclerViewItemGraph(dataPointsPreview);
            itemList.add(graph);
        }
    }
}