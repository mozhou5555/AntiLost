package com.zzz.android.act;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.zzz.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private ActionBar actionBar;
	private ViewPager mViewPager;
	private List<Fragment> mTabPagerList = new ArrayList<Fragment>();
	private Toast toast;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
    }
    
    private void initWidget(){
    	actionBar = getSupportActionBar();
    	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("总览").setTabListener(new MyTabListener()));
        actionBar.addTab(actionBar.newTab().setText("时间").setTabListener(new MyTabListener()));
        
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mTabPagerList.add(new Fragment1());
		mTabPagerList.add(new Fragment2());
		mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTabPagerList));
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	//初始化actionbar
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	//actionbar点击事件
    	return super.onOptionsItemSelected(item);
    }
    
	//TAB切换监听事件
	private class MyTabListener implements TabListener{
		
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction arg1) {
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction arg1) {
			for (int i = 0; i < mTabPagerList.size(); i++) {
				if (i == tab.getPosition()) {
					mViewPager.setCurrentItem(i);
				}
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
			
		}
    }
	
	private class MyPagerAdapter extends FragmentPagerAdapter {
		List<Fragment> list;
		
		public MyPagerAdapter(FragmentManager fm, List<Fragment> list) {
			super(fm);
			this.list = list; 
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			return list.size();
		}
		
		
	}
	
	private class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int position) {
			actionBar.setSelectedNavigationItem(position);
		}
	}	
	
	private long exitTime = 0;  
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				toast = Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT);
				toast.show();
				exitTime = System.currentTimeMillis(); 
			}else{
				finish();
				toast.cancel();
			}
	    }else if(keyCode == KeyEvent.KEYCODE_MENU){
	    	
	    }
		return false;
	}
}
