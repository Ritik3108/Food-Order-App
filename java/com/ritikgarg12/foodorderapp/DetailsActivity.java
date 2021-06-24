package com.ritikgarg12.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ritikgarg12.foodorderapp.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding dBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBinding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(dBinding.getRoot());

        DB_Helper helper=new DB_Helper(this);

        if(getIntent().getIntExtra("type",0)==1){
        final int image=getIntent().getIntExtra("image",0);
        final int price=Integer.parseInt(getIntent().getStringExtra("price"));
        final String desc=getIntent().getStringExtra("desc");
        final String name=getIntent().getStringExtra("name");

        dBinding.detailsBurger.setImageResource(image);
        dBinding.detailPrice.setText(String.format("%d",price));
        dBinding.detailName.setText(name);
        dBinding.detailsDescription.setText(desc);


        dBinding.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=helper.insertOrder(dBinding.nameBox.getText().toString(),
                dBinding.phoneBox.getText().toString(),
                price,
                image,
                        Integer.parseInt(dBinding.quantity.getText().toString()),
                        desc,
                        name
                );
                if(isInserted)
                {
                    Toast.makeText(DetailsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(DetailsActivity.this, "Error",Toast.LENGTH_SHORT).show();
                }

            }
        });}
        else
        {
            int id=getIntent().getIntExtra("id",0);
            Cursor cursor=helper.getOrderbyId(id);

            int pico=cursor.getInt(4);
            dBinding.detailsBurger.setImageResource(pico);

            dBinding.detailPrice.setText(String.format("%d",cursor.getInt(3)));
            dBinding.detailName.setText(cursor.getString(7));
            dBinding.detailsDescription.setText(cursor.getString(6));

            dBinding.nameBox.setText(cursor.getString(1));
            dBinding.phoneBox.setText(cursor.getString(2));
            dBinding.orderButton.setText("Update Now");
            dBinding.orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean isUpdated=helper.updateOrder(dBinding.nameBox.getText().toString(),
                            dBinding.phoneBox.getText().toString(),
                            Integer.parseInt(dBinding.detailPrice.getText().toString()),
                            pico,

                            1,
                            dBinding.detailsDescription.getText().toString(),

                            dBinding.detailName.getText().toString(),
                            id
                    );

                    if(isUpdated)
                    {
                        Toast.makeText(DetailsActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(DetailsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}