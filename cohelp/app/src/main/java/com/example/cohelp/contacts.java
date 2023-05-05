package com.example.cohelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.cohelp.MyAdapter;
import com.example.cohelp.MyModel;
import com.example.cohelp.R;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class contacts extends AppCompatActivity {

    ProgressDialog dialog;
    MyAdapter myAdapter;
    MyModel myModel;
    int length=0;
    RecyclerView recyclerView;
    List<MyModel>my_list=new ArrayList<>();


    Integer value[];
    String name[];
    String temp_name;
    Integer temp_value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("LET'S VACCINATED");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#018786")));
        setContentView(R.layout.activity_contacts);
        dialog=new ProgressDialog(this);
        dialog.setMessage("LOADING");
           dialog.show();

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(contacts.this));

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("CENTRAL STATES");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                length=(int) snapshot.getChildrenCount();
                // Toast.makeText(getApplicationContext(),"Length= "+length,Toast.LENGTH_SHORT).show();
                if (length!=0){
                    for (int i=1;i<=length;i++){
                        String ChildName=String.valueOf(i);
                        getValues(ChildName);

                    }




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        TextView Click =findViewById(R.id.click);

        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(contacts.this,district.class);
                startActivity(intent);
            }
        });






    }
    public void getValues(String ChildName){

        // Toast.makeText(getApplicationContext(),"Hallo worold",Toast.LENGTH_SHORT).show();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("CENTRAL STATES");
        DatabaseReference databaseReference1=databaseReference.child(ChildName);


        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String Name=snapshot.child("NAME OF STATE").getValue(String.class);
                String Slno=snapshot.child("SLNO").getValue(String.class);
                String help=snapshot.child("HELPLINE NO").getValue(String.class);
                my_list.add(new MyModel(Slno,Name,help));

                myAdapter=new MyAdapter(contacts.this, my_list, new MyAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(MyModel myModel) {
                        Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+myModel.getPhoneNo()));
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(myAdapter);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}