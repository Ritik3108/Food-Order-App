package com.ritikgarg12.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.ritikgarg12.foodorderapp.Adapters.OrderAdapter;
import com.ritikgarg12.foodorderapp.Models.OrderModel;
import com.ritikgarg12.foodorderapp.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    ActivityOrdersBinding oBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oBinding=ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(oBinding.getRoot());


        DB_Helper helper=new DB_Helper(this);
        ArrayList<OrderModel> list=helper.getOrders();


        OrderAdapter oAdapter = new OrderAdapter(list,this);
        oBinding.orderRecyclerView.setAdapter(oAdapter);

        LinearLayoutManager oLayoutManager = new LinearLayoutManager(this);
        oBinding.orderRecyclerView.setLayoutManager(oLayoutManager);
    }
}