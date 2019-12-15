package com.example.ordering;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pb.fragment.MainFragment;
import com.pb.fragment.MyFragment;
import com.pb.fragment.OrderFragment;
import com.pb.fragment.TrendsFragment;

public class MainActivity extends FragmentActivity {
	private LinearLayout main_layout;
	private LinearLayout order_layout;
	private LinearLayout trends_layout;
	private LinearLayout my_layout;
	private ImageView main_img;
	private ImageView order_img;
	private ImageView trends_img;
	private ImageView my_img;
	private TextView main_txt;
	private TextView order_txt;
	private TextView trends_txt;
	private TextView my_txt;
	private Fragment mainFragment;
	private Fragment orderFragment;
	private Fragment trendsFragment;
	private Fragment myFragment;
	private FragmentManager fragmentManager;
	private FragmentTransaction trans;
	private static long INTERVAL = 2000;   //两次返回键间隔最大值常量
	private long mFirstBackKeyPressTime = -1; //第一次按下返回键的时间
	private long mLastBackKeyPressTime = -1; //第一次按下返回键的时间
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		//实例管理对象
		fragmentManager = this.getSupportFragmentManager();
		//设置默认
		setTabSelection(0);

	}
	// 按"返回键"两次即退出应用程序
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(mFirstBackKeyPressTime == -1){
			mFirstBackKeyPressTime = System.currentTimeMillis();
			Toast.makeText(MainActivity.this, "再按一下退出程序", Toast.LENGTH_LONG).show();
		}else{
			mLastBackKeyPressTime = System.currentTimeMillis();
			if((mLastBackKeyPressTime-mFirstBackKeyPressTime) <= INTERVAL){
				finish();
				System.exit(0);
				super.onBackPressed();
			}else{
				mFirstBackKeyPressTime = System.currentTimeMillis();
				Toast.makeText(MainActivity.this, "再按一下退出程序", Toast.LENGTH_LONG).show();
			}
		}
	}
	private void initView(){
		main_layout = (LinearLayout) findViewById(R.id.linear_main);
		main_layout.setOnClickListener(listener);
		order_layout = (LinearLayout) findViewById(R.id.linear_order);
		order_layout.setOnClickListener(listener);
		trends_layout = (LinearLayout) findViewById(R.id.linear_trends);
		trends_layout.setOnClickListener(listener);
		my_layout = (LinearLayout) findViewById(R.id.linear_my);
		my_layout.setOnClickListener(listener);
		main_img = (ImageView) findViewById(R.id.img_main);
		order_img = (ImageView) findViewById(R.id.img_order);
		trends_img = (ImageView) findViewById(R.id.img_trends);
		my_img = (ImageView) findViewById(R.id.img_my);
		main_txt = (TextView) findViewById(R.id.txt_main);
		order_txt = (TextView) findViewById(R.id.txt_order);
		trends_txt = (TextView) findViewById(R.id.txt_trends);
		my_txt = (TextView) findViewById(R.id.txt_my);
	}
	View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.linear_main:
				setTabSelection(0);
				break;
			case R.id.linear_order:
				setTabSelection(1);
				break;
			case R.id.linear_trends:
				setTabSelection(2);
				break;
			case R.id.linear_my:
				setTabSelection(3);
				break;
			default:
				break;
			}
		}
	};
	private void setTabSelection(int index){
		//清空之前的选择，都从第一个开始
		closeAllImageView();
		//开启事务
		trans = fragmentManager.beginTransaction();
		//先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况 
		hideFragments(trans);
		switch (index) {
		case 0:
			//首页
			main_img.setImageResource(R.drawable.main_selected);
			main_txt.setTextColor(Color.GREEN);
			if(mainFragment==null){
				mainFragment = new MainFragment();//如果是空的的话，就重新创一个
				trans.add(R.id.content,mainFragment);
			}else{
				trans.show(mainFragment);//否则直接显示出来
			}
			break;
		case 1:
			//订单
			order_img.setImageResource(R.drawable.order_selected);
			order_txt.setTextColor(Color.GREEN);
			if(orderFragment==null){
				orderFragment = new OrderFragment();
				trans.add(R.id.content,orderFragment);
			}else{
				trans.show(orderFragment);
			}
			break;
		case 2:
			//动态
			trends_img.setImageResource(R.drawable.trends_selected);
			trends_txt.setTextColor(Color.GREEN);
			if(trendsFragment==null){
				trendsFragment = new TrendsFragment();
				trans.add(R.id.content,trendsFragment);
			}else{
				trans.show(trendsFragment);
			}
			break;
		case 3:
			//我的
			my_img.setImageResource(R.drawable.my_selected);
			my_txt.setTextColor(Color.GREEN);
			if(myFragment==null){
				myFragment = new MyFragment();
				trans.add(R.id.content,myFragment);
			}else{
				trans.show(myFragment);
			}
			break;
		default:
			break;
		}
		trans.commit();//提交事务
	}
	//关闭所有的ImageView(图标变暗)
	private void closeAllImageView(){
		main_img.setImageResource(R.drawable.main_unselected);
		main_txt.setTextColor(Color.BLACK);
		order_img.setImageResource(R.drawable.order_unselected);
		order_txt.setTextColor(Color.BLACK);
		trends_img.setImageResource(R.drawable.trends_unselected);
		trends_txt.setTextColor(Color.BLACK);
		my_img.setImageResource(R.drawable.my_unselected);
		my_txt.setTextColor(Color.BLACK);
	}
	private void hideFragments(FragmentTransaction trans){
		if(mainFragment!=null){//如果不为空，就将此界面隐藏起来
			trans.hide(mainFragment);
		}
		if(orderFragment!=null){
			trans.hide(orderFragment);
		}
		if(trendsFragment!=null){
			trans.hide(trendsFragment);
		}
		if(myFragment!=null){
			trans.hide(myFragment);
		}
	}
}
