package com.example.omak.keystoreexample;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omak.keystoreexample.databinding.ListItemBinding;

import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomHolder>{

    private List<Model> mList;
    private Context mContext;
    public CustomAdapter(Context context,List<Model> models){
        this.mList = models;
        this.mContext = context;
    }
    public void setList(List<Model> models){
        this.mList = models;
    }
    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ListItemBinding b  = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.list_item,parent,false);


        return new CustomHolder(b);
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        Model model = mList.get(position);
        holder.b.tvMessage.setText(model.getMessage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder{
        ListItemBinding b;
        public CustomHolder(ListItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }
    }
}
