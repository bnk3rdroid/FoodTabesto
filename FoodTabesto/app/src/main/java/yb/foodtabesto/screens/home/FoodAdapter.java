package yb.foodtabesto.screens.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yb.foodtabesto.R;
import yb.foodtabesto.data.Food;

/**
 * Adapter for list of Food.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodVH> {

    private List<Food> mData;

    private Context mContext;

    public FoodAdapter(@NonNull List<Food> data) {
        mData = data;
    }

    @Override
    public FoodVH onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_food, parent, false);
        return new FoodVH(view);
    }

    @Override
    public void onBindViewHolder(FoodVH holder, int position) {
        Food current = mData.get(position);
        holder.bindName(current.getName());
        holder.bindPrice(current.getPrice());
        holder.bindImage(current.getImageThumbnailUrl());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateData(@NonNull List<Food> data) {
        mData = data;
        notifyDataSetChanged();
    }

    class FoodVH extends RecyclerView.ViewHolder {

        @BindView(R.id.product_name)
        TextView mName;

        @BindView(R.id.product_price)
        TextView mPrice;

        @BindView(R.id.product_image)
        ImageView mImage;

        FoodVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bindName(@NonNull String name) {
            mName.setText(name);
        }

        void bindPrice(@NonNull String price) {
            mPrice.setText(price);
        }

        void bindImage(@NonNull String url) {
            Glide.with(mContext).load(url).into(mImage);
        }
    }

}
