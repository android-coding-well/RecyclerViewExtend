package com.gosuncn.rved;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gosuncn.rve.RecyclerViewExtend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuWeiJian on 2017/12/20.
 */

public class ListAdapter extends RecyclerViewExtend.Adapter<ListAdapter.MyViewHolder> {

    private List<String> list = new ArrayList<String>();
    private Context context;
    public ListAdapter(Context context){
        this.context= context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(android.R.layout.activity_list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text.setText(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<String> getList(){
        return list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        public MyViewHolder(View itemView) {
            super(itemView);
            text=itemView.findViewById(android.R.id.text1);

        }
    }
}
