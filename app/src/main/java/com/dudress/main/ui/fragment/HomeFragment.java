package com.dudress.main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dudress.main.R;
import com.dudress.main.ui.adapter.HomeListAdapter;
import com.dudress.main.db.models.Entry;
import com.dudress.main.ui.activities.PageActivity;
import com.dudress.main.bean.MsgModel;
import com.dudress.main.util.NetUtils;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhou on 16-3-8.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeListAdapter adapter;
    private List<Entry> entryList = new ArrayList<>();
    private String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = (ListView) (view.findViewById(R.id.id_listview));
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new HomeListAdapter(getActivity(), entryList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "position:" + position);
                Intent mIntent = new Intent(getActivity(), PageActivity.class);
                startActivity(mIntent);
            }
        });
        getLeastEntryList();
    }

    @Override
    public void onRefresh() {
        getMoreEntryList();
    }

    private List<MsgModel> getMsgModels() {
        List<MsgModel> msgModels = new ArrayList<>();
        Log.d(TAG, "开始：I:0");
        for (int i = 0; i < 20; i++) {
            Log.d(TAG, "I:" + i);
            MsgModel msgModel = new MsgModel();
            msgModel.setFavouriteCount(i * 3);
            msgModel.setTitle("我是标题，粗体i:" + i);
            msgModel.setSubtitle("我是副标题，很多人以为我是简介i:" + i);
            msgModel.setTimestamp(System.currentTimeMillis());
            msgModel.setImgUrl("http://img2.3lian.com/2014/f5/158/d/86.jpg");
            msgModels.add(msgModel);
        }

        Log.d(TAG, "size:" + msgModels.size());
        return msgModels;
    }

    //加载最新的数据
    private void getLeastEntryList() {
        if (entryList.size() == 0){
            //GET DATA FROM DB
            List<Entry> entries = Entry.find(Entry.class,null,null,null," sendid desc ","50");
            if (entries.size() > 0){
                entryList.addAll(entries);
                adapter.notifyDataSetChanged();
            }
        }
        long sendid = 0 ;
        if (entryList.size() > 0){
            sendid = entryList.get(0).getSendid();
        }
        RequestParams requestParams = new RequestParams();
        requestParams.add("key", "value");
        requestParams.add("type", "更新的");//TODO
        requestParams.add("EntryId", String.valueOf(sendid));
        NetUtils.post("login.php", requestParams, leastEntryResponseHandler);
    }

    //加载更多的数据
    private void getMoreEntryList() {
        String where = null;
        long sendid = 0 ;
        if (entryList.size() > 0) {
            sendid = entryList.get(entryList.size() - 1).getSendid();
            where = " sendid < " + sendid;
        }
        List<Entry> entries = Entry.find(Entry.class, where, null, null," sendid desc ", "50");
        if (entries.size() > 0) {
            entryList.addAll(entries);
            adapter.notifyDataSetChanged();
        } else {
            RequestParams requestParams = new RequestParams();
            requestParams.add("key", "value");
            requestParams.add("type", "更久的");//TODO
            requestParams.add("EntryId", String.valueOf(sendid));
            NetUtils.post("login.php", requestParams, moreEntryResponseHandler);
        }
    }

    //从网络接口的返回值JSONObject中转化成entry
    private List<Entry> getEntryList(JSONObject response){
        List<Entry> entries = new ArrayList<>();
        //TODO
        return  entries;
    }

    JsonHttpResponseHandler moreEntryResponseHandler = new JsonHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            List<Entry> entries = getEntryList(response) ;
            entryList.addAll(entries);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onFinish() {
            super.onFinish();
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
        }
    };

    JsonHttpResponseHandler leastEntryResponseHandler = new JsonHttpResponseHandler(){
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            List<Entry> entries = getEntryList(response) ;
            entryList.addAll(0,entries);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onFinish() {
            super.onFinish();
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
        }
    };

}
