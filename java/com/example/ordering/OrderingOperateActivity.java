package com.example.ordering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class OrderingOperateActivity extends Activity {
	private GridView gv_table;
	private int pos=0;//记录几号桌
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ordering_operate);
		init();
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),R.layout.gv,new String[]{"img","txt"},new int[]{R.id.img_table,R.id.txt_table});
		//桌子的图片以及桌号
		gv_table.setAdapter(simpleAdapter);
		gv_table.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				position++;
				if(position!=5) {
					Toast.makeText(OrderingOperateActivity.this, "您选中了" + position + "号桌", Toast.LENGTH_SHORT).show();
					pos = position;
					Bundle b = new Bundle();
					b.putInt("pos", pos);
					Intent intent = new Intent();
					intent.setClass(OrderingOperateActivity.this, FoodShowActivity.class);//显示选中了几号桌并跳转到FoodShow界面
					intent.putExtras(b);
					startActivity(intent);
				}
				else Toast.makeText(OrderingOperateActivity.this, "此桌已被预订！", Toast.LENGTH_SHORT).show();
			}
		});
	}
	private void init(){
		gv_table = (GridView)findViewById(R.id.gv_table);

	}
	private List<Map<String,Object>> getData(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		for(int i=1;i<8;i++){
			map = new HashMap<String,Object>();
			if(i==5){
				map.put("img",R.drawable.ordered_table_pic);//已点桌位
				map.put("txt",i+"号桌(已被预订)");//桌号递增
			}else{
				map.put("img",R.drawable.ordering_table);//可点桌位
				map.put("txt",i+"号桌");//桌号递增
			}
			list.add(map);
		}
		return list;
	}

}
