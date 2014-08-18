package com.tripleZ.android.act;

import com.tripleZ.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment1 extends Fragment{
	private View fragment1;
	private Button btnTest;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragment1 = inflater.inflate(R.layout.fragment1, container, false);
		btnTest = (Button) fragment1.findViewById(R.id.fragment1_btn_test);
		btnTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "ssssssss", Toast.LENGTH_SHORT).show();
			}
		});
		
		return fragment1;
	}
}
