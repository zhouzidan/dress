package com.dudress.main.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dudress.main.db.models.Entry;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.dudress.main.R;
import com.dudress.main.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhou on 16-3-10.
 */
public class HomeListAdapter extends BaseAdapter {
    private String TAG = "HomeListAdapter";
    private Context mContext;
    private List<Entry> entries;
    private LayoutInflater mInflater; //得到一个LayoutInfalter对象用来导入布局
    private List<Long> firstModelPositions = new ArrayList<>();

    //    private View view1 , view2 ;
 /*   public HomeListAdapter(Context mContext, List<MsgModel> msgModels) {
        this.mContext = mContext;
        this.msgModels = msgModels;
        mInflater = LayoutInflater.from(mContext);
        InitFirstModelPositions();
        Log.d(TAG, "msgModels.size():" + msgModels.size());
        Log.d(TAG, "firstModelPositions.size():" + firstModelPositions.size());
    }*/

    public HomeListAdapter(Context mContext, List<Entry> entries){
        this.mContext = mContext;
        this.entries = entries;
        mInflater = LayoutInflater.from(mContext);
        InitFirstModelPositions();
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Entry getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler;
        if (convertView != null) {
            Long tempPosition = (Long) convertView.getTag(R.id.tag_home_adapter);
            if (!firstModelPositions.contains(tempPosition) && firstModelPositions.contains(Long.valueOf(position))) {
                convertView = mInflater.inflate(R.layout.listitem_home_1, null);
                viewHoler = new ViewHoler();
                viewHoler.subTextView = (TextView) convertView.findViewById(R.id.tv_subtitle);
                viewHoler.titleTextView = (TextView) convertView.findViewById(R.id.tv_title);
                viewHoler.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                viewHoler.timeTextView = (TextView) convertView.findViewById(R.id.tv_time);
                convertView.setTag(viewHoler);
                convertView.setTag(R.id.tag_home_adapter, Long.valueOf(position));
            } else if (firstModelPositions.contains(tempPosition) && !firstModelPositions.contains(Long.valueOf(position))) {
                convertView = mInflater.inflate(R.layout.listitem_home, null);
                viewHoler = new ViewHoler();
                viewHoler.favouriteTextView = (TextView) convertView.findViewById(R.id.tv_faourite);
                viewHoler.subTextView = (TextView) convertView.findViewById(R.id.tv_subtitle);
                viewHoler.titleTextView = (TextView) convertView.findViewById(R.id.tv_title);
                viewHoler.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(viewHoler);
                convertView.setTag(R.id.tag_home_adapter, Long.valueOf(position));
            }else{
                Log.d(TAG,"78到了这里position："+position);
            }
        }
        if (convertView == null && !firstModelPositions.contains(Long.valueOf(position))) {
            convertView = mInflater.inflate(R.layout.listitem_home, null);
            viewHoler = new ViewHoler();
            viewHoler.favouriteTextView = (TextView) convertView.findViewById(R.id.tv_faourite);
            viewHoler.subTextView = (TextView) convertView.findViewById(R.id.tv_subtitle);
            viewHoler.titleTextView = (TextView) convertView.findViewById(R.id.tv_title);
            viewHoler.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHoler);
            convertView.setTag(R.id.tag_home_adapter, Long.valueOf(position));
        } else if (convertView == null && firstModelPositions.contains(Long.valueOf(position))) {
            convertView = mInflater.inflate(R.layout.listitem_home_1, null);
            viewHoler = new ViewHoler();
            viewHoler.subTextView = (TextView) convertView.findViewById(R.id.tv_subtitle);
            viewHoler.titleTextView = (TextView) convertView.findViewById(R.id.tv_title);
            viewHoler.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHoler.timeTextView = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHoler);
            convertView.setTag(R.id.tag_home_adapter, Long.valueOf(position));
        }else{
            Log.d(TAG,"99到了这里position："+position);
        }

        if (convertView != null) {
            viewHoler = (ViewHoler) convertView.getTag();
            Entry entry = entries.get(position);
            viewHoler.titleTextView.setText(entry.getTitle());
            viewHoler.subTextView.setText(entry.getSubtitle());
            if (viewHoler.timeTextView != null)
                viewHoler.timeTextView.setText(getDateText(entry.getSendtime()));
            if (viewHoler.favouriteTextView != null)
                viewHoler.favouriteTextView.setText(String.format(mContext.getResources().getString(R.string.count_of_people_love_it), entry.getLoves()));
            ImageLoader.getInstance().displayImage(entry.getPicurl(), viewHoler.imageView);
        }
        return convertView;
    }

    private void InitFirstModelPositions() {
        long temp = 0;
        for (int position = 0; position < entries.size(); position++) {
            Entry entry = entries.get(position);
            if (temp != 0) {
                boolean inSameDay = TimeUtils.isInSameDay(temp, entry.getSendtime());
                if (inSameDay == false)
                    firstModelPositions.add(Long.valueOf(position));
            } else {
                firstModelPositions.add(Long.valueOf(position));
            }
            temp = entry.getSendtime();
        }
    }

    private String getDateText(long timestamp){
        String format = "yyyy.MM.dd";
        StringBuffer stringBuffer = new StringBuffer(TimeUtils.getFormatDate(format,timestamp));
        stringBuffer.append(" ");
        int weekCount = TimeUtils.getDaysOfWeek(timestamp);
        String[] weeks = mContext.getResources().getStringArray(R.array.week);
        stringBuffer.append(weeks[weekCount - 1]);
        return stringBuffer.toString();
    }


    class ViewHoler {
        ImageView imageView;
        TextView titleTextView;
        TextView subTextView;
        TextView favouriteTextView;
        TextView timeTextView;
    }


}
