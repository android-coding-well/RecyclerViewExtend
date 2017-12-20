package com.gosuncn.rved;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gosuncn.rve.RecyclerViewExtend;
import com.gosuncn.rved.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvList.setAdapter(adapter=new ListAdapter(this));
        binding.rvList.setLoadingMode(RecyclerViewExtend.LOADINGMODE_SLIDE_TO_BOTTOM_AND_PULL_UP);
        binding.rvList.setEmptyView(binding.tvEmpty);
        binding.srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(adapter.getList().size()==0){
                            for(int i=0;i<30;i++){
                                adapter.getList().add("item"+i);
                            }
                        }else{
                            adapter.getList().clear();
                        }

                        updateView();
                    }
                }).start();

            }
        });
        binding.rvList.setOnLoadingListener(new RecyclerViewExtend.OnLoadingListener() {
            @Override
            public void onLoading() {
                Toast.makeText(MainActivity.this, "正在加载。。。", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for(int i=0;i<5;i++){
                            adapter.getList().add("load item"+i);
                        }
                        updateView2();
                    }
                }).start();
            }
        });
    }


    public void updateView(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                binding.srlRefresh.setRefreshing(false);
            }
        });
    }

    public void updateView2(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "加载完毕", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                binding.rvList.setLoading(false);
            }
        });
    }

}
