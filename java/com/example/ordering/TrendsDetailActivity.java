package com.example.ordering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/27.
 */
public class TrendsDetailActivity extends Activity {

    private GridView gv_table1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trend_detail);
        init();
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),R.layout.gv1,new String[]{"id","rec"},new int[]{R.id.user_id,R.id.user_recommend});
        //桌子的图片以及桌号
        gv_table1.setAdapter(simpleAdapter);
    }
    private void init(){
        gv_table1 = (GridView)findViewById(R.id.gv_table1);
    }
    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = null;

        for(int i=0;i<1;i++){//可改列表数目
            map = new HashMap<String,Object>();
            map.put("id","用户34251：");
            map.put("rec","评价：服务态度非常好，上菜速度快，推荐大家来吃。");
            Map map1 = new HashMap<String,Object>();
            map1.put("id","用户23415：");
            map1.put("rec","评价：菜的种类很丰富，以鱼为主，上菜速度不错。");
            Map map2 = new HashMap<String,Object>();
            map2.put("id","用户13241：");
            map2.put("rec","评价：当时去的时候人特别多，但是老板专门为我们安排了等候区，还有一些小吃，非常贴心。");
            Map map3 = new HashMap<String,Object>();
            map3.put("id","用户14156：");
            map3.put("rec","评价：服务周到，非常棒！");
            Map map4 = new HashMap<String,Object>();
            map4.put("id","用户24563：");
            map4.put("rec","评价：5星级服务，有些菜跟家乡的非常像。");
            Map map5 = new HashMap<String,Object>();
            map5.put("id","用户32526：");
            map5.put("rec","评价：中午人流量大的时候上菜速度较慢。");
            Map map6 = new HashMap<String,Object>();
            map6.put("id","用户46373：");
            map6.put("rec","评价：菜量很足，很喜欢。");
            Map map7 = new HashMap<String,Object>();
            map7.put("id","用户16137：");
            map7.put("rec","评价：店家会根据用餐者的喜好和禁忌来及时调整服务，非常周到！");
            Map map8 = new HashMap<String,Object>();
            map8.put("id","用户57895：");
            map8.put("rec","评价：全国各地特色菜应有尽有，可以满足各地人群需求。");
            Map map9 = new HashMap<String,Object>();
            map9.put("id","用户67532：");
            map9.put("rec","评价：门面装修不够大气，价格稍贵。");
            Map map10 = new HashMap<String,Object>();
            map10.put("id","用户46731：");
            map10.put("rec","评价：订单提交之后店主确认时间过久。");

            lists.add(map);
            lists.add(map1);
            lists.add(map2);
            lists.add(map3);
            lists.add(map4);
            lists.add(map5);
            lists.add(map6);
            lists.add(map7);
            lists.add(map8);
            lists.add(map9);
            lists.add(map10);
        }
        return lists;
    }


}
