package com.bahadir.cagatay.bahadircagatayproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bahadir.cagatay.bahadircagatayproject.Activities.MainActivity;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewItemHolder> {
    Context context;
    ArrayList<CoffeeBrand> recyclerItemValues;
    MainActivity ma;


    public MyRecyclerViewAdapter(Context context, ArrayList<CoffeeBrand> values){
        this.context = context;
        this.recyclerItemValues = values;
        ma = (MainActivity)context;
    }

    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.recycler_layout, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final CoffeeBrand sm = recyclerItemValues.get(i);

        myRecyclerViewItemHolder.tv.setText(sm.getCoffeeBrand());
        myRecyclerViewItemHolder.img.setImageResource(sm.getImgId());

        myRecyclerViewItemHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FancyToast.makeText(context, "Clicked on item "+sm.getCoffeeBrand(), FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
            }
        });

        myRecyclerViewItemHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FancyToast.makeText(context, "Clicked on "+ sm.getCoffeeBrand()+" Image of item", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                ma.getDeliverInfo(sm.getCoffeeBrand());
            }
        });

        myRecyclerViewItemHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FancyToast.makeText(context, "Clicked on "+ sm.getCoffeeBrand()+" TextView of item", FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

     class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder{
        TextView tv;
        ImageView img;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            img = itemView.findViewById(R.id.img);
            parentLayout = itemView.findViewById(R.id.constBrand);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return false;
                }
            });

        }
    }

}
