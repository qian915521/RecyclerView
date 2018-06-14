package com.example.recyclerview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView extends AppCompatActivity {
    private HomeAdapter homeAdapter ;
    private List<String> mList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        for(int i =0 ;i<20;i++){
            mList.add("这是第"+String.valueOf(i)+"个列表项");
        }
        android.support.v7.widget.RecyclerView recyclerView=(android.support.v7.widget.RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置横向Item布局  recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //设置网格布局      recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //设置瀑布流        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        homeAdapter=new HomeAdapter(this,mList);
        recyclerView.setAdapter(homeAdapter);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(RecyclerView.this, DividerItemDecoration.VERTICAL));
        //recyclerView.addItemDecoration(new RecycleViewDivider(RecyclerView.this,LinearLayoutManager.VERTICAL));
        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                Toast.makeText(RecyclerView.this,"点击第"+position+"条",Toast.LENGTH_SHORT).show();
            }
            @Override
            public  void onItemLongClick(View view ,final int position){
                new AlertDialog.Builder(RecyclerView.this)
                        .setTitle("确认删除吗？")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new  DialogInterface.OnClickListener( ){
                            @Override
                            public  void onClick(DialogInterface dialogInterface , int i){
                                homeAdapter.removeData(position);
                            }
                        }).show() ;
            }
        });
    }
}
