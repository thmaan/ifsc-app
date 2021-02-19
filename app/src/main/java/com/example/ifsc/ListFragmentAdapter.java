package com.example.ifsc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.MyViewHolder> {
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
        TextView tv_date;

        public MyViewHolder(@NonNull View itemView, OnItemClickedListener listener) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.news_title);
            tv_date = itemView.findViewById(R.id.news_published_date);
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
    public ListFragmentAdapter (List<News> myData){
        this.myData = myData;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news_item, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFragmentAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(myData.get(position).getTitle());
        holder.tv_description.setText(myData.get(position).getDescription());
        holder.tv_date.setText((myData.get(position).getPublished()));

    }

    @Override
    public int getItemCount() {
        return myData.size();
    }
}
