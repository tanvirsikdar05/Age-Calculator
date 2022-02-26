package com.smart_tech_nk.age_calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {

    private ArrayList<recyclerviewexdata> mexamplelist;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {

        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ExampleAdapter(ArrayList<recyclerviewexdata> examplelist) {
        this.mexamplelist = examplelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewdemol, parent, false);
        ViewHolder evh = new ViewHolder(v,mListener);
        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        recyclerviewexdata currentItem = mexamplelist.get(position);


        holder.profileimg.setImageBitmap(currentItem.getImagesrc());
        holder.tvname.setText(currentItem.getName());
        holder.tveventtype.setText(currentItem.getEventtype());

        holder.tveventdate.setText(currentItem.getEventdate());
        holder.tvage.setText(currentItem.getAge());
        holder.tvnextan.setText(currentItem.getNexteventtype());
        holder.tvnextbday.setText(currentItem.getNextdate());

    }

    @Override
    public int getItemCount() {
        return mexamplelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tveventtype,tveventdate,tvage,tvnextan,tvnextbday;
        CircleImageView profileimg;
        ImageView deleteitem;


        public ViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvNameMain);
            tveventtype=itemView.findViewById(R.id.tvEventType);
            tveventdate=itemView.findViewById(R.id.tvEventDate);
            tvage=itemView.findViewById(R.id.tvAge);
            tvnextan=itemView.findViewById(R.id.tvNextAniver);
            tvnextbday=itemView.findViewById(R.id.tvNextBDay);
            profileimg=itemView.findViewById(R.id.profileimageview);
            deleteitem=itemView.findViewById(R.id.ivDelete);

            deleteitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });
        }
    }
}
