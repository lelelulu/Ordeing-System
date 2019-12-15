package com.example.ordering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FoodShowActivity extends Activity {//结算
	private ListView listView_foods=null;
	private ImageView img_order_submit=null;
	private EditText food_amount=null;
	private EditText food_amount2=null;
	private EditText food_amount3=null;
	private EditText food_amount4=null;
	private EditText food_amount5=null;
	private TextView all_amount = null;
	private Button but=null;
	private int pos;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_show);//出现结算界面  麻辣香锅25 2 15个
		Intent intent = getIntent(); ;
		Bundle b=intent.getExtras();
		pos=b.getInt("pos");
		init();
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),R.layout.food_list,new String[]{"img_food","txt_food_title","txt_price"},
				new int[]{R.id.img_food,R.id.txt_food_title,R.id.txt_price});
		listView_foods.setAdapter(simpleAdapter);

	}

private void init(){
	listView_foods = (ListView) findViewById(R.id.listView_foods);
	food_amount=(EditText)findViewById(R.id.editText2);
	food_amount2=(EditText)findViewById(R.id.editText3);
	food_amount3=(EditText)findViewById(R.id.editText4);
	food_amount4=(EditText)findViewById(R.id.editText5);
	food_amount5=(EditText)findViewById(R.id.editText6);

	all_amount=(TextView)findViewById(R.id.all_amount);
	but=(Button)findViewById(R.id.button);

	img_order_submit = (ImageView) findViewById(R.id.img_order_submit);
	img_order_submit.setOnClickListener(listener);
	but.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			String total = food_amount.getText().toString();
			String total2 = food_amount2.getText().toString();
			String total3 = food_amount3.getText().toString();
			String total4 = food_amount4.getText().toString();
			String total5 = food_amount5.getText().toString();
			double num1 = Double.valueOf(total);
			double num2 = Double.valueOf(total2);
			double num3 = Double.valueOf(total3);
			double num4 = Double.valueOf(total4);
			double num5 = Double.valueOf(total5);
			double total1 = 15*num1+14*num2+16*num3+20*num4+17*num5;
			all_amount.setText(String.valueOf(total1));
		}
	});
}
	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			img_order_submit.setBackgroundResource(R.drawable.order_submited);
			String total = food_amount.getText().toString();
			String total2 = food_amount2.getText().toString();
			String total3 = food_amount3.getText().toString();
			String total4 = food_amount4.getText().toString();
			String total5 = food_amount5.getText().toString();
   			Bundle b =new Bundle();
			b.putString("food_amount", total);
			b.putString("food_amount2", total2);
			b.putString("food_amount3", total3);
			b.putString("food_amount4", total4);
			b.putString("food_amount5", total5);
            b.putInt("pos",pos);
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(),OrderDetailActivity.class);//传送意图至OrderDetailActivity界面即确定提交的界面
			intent.putExtras(b);
			startActivity(intent);
		}
	};

	private List<Map<String,Object>> getData(){
		List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		for(int i=0;i<1;i++){//可改列表数目
			map = new HashMap<String,Object>();
			map.put("img_food",R.drawable.shuizhuyu);
			map.put("txt_food_title","水煮鱼");
			map.put("txt_price","￥15");
			Map map2 = new HashMap<String,Object>();
			map2.put("img_food",R.drawable.mapodoufu);
			map2.put("txt_food_title","麻婆豆腐");
			map2.put("txt_price","14");
			Map map3 = new HashMap<String,Object>();
			map3.put("img_food",R.drawable.suancaiyu);
			map3.put("txt_food_title","酸菜鱼");
			map3.put("txt_price","16");
			Map map4 = new HashMap<String,Object>();
			map4.put("img_food",R.drawable.laziji);
			map4.put("txt_food_title","辣子鸡");
			map4.put("txt_price","20");
			Map map5 = new HashMap<String,Object>();
			map5.put("img_food",R.drawable.maoxuewang);
			map5.put("txt_food_title","毛血旺");
			map5.put("txt_price","17");
			lists.add(map);
			lists.add(map2);
			lists.add(map3);
			lists.add(map4);
			lists.add(map5);

		}
		return lists;
	}
}
