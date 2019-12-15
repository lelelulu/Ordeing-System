package com.pb.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.ordering.CheckBillActivity;
import com.example.ordering.KeFuActivity;
import com.example.ordering.R;

public class OrderFragment extends Fragment{
	private ImageView img_order_pic;
	private ListView listview_order_detail;
	private ImageView all;
	private ImageView commend;
	private ImageView done;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_order_fragment,container,false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}
	private void init(){
		/*img_order_pic = (ImageView) this.getActivity().findViewById(R.id.img_order_pic);
		listview_order_detail = (ListView) this.getActivity().findViewById(R.id.listview_order_detail);
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), getData(),R.layout.layout_order_listview, new String[]{"order_list_img","order_list_txt"},
				new int[]{R.id.order_list_img,R.id.order_list_txt});
		listview_order_detail.setAdapter(adapter);*/
		all = (ImageView) this.getActivity().findViewById(R.id.all);
		done = (ImageView) this.getActivity().findViewById(R.id.done);
		commend = (ImageView) this.getActivity().findViewById(R.id.commend);

		all.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(),CheckBillActivity.class);
				startActivity(intent);

			}
		});
		done.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(),CheckBillActivity.class);
				startActivity(intent);

			}
		});
		commend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(),CheckBillActivity.class);
				startActivity(intent);

			}
		});



	}
	/*private List<Map<String,Object>> getData(){
		List<Map<String,Object>> list = null;
		list = new ArrayList<Map<String,Object>>();
		Map map1 = new HashMap<String,Object>();
		map1.put("order_list_img",R.drawable.order_completing);
		map1.put("order_list_txt","已经完成订单");
		Map map2 = new HashMap<String,Object>();
		map2.put("order_list_img",R.drawable.order_completed);
		map2.put("order_list_txt","待评价订单");
		Map map3 = new HashMap<String,Object>();
		map3.put("order_list_img",R.drawable.order_evaluate);
		map3.put("order_list_txt","全部订单");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		return list;
	}*/
}
