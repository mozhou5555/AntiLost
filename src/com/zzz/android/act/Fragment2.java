package com.zzz.android.act;

import com.zzz.android.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment2 extends Fragment{
	private View fragment2;
	private Button btnTp;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragment2 = inflater.inflate(R.layout.fragment2, container, false);
		initWidget();
		initLogic();
		return fragment2;
	}
	
	private void initWidget(){
		btnTp = (Button) fragment2.findViewById(R.id.fragment2_btn_tp);
	}
	
	private void initLogic(){
        
		btnTp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), TimeSetActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
