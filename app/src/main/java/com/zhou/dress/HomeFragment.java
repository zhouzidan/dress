package com.zhou.dress;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhou.dress.adapter.HomeListAdapter;
import com.zhou.dress.model.MsgModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhou on 16-3-8.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeListAdapter adapter;
    private List<MsgModel> mDatas = null;
private String TAG = "HomeFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home,null);
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    private void initView(){
        listView = (ListView) (getView().findViewById(R.id.id_listview));
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.id_swipe_ly);
        swipeRefreshLayout.setOnRefreshListener(this);
        mDatas = getMsgModels();
        adapter = new HomeListAdapter(getActivity(),mDatas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"position:"+position);
            }
        });
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(0, 500);
    }

    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg){
            swipeRefreshLayout.setRefreshing(false);
            if (msg.what == 0){
                mDatas.addAll(getMsgModels());
                adapter.notifyDataSetChanged();
            }
        }
    };


    private List<MsgModel> getMsgModels(){
        List<MsgModel> msgModels = new ArrayList<>();
        Log.d(TAG,"开始：I:0");
        for (int i = 0 ; i < 20 ; i++){
            Log.d(TAG,"I:"+i);
            MsgModel msgModel = new MsgModel();
            msgModel.setFavouriteCount(i*3);
            msgModel.setTitle("我是标题，粗体i:"+i);
            msgModel.setSubtitle("我是副标题，很多人以为我是简介i:"+i);
            msgModel.setTimestamp(System.currentTimeMillis());
            msgModel.setImgUrl("http://img2.3lian.com/2014/f5/158/d/86.jpg");
            msgModels.add(msgModel);
        }

        Log.d(TAG,"size:"+msgModels.size());
        return  msgModels;
    }

}
