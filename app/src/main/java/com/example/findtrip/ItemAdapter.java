package com.example.findtrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<ItemModel> items;
    private Context context;

    public ItemAdapter(List<ItemModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_light, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = items.get(position);
        holder.image.setImageResource(item.imageResId);
        holder.title.setText(item.title);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemimage);
            title = itemView.findViewById(R.id.itemtitle);
        }
    }
}
