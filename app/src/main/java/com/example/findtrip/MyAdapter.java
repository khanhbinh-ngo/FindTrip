package com.example.findtrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<CardItem> itemList;
    private Context context;

    public MyAdapter(Context context, List<CardItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, favoriteIcon;
        TextView priceTextView, titleTextView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            favoriteIcon = view.findViewById(R.id.favoriteIcon);
            priceTextView = view.findViewById(R.id.priceTextView);
            titleTextView = view.findViewById(R.id.titleTextView);
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_light, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        CardItem item = itemList.get(position);

        holder.titleTextView.setText(item.getTitle());
        holder.priceTextView.setText(item.getPrice());

        // Load ảnh bằng Glide (phải thêm thư viện vào gradle)
        Glide.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.favone)
                .into(holder.imageView);

        // Gán sự kiện click nếu cần
        holder.favoriteIcon.setOnClickListener(v -> {
            // TODO: xử lý sự kiện khi nhấn yêu thích
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

