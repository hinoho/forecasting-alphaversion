package com.revolve44.fragments22.recyclerview;

abstract public class RecyclerViewItem {
    public static int TYPE_INFO = 0;
    public static int TYPE_GRAPH = 1;

    protected int type;

    public int getType() {
        return type;
    }
}
