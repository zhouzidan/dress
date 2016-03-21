package com.zhou.dress.guideshow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.zhou.dress.R;

public class EntryFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, null);
        v.findViewById(R.id.btn_entry).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                GuideActivity activity = (GuideActivity) getActivity();
                activity.entryApp();
            }
        });
        return v;
    }
}
