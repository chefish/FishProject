package com.fish.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fish.lib.fishlib.log.LogUtil;

public class Fragment2 extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.fish("onCreate 2");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.fish("onCreateView 2");
		return inflater.inflate(R.layout.fragment2, container, false);
	}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.fish("onActivityCreated 2");
        super.onActivityCreated(savedInstanceState);
        Button btn= (Button) getView().findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeToFragment1();
            }
        });
    }
}