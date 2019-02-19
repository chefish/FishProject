package com.fish.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fish.lib.fishlib.log.LogUtil;

public class Fragment1 extends Fragment {

    private Button btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.fish("onCreate 1");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.fish("onCreateView 1");
		return inflater.inflate(R.layout.fragment1, container, false);
	}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.fish("onActivityCreated 1");
        super.onActivityCreated(savedInstanceState);
        btn= (Button) getView().findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeToFragment2();
            }
        });
    }
}