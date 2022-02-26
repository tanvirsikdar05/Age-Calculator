package com.smart_tech_nk.age_calculator;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    private OnItemClickListener2 mListener;

    public interface OnItemClickListener2 {

        void oncopyclick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener2 listener) {
        mListener = listener;
    }

    private String[] data;
    public MyAdapter(String[] data) {
        this.data=data;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row,parent,false);
        return new MyViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final String mydata=data[position];
        holder.textView.setText(mydata);

        holder.button.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,mydata);
            intent.setType("text/plain");
            intent=Intent.createChooser(intent,"share by");
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        Button button,copy;

        public MyViewHolder(@NonNull View itemView,final OnItemClickListener2 listener) {
            super(itemView);
            textView=itemView.findViewById(R.id.txt);
            button=itemView.findViewById(R.id.share_btn);
            copy=itemView.findViewById(R.id.copy);

            copy.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.oncopyclick(position);
                }
            });

        }
    }
}

