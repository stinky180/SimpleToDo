package com.example.simpletodo;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import android.support.v7.widget.RecyclerView;
//import android.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @android.support.annotation.NonNull
    @Override
    public ViewHolder onCreateViewHolder(@android.support.annotation.NonNull ViewGroup parent, int viewType) {
        // Use layout inflater to inflate a view
        // wrap it inside a View Holder and return it
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
    }

    // responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@android.support.annotation.NonNull ViewHolder holder, int position) {
        // grab the item at the position
        String item = items.get(position);
        // Bind the item into the specified view holder
        holder.bind(item);
    }

    // tells the RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // notify item has been long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
