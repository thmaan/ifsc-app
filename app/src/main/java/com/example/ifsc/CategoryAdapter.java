package com.example.ifsc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private List<Category> mData;
    private OnItemClickedListener mListener;

    public interface OnItemClickedListener {
        void onItemClicked(int pos);
    }
    public void setOnItemClickedListener(OnItemClickedListener listener){
        mListener = listener;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_category_name;

        public MyViewHolder(@NonNull View itemView, OnItemClickedListener listener) {
            super(itemView);
            tv_category_name = itemView.findViewById(R.id.category_name_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClicked(position);
                        }
                    }
                }
            });
        }
    }

    public CategoryAdapter(List<Category> mData) {
        this.mData = mData;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_category, parent,false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_category_name.setText(mData.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
