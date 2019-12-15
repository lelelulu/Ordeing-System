package com.pb.utils;

import java.util.List;

import android.app.Activity;

public class ActivityManager {
	private List<Activity> list;
	
	public ActivityManager() {
		super();
	}

	public ActivityManager(List<Activity> list) {
		super();
		this.list = list;
	}

	public List<Activity> getList() {
		return list;
	}

	public void setList(List<Activity> list) {
		this.list = list;
	}
	private boolean addActivity(Activity activity){
		boolean tag = false;
		try {
			list.add(activity);
			tag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}
	private boolean removeActivity(Activity activity){
		boolean tag = false;
		try {
			list.remove(activity);
			tag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}
	private boolean releaseAllActivity(){
		boolean tag = false;
		try {
			list.clear();
			tag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}
}
