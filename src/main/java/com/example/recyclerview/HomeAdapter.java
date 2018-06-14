package com.example.recyclerview;

import android.content.Context;
import android.support.v7.widget.*;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mango on 2018/6/14.
 */

public class HomeAdapter extends android.support.v7.widget.RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<String > mList =new ArrayList<>();
    private Context mContext ;
    private OnItemClickListener mOnItemClickListener;


    public  HomeAdapter(Context mContext ,List<String> mList){
        this.mContext=mContext ;
        this.mList=mList ;
    }

    public void removeData(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public  MyViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        MyViewHolder holdeer =new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview ,parent,false)) ;
        return  holdeer ;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder ,final int position){
        holder.tv.setText(mList.get(position));
        if(mOnItemClickListener!=null ){
            holder.tv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos =holder.getLayoutPosition() ;
                    mOnItemClickListener.onItemClick(holder.tv,pos);
                }
            });
            holder.tv.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public  boolean onLongClick(View view){
                    int pos =holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.tv,pos);
                    return false ;
                }
            });
        }
    }

    public  int getItemCount(){
        return mList.size() ;
    }


    class MyViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        TextView tv ;
        public  MyViewHolder(View view ){
            super(view);
            tv=(TextView)view.findViewById(R.id.tv_item);
        }
    }

    //以下为为Adapter自定义点击事件

    public interface  OnItemClickListener {
        void onItemClick(View view ,int position ) ;
        void onItemLongClick(View view ,int position) ;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener ;
    }
}
