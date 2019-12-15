package com.example.ordering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/12/26.
 */
public class LoginActivity extends Activity {
    EditText usernameEdit; // 用户名输入框
    EditText pwdEdit; // 密码输入框
    CheckBox rememberPwdCheck;// 记住密码的复选框
    Button loginBtn;// 登录按钮
    ProgressBar progressBar;// 进度条
    SharedPreferences pref; // 简单数据存储
    Handler handler; // 线程的手柄
    static final int STOP = 0x111;// 进度完成的标志
    static final int CONTINUE = 0x112;// 继续显示进度条的标志
    static final int MAX = 100; // 最大的进度为100%
    int progress; // 进度条的当前进度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init(); // 初始化工作
        setListeners();// 事件监听
    }

    void init() {
      /* 初始化控件变量 */
        initViews();

      /* 初始化线程的手柄 */
        initHandler();

    }

    void initViews() {
        usernameEdit = (EditText) findViewById(R.id.edit_username);
        pwdEdit = (EditText) findViewById(R.id.edit_pwd);
        rememberPwdCheck = (CheckBox) findViewById(R.id.check_rememberPwd);
        loginBtn = (Button) findViewById(R.id.btn_login);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        pref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        usernameEdit.setText(pref.getString("USERNAME", ""));
        if (pref.getBoolean("REMBERPWD", false)) {
            pwdEdit.setText(pref.getString("PWD", ""));
        } else {
            pwdEdit.setText("");
        }
        rememberPwdCheck.setChecked(pref.getBoolean("REMBERPWD", false));
        progress = 0;
        progressBar.setProgress(progress);
        progressBar.setMax(MAX);
    }

    void initHandler() {
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                switch (msg.what) {
            /* 进度未完成 */
                    case CONTINUE:
                        if (!Thread.currentThread().isInterrupted()) {// 当前线程正在运行
                            progressBar.setProgress(progress);

                        }
                        break;

            /* 进度完成 */
                    case STOP:
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // 结束该Activity
                        break;

                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    void setListeners() {
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username = usernameEdit.getText().toString();
                String pwd = pwdEdit.getText().toString();
                if ((!username.equals("lalala")) || (!pwd.equals("lalala"))) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不正确",
                            Toast.LENGTH_LONG).show();
                } else {
               /*用户名与密码都正确的情况处理*/
                    SharedPreferences.Editor editor = pref.edit();
                    if (rememberPwdCheck.isChecked()) {
                        editor.putString("USERNAME", username);
                        editor.putString("PWD", pwd);
                        editor.putBoolean("REMBERPWD", true);
                        editor.commit();
                    } else {
                        editor.putBoolean("REMBERPWD", false);
                        editor.commit();
                    }
                    usernameEdit.setEnabled(false);
                    pwdEdit.setEnabled(false);
                    loginBtn.setEnabled(false);

               /*将显示进度条5秒钟*/
                    progressBar.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            try {
                        /*循环5次，每次睡眠1秒钟*/
                                for (int i = 0; i < 5; i++) {
                                    progress = (i + 1) * 20;
                                    Thread.sleep(1000);
                                    if (i == 4) {
                                        Message msg = new Message();
                                        msg.what = STOP;
                                        handler.sendMessage(msg);
                                        break;
                                    } else {
                                        Message msg = new Message();
                                        msg.what = CONTINUE;
                                        handler.sendMessage(msg);
                                    }

                                }
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }).start();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                hideSoftInput(view);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    // 隐藏软键盘
    private void hideSoftInput(View view) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
