package com.pb.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.ordering.OrderingOperateActivity;
import com.example.ordering.R;
import com.pb.entity.Img;

public class MainFragment extends Fragment{//主页界面
	private ViewFlipper vf_today;//预留的图片滑动显示区域
	private ImageView btn_ordering;//圆按钮
	//private FragmentManager fragmentManager;
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_main_fragment,container,false);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
		List<Img> list = getImages();
		for(Img img:list){
			ImageView imgView = new ImageView(getActivity());//为几个需要动态显示的图片腾出位置
			imgView.setImageResource(img.getIcon());
			vf_today.addView(imgView,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		}
		vf_today.startFlipping();
	}
	private void init(){
		vf_today = (ViewFlipper) this.getActivity().findViewById(R.id.vf_today);
		btn_ordering = (ImageView) this.getActivity().findViewById(R.id.btn_ordering);
		btn_ordering.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				btn_ordering.setImageResource(R.drawable.ordering_selected);
				Intent intent = new Intent();
				intent.setClass(getActivity(),OrderingOperateActivity.class);//转至显示所有桌位的界面
				startActivity(intent);
			}
		});
	}
	private List<Img> getImages(){
		List<Img> list = new ArrayList<Img>();
		Img img1 = new Img(R.drawable.shuizhuyu);
		Img img2 = new Img(R.drawable.suancaiyu);
		Img img3 = new Img(R.drawable.maoxuewang);
		Img img4 = new Img(R.drawable.mapodoufu);
		Img img5 = new Img(R.drawable.laziji);
		list.add(img1);
		list.add(img2);
		list.add(img3);
		list.add(img4);
		list.add(img5);
		return list;
	}
}
