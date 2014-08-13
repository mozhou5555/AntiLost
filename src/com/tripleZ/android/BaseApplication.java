package com.tripleZ.android;

import android.app.Application;

public class BaseApplication extends Application{
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
}
