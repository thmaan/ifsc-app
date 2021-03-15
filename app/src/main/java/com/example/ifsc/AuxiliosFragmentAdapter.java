package com.example.ifsc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AuxiliosFragmentAdapter extends RecyclerView.Adapter<AuxiliosFragmentAdapter.MyViewHolder> {
    private static List<News> myData;
    private OnItemClickedListener mListener;

    public interface OnItemClickedListener {
        void onItemClicked(int pos, String item);
    }
    public void setOnItemClickedListener(OnItemClickedListener listener){
        mListener = listener;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        TextView tv_description;

        public MyViewHolder(@NonNull View itemView, OnItemClickedListener listener) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.news_title);
            tv_description = itemView.findViewById(R.id.news_description);

            itemView.setOnClickListener(v -> {
                if (listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClicked(position,myData.get(position).getTitle());

                    }
                }
            });
        }
    }
    public AuxiliosFragmentAdapter(List<News> myData){
        this.myData = myData;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_auxilios, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AuxiliosFragmentAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(myData.get(position).getTitle());
        holder.tv_description.setText(myData.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return myData.size();
    }
}
