package com.pb.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ordering.KeFuActivity;
import com.example.ordering.OrderingOperateActivity;
import com.example.ordering.PersonalInfActivity;
import com.example.ordering.R;
import com.example.ordering.VersionActivity;

public class MyFragment extends Fragment{
	private ImageView about;
	private ImageView personal_inf;
	private ImageView kefucontact;


	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_my_fragment,container,false);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();

		personal_inf.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(),PersonalInfActivity.class);
				startActivity(intent);
			}
		});
		kefucontact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(),KeFuActivity.class);
				startActivity(intent);
			}
		});

		about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(),VersionActivity.class);
				startActivity(intent);
			}
		});

	}
	private void init(){
		 about= (ImageView) this.getActivity().findViewById(R.id.about);
		 personal_inf= (ImageView) this.getActivity().findViewById(R.id.personal_inf);
		 kefucontact= (ImageView) this.getActivity().findViewById(R.id.kefucontact);


	}
}
