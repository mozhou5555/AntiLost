package com.zzz.android.act;

import com.zzz.android.R;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class SettingActionProvider extends ActionProvider{
	public SettingActionProvider(Context context) {
		super(context);
	}

	@Override
	public View onCreateActionView() {
		return null;
	}
	
	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		super.onPrepareSubMenu(subMenu);
		subMenu.clear(); 
		subMenu.add(getContext().getResources().getString(R.string.setting)).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
//				Intent intent = new Intent(getContext(), AboutActivity.class);
//				getContext().startActivity(intent);
				return false;
			}
		});
		
		subMenu.add(getContext().getResources().getString(R.string.about)).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent intent = new Intent(getContext(), AboutActivity.class);
				getContext().startActivity(intent);
				return false;
			}
		});
	}
	
	 @Override  
	 public boolean hasSubMenu() {  
		 return true;  
	 }
}
