package com.example.cohelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.Switch;
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


public class district extends AppCompatActivity {

    ProgressDialog dialog;
    DisAdapter disAdapter;
    DisModel disModel;
    int length=0;
    RecyclerView recyclerView;
    List<DisModel>DisList=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("KERALA COVID HELPLINE NUMBERS");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#018786")));
        setContentView(R.layout.activity_district);
        dialog = new ProgressDialog(this);
        dialog.setMessage("LOADING");

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_district);

        getValues();
        disAdapter=new DisAdapter(district.this, DisList, new DisAdapter.ItemClickListener() {
            @Override
            public void onItemClick(DisModel disModel) {
                ClickAndCall(disModel);
            }
        });
        recyclerView.setAdapter(disAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(district.this));
    }
    public void getValues(){
        String district="THIRUVANANTHAPURAM";
        String Cohelp="1075";
        String ambulance="108";
        String OxiRoom="101110022";
        DisList.add(new DisModel(district,Cohelp,ambulance,OxiRoom));

    }

    private void ClickAndCall(DisModel disModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setItems(new CharSequence[]{"HELPLINE", "AMBULENCE", "OXIGEN",}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0 :Intent help=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+disModel.getCohelp_No()));
                            startActivity(help);
                    case 1 :Intent amb=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+disModel.getAmb_No()));
                            startActivity(amb);
                    case 2 :Intent oxi=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+disModel.getOxi_No()));
                            startActivity(oxi);


            }
        }});
        builder.create();
        builder.show();
    }




}