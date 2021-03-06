package com.example.ifsc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private final HashSet<String> cat;
    private static ArrayList<String> myData;
    private OnItemClickedListener mListener;

    public interface OnItemClickedListener {
        void onItemClicked(int pos, String item);
    }
    public void setOnItemClickedListener(OnItemClickedListener listener){
        mListener = listener;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_category_name;

        public MyViewHolder(@NonNull View itemView, OnItemClickedListener listener) {
            super(itemView);
            tv_category_name = itemView.findViewById(R.id.category_name_id);

            itemView.setOnClickListener(v -> {
                if (listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClicked(position, myData.get(position));
                    }
                }
            });
        }
    }
    /*
    public CategoryAdapter(List<Category> mData) {
        this.mData = mData;
    }*/
    /*public CategoryAdapter(Map<String,String> myData) {
        this.myData = myData;
    }*/

    public CategoryAdapter(HashSet<String> cat) {
        this.cat = cat;
    }
    public void hashConverter(){
        myData = new ArrayList<>();
        //no lugar do foreach
        myData.addAll(cat);
        //Collections.sort(test);
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
        hashConverter();
        holder.tv_category_name.setText(myData.get(position));
    }

    @Override
    public int getItemCount() {
        return cat.size();
    }
}
