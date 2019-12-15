package com.example.ordering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OrderDetailActivity extends Activity {//订单详情界面
	private ListView listview_order_detail;
	private ImageView img_oder_submit;
	private TextView food_amount_txt;
	private TextView food_amount_txt2;
	private TextView food_amount_txt3;
	private TextView food_amount_txt4;
	private TextView food_amount_txt5;
	private TextView total_amount1;
	private TextView total_amount2;
	private TextView total_amount3;
	private TextView total_amount4;
	private TextView total_amount5;

	private TextView alltotal;
	private TextView pos1;
	private EditText time1=null;
	double total;

	String food_amount;
	String food_amount2;
	String food_amount3;
	String food_amount4;
	String food_amount5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		init();
		food_amount_txt=(TextView)findViewById(R.id.food_amount);
		food_amount_txt2=(TextView)findViewById(R.id.food_amount2);
		food_amount_txt3=(TextView)findViewById(R.id.food_amount3);
		food_amount_txt4=(TextView)findViewById(R.id.food_amount4);
		food_amount_txt5=(TextView)findViewById(R.id.food_amount5);

		total_amount1=(TextView)findViewById(R.id.totalamount1);
		total_amount2=(TextView)findViewById(R.id.totalamount2);
		total_amount3=(TextView)findViewById(R.id.totalamount3);
		total_amount4=(TextView)findViewById(R.id.totalamount4);
		total_amount5=(TextView)findViewById(R.id.totalamount5);

		time1=(EditText)findViewById(R.id.time);
		alltotal=(TextView)findViewById(R.id.txt_orderdetail_total);
		pos1=(TextView)findViewById(R.id.pos);


		food_amount_txt.setText("5");
		Intent intent = getIntent(); ;
		Bundle b=intent.getExtras();
		food_amount=b.getString("food_amount");
		food_amount2=b.getString("food_amount2");
		food_amount3=b.getString("food_amount3");
		food_amount4=b.getString("food_amount4");
		food_amount5=b.getString("food_amount5");
		int pos = b.getInt("pos");
		food_amount_txt.setText(food_amount);
		food_amount_txt2.setText(food_amount2);
		food_amount_txt3.setText(food_amount3);
		food_amount_txt4.setText(food_amount4);
		food_amount_txt5.setText(food_amount5);
        pos1.setText(String.valueOf(pos));

		double num1=Double.valueOf(food_amount);
		num1=15*num1;
		double num2=Double.valueOf(food_amount2);
		num2=14*num2;
		double num3=Double.valueOf(food_amount3);
		num3=16*num3;
		double num4=Double.valueOf(food_amount4);
		num4=20*num4;
		double num5=Double.valueOf(food_amount5);
		num5=17*num5;
		total_amount1.setText(String.valueOf(num1));
		total_amount2.setText(String.valueOf(num2));
		total_amount3.setText(String.valueOf(num3));
		total_amount4.setText(String.valueOf(num4));
		total_amount5.setText(String.valueOf(num5));

		total=num1+num2+num3+num4+num5;
        alltotal.setText(String.valueOf(total));

	}
	private void init(){
		listview_order_detail = (ListView) findViewById(R.id.listview_order_detail);
		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
				getData(),R.layout.order_detail_listview,
				new String[]{"food_name","food_price"},
				new int[]{R.id.txt_food_name,R.id.txt_food_price});
		listview_order_detail.setAdapter(adapter);
		img_oder_submit = (ImageView) findViewById(R.id.img_oder_submit);
		img_oder_submit.setOnClickListener(listener);
	}
	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			img_oder_submit.setBackgroundResource(R.drawable.submited_background);
			Toast.makeText(getApplicationContext(),"订单已提交！",Toast.LENGTH_LONG).show();
			String time = time1.getText().toString();
			Intent intent = new Intent();
			Bundle b =new Bundle();
			b.putString("food_amount",food_amount);
			b.putString("food_amount2",food_amount2);
			b.putString("food_amount3",food_amount3);
			b.putString("food_amount4",food_amount4);
			b.putString("food_amount5",food_amount5);
			b.putString("total",String.valueOf(total));
			b.putString("time",time);
			intent.setClass(getApplicationContext(),OnLoadActivity.class);//显示订单已提交并返回主界面
			intent.putExtras(b);
			startActivity(intent);
		}
	};
	private List<Map<String,Object>> getData(){
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		Map map1 = new HashMap<String,Object>();
		map1.put("food_name","水煮鱼");
		map1.put("food_price","15");
		Map map2 = new HashMap<String,Object>();
		map2.put("food_name","麻婆豆腐");
		map2.put("food_price","14");
		Map map3 = new HashMap<String,Object>();
		map3.put("food_name","酸菜鱼");
		map3.put("food_price","16");
		Map map4 = new HashMap<String,Object>();
		map4.put("food_name","辣子鸡");
		map4.put("food_price","20");
		Map map5 = new HashMap<String,Object>();
		map5.put("food_name","毛血旺");
		map5.put("food_price","17");
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);
		data.add(map5);
		return data;
	}
}
