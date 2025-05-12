package com.example.pakwheelsclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class CarAdAdapter extends RecyclerView.Adapter<CarAdAdapter.CarAdViewHolder> {

    private List<CarAd> carAdList;

    public CarAdAdapter(List<CarAd> carAdList) {
        this.carAdList = carAdList;
    }

    @NonNull
    @Override
    public CarAdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_ad_item, parent, false);
        return new CarAdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdViewHolder holder, int position) {
        CarAd carAd = carAdList.get(position);
        holder.carTitle.setText(carAd.getTitle());
        holder.carPrice.setText(carAd.getPrice());
        Glide.with(holder.itemView.getContext())
                .load(carAd.getImageUrl())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.carImage);
    }

    @Override
    public int getItemCount() {
        return carAdList != null ? carAdList.size() : 0;
    }

    public static class CarAdViewHolder extends RecyclerView.ViewHolder {
        ImageView carImage;
        TextView carTitle;
        TextView carPrice;

        public CarAdViewHolder(@NonNull View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.carImage);
            carTitle = itemView.findViewById(R.id.carTitle);
            carPrice = itemView.findViewById(R.id.carPrice);
        }
    }
}