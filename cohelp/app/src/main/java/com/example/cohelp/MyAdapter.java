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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    List<MyModel>my_list;
    private ItemClickListener mitemClickListener;

    public MyAdapter(Context context,List<MyModel> my_list,ItemClickListener itemClickListener) {
        this.context = context;
        this.my_list = my_list;
        this.mitemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i(TAG,"THIS IS RUNNIMF");

        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        MyModel myModel=my_list.get(position);
        holder.text_slno.setText(myModel.getSlno());
        holder.text_name.setText(myModel.getNameOfState());
        holder.text_phone.setText(myModel.getPhoneNo());

        holder.itemView.setOnClickListener(view ->{
            mitemClickListener.onItemClick(my_list.get(position));
        });


    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }

    public interface ItemClickListener{
        void  onItemClick(MyModel myModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_slno,text_name,text_phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_slno = itemView.findViewById(R.id.item_slno);
            text_name = itemView.findViewById(R.id.item_NameOfState);
            text_phone = itemView.findViewById(R.id.item_PhoneNo);
            Log.i(TAG,"hallo");
        }
    }


}

