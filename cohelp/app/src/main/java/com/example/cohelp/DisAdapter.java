package com.example.cohelp;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class DisAdapter extends RecyclerView.Adapter<DisAdapter.ViewHolder> {

    Context context;
    List<DisModel>dis_list;
    private ItemClickListener mitemClickListener;

    public DisAdapter(Context context,List<DisModel> dis_list,ItemClickListener itemClickListener) {
        this.context = context;
        this.dis_list = dis_list;
        this.mitemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public DisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i(TAG,"THIS IS RUNNIMF");

        View view = LayoutInflater.from(context).inflate(R.layout.disitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisAdapter.ViewHolder holder, int position) {

        DisModel disModel=dis_list.get(position);
        holder.DistrictName.setText(disModel.getDist_Name());
        holder.Cohepline.setText(disModel.getCohelp_No());
        holder.AmbulaneNo.setText(disModel.getAmb_No());
        holder.OxiRoom.setText(disModel.getOxi_No());

        holder.itemView.setOnClickListener(view ->{
            mitemClickListener.onItemClick(dis_list.get(position));
        });


    }

    @Override
    public int getItemCount() {
        return dis_list.size();
    }

    public interface ItemClickListener{
        void  onItemClick(DisModel disModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView DistrictName,Cohepline,AmbulaneNo,OxiRoom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DistrictName=itemView.findViewById(R.id.dist);
            Cohepline = itemView.findViewById(R.id.help);
            AmbulaneNo = itemView.findViewById(R.id.Amb);
            OxiRoom = itemView.findViewById(R.id.Oxi);
            Log.i(TAG,"hallo");
        }
    }


}