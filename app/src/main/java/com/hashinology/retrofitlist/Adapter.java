package com.hashinology.retrofitlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashinology.retrofitlist.model.ModelList;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Myviews> {
    private List<ModelList> modelLists;
    private LayoutInflater mInflater;
    private AdapterInterface adapterInterface;

    public Adapter(List<ModelList> modelLists, Context context) {
        this.modelLists = modelLists;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public Myviews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.retrofit_list, parent, false);
        return new Myviews(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviews holder, int position) {
        ModelList objectList = modelLists.get(position);
        holder.tvUSerID.setText(String.valueOf(objectList.getUserID()));
        holder.tvID.setText(String.valueOf(objectList.getId()));
        holder.tvTitle.setText(objectList.getTitle());
        holder.tvBody.setText(objectList.getBody());
    }

    @Override
    public int getItemCount() {
        return modelLists.size();
    }

    public class Myviews extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvUSerID, tvID, tvTitle, tvBody;
        public Myviews(@NonNull View itemView) {
            super(itemView);
            tvUSerID = itemView.findViewById(R.id.tvUserId);
            tvID = itemView.findViewById(R.id.tvID);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (adapterInterface != null){
                adapterInterface.myInterfaceClick(view, getAdapterPosition());
            }
        }
    }
    public void getMyInterface(AdapterInterface adapterInterface){
        this.adapterInterface = adapterInterface;
    }
}
