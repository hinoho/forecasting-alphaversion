package com.revolve44.fragments22.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.revolve44.fragments22.R;
import com.revolve44.fragments22.recyclerview.recyclerviewitems.RecyclerViewItemGraph;
import com.revolve44.fragments22.recyclerview.recyclerviewitems.RecyclerViewItemInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<RecyclerViewItem> viewItemList;

    public RecyclerViewDataAdapter(List<RecyclerViewItem> viewItemList) {
        this.viewItemList = viewItemList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if (viewType == RecyclerViewItem.TYPE_INFO) {
            view = layoutInflater.inflate(R.layout.recycler_view_item_info, parent, false);
        }
        else if (viewType == RecyclerViewItem.TYPE_GRAPH) {
            view = layoutInflater.inflate(R.layout.recycler_view_item_graph, parent, false);
        }
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        RecyclerViewItem viewItem = viewItemList.get(position);
        if(getItemViewType(position) == RecyclerViewItem.TYPE_INFO){
            holder.setTempData(((RecyclerViewItemInfo)viewItem).getTempData(), ((RecyclerViewItemInfo)viewItem).getWindData());
        }
        else if (getItemViewType(position) == RecyclerViewItem.TYPE_GRAPH){
            holder.setGraphData(((RecyclerViewItemGraph)viewItem).getGraphData(), ((RecyclerViewItemGraph)viewItem).getNominalPower());
        }
    }

    @Override
    public int getItemCount() {
        return viewItemList.size();
    }

    @Override
    public int getItemViewType(final int position) {
        final RecyclerViewItem item = viewItemList.get(position);
        return item.getType();
    }
}
