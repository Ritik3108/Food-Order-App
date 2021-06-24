package com.ritikgarg12.foodorderapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ritikgarg12.foodorderapp.DetailsActivity;
import com.ritikgarg12.foodorderapp.Models.MainModel;
import com.ritikgarg12.foodorderapp.R;

import java.util.ArrayList;

public class MainAdapter extends  RecyclerView.Adapter<MainAdapter.View_Holder>{

    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;

    }

    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sample_mainfood,parent,false);
        return new View_Holder(view);
    }


    public void onBindViewHolder(MainAdapter.View_Holder holder, int position) {

        final MainModel model = list.get(position);
        holder.foodImage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.mainPrice.setText(model.getPrize());
        holder.mainDesc.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("image",model.getImage());
                intent.putExtra("price",model.getPrize());
                intent.putExtra("desc",model.getDescription());
                intent.putExtra("name",model.getName());
                intent.putExtra("type",1);
                context.startActivity(intent);
            }
        });

    }


    public int getItemCount()
    {
        return list.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView mainName, mainPrice, mainDesc;


        public View_Holder(View itemView) {
            super(itemView);

            foodImage= (ImageView) itemView.findViewById(R.id.burger);
            mainName= (TextView) itemView.findViewById(R.id.Name);
            mainPrice= (TextView) itemView.findViewById(R.id.price);
            mainDesc= (TextView) itemView.findViewById(R.id.desc);
        }
    }
}
