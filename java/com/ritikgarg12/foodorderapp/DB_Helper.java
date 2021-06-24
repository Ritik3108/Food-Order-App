package com.ritikgarg12.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ritikgarg12.foodorderapp.Models.OrderModel;

import java.util.ArrayList;

public class DB_Helper extends SQLiteOpenHelper {

    final static String DBName="mydatabase.db";
    final static int DBVersion=2;


    public DB_Helper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text,"+
                        "price int," +
                        "image int," +
                        "quantity int," +
                        "description text," +
                        "foodname text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);

    }

    public boolean insertOrder(String name,String phone,int price,int image,int quantity,String desc,String foodname)
    {
        SQLiteDatabase database=getReadableDatabase();

        ContentValues values=new ContentValues();

        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("description",desc);
        values.put("foodname",foodname);

        long id=database.insert("orders",null,values);
        if(id<=0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public ArrayList<OrderModel> getOrders()
    {
        ArrayList<OrderModel> orders=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select id,foodname,image,price from orders",null);

        if(cursor.moveToFirst())
        {
            while(cursor.moveToNext())
            {
                OrderModel model=new OrderModel();
                model.setSoldItemorderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setSoldItemImage(cursor.getInt(2));
                model.setSoldItemPrice(cursor.getInt(3)+"");
                orders.add(model);
            }
        }
        cursor.close();
        db.close();
        return orders;
    }

    public Cursor getOrderbyId(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from orders where id= " +id ,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }


        return cursor;
    }
    public boolean updateOrder(String name,String phone,int price,int image,int quantity,String desc,String foodname,int id)
    {
        SQLiteDatabase database=getReadableDatabase();

        ContentValues values=new ContentValues();

        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("foodname",foodname);
        values.put("description",desc);
        values.put("quantity",quantity);

        long row=database.update("orders",values,"id="+id,null);
        if(row<=0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public int deleteOrder(String id)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }

}
