package online.thrift.shop.adapters;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import online.thrift.shop.R;
import online.thrift.shop.models.good;
import online.thrift.shop.ui.HomePageActivity;

public class goodsListAdapter extends RecyclerView.Adapter <goodsListAdapter.ViewHolder> {

    ArrayList<good> mGoods;
    Context mContext;

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView goodNametextView;
        public TextView LocationtextView;
        public  TextView PriceTextView;
        public  ImageView imageViewGoodItem;
        public TextView chatTextView;
        public  TextView contactTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodNametextView = (TextView) itemView.findViewById(R.id.goodNametextView);
            LocationtextView = (TextView) itemView.findViewById(R.id.LocationtextView);
            PriceTextView = (TextView)  itemView.findViewById(R.id.PriceTextView);
            imageViewGoodItem = (ImageView) itemView.findViewById(R.id.imageViewGoodItem);
            chatTextView = (TextView) itemView.findViewById(R.id.ChatTextView);
            contactTextView = (TextView) itemView.findViewById(R.id.ContactTextView);

        }

    }


    public goodsListAdapter(ArrayList<good> mGoods, Context mContext) {
        this.mGoods = mGoods;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public goodsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.good_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull goodsListAdapter.ViewHolder holder, int position) {

        good goodsList = mGoods.get(position);
        holder.goodNametextView.setText(goodsList.getName());
        holder.LocationtextView.setText(goodsList.getLocation());
        holder.PriceTextView.setText(goodsList.getPhoneNumber());
        holder.imageViewGoodItem.setImageResource(goodsList.getImageURL());
        holder.contactTextView.setText(goodsList.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return mGoods.size();
    }



}
