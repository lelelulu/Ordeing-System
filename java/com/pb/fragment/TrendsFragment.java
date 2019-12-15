package com.pb.fragment;

import com.example.ordering.OrderDetailActivity;
import com.example.ordering.OrderingOperateActivity;
import com.example.ordering.R;
import com.example.ordering.TrendsDetailActivity;
import com.pb.entity.Img;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class TrendsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_trends_fragment, container, false);
	}

	private ViewFlipper vf;//预留的图片滑动显示区域
	private Button button;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		vf = (ViewFlipper) this.getActivity().findViewById(R.id.viewFlipper);
		button=(Button)this.getActivity().findViewById(R.id.button3);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), TrendsDetailActivity.class);//传送意图至OrderDetailActivity界面即确定提交的界面
				startActivity(intent);
			}
		});
		List<Img> list = getImages();
		for(Img img:list){
			ImageView imgView = new ImageView(getActivity());//为几个需要动态显示的图片腾出位置
			imgView.setImageResource(img.getIcon());
			vf.addView(imgView,new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT));
		}
		vf.startFlipping();

	}

	private List<Img> getImages(){
		List<Img> list = new ArrayList<Img>();
		Img img1 = new Img(R.drawable.icon_order_inform);
		Img img2 = new Img(R.drawable.icon_order_todaytastes);
		Img img3 = new Img(R.drawable.icon_order_todaytopic);
		list.add(img1);
		list.add(img2);
		list.add(img3);
		return list;
	}


}
