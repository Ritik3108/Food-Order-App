package com.ritikgarg12.foodorderapp.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ritikgarg12.foodorderapp.DB_Helper;
import com.ritikgarg12.foodorderapp.DetailsActivity;
import com.ritikgarg12.foodorderapp.MainActivity;
import com.ritikgarg12.foodorderapp.Models.OrderModel;
import com.ritikgarg12.foodorderapp.OrdersActivity;
import com.ritikgarg12.foodorderapp.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    ArrayList<OrderModel> list;
    Context context;

    public OrderAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_orderfood,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {
        final OrderModel model=list.get(position);
        holder.orderPhoto.setImageResource(model.getSoldItemImage());
        holder.orderTitle.setText(model.getSoldItemName());
        holder.orderNo.setText(model.getSoldItemorderNumber());
        holder.orderPrize.setText(model.getSoldItemPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsActivity.class);
                
                intent.putExtra("id",Integer.parseInt(model.getSoldItemorderNumber()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });
        
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")

                        .setMessage("Do you want to Delete")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DB_Helper helper=new DB_Helper(context);
                                if(helper.deleteOrder(model.getSoldItemorderNumber())>0)
                                {
                                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                            }
                }).show();


                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView orderPhoto;
        TextView orderTitle,orderNo,orderPrize;


        public ViewHolder(View itemView) {
            super(itemView);

            orderPhoto=itemView.findViewById(R.id.orderImage);
            orderTitle=itemView.findViewById(R.id.orderFoodNmae);
            orderNo=itemView.findViewById(R.id.orderNumber); 
            orderPrize=itemView.findViewById(R.id.oPrice);
        }
    }
}
