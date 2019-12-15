package com.example.ordering;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/12/25.
 */
public class VersionActivity  extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        Toast.makeText(getApplicationContext(),"当前已是最新版本，无需更新！",Toast.LENGTH_LONG).show();
    }
}
