package com.example.ordering;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/12/25.
 */

public class PersonalInfActivity extends Activity {

    private ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_inf);
        photo=(ImageView)findViewById(R.id.image) ;
        initView();
    }


    private void initView() {
        //从相册选择图片按钮
        Button pick = (Button) findViewById(R.id.button2);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlbum();
            }
        });
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 2:
                    //如果系统API大于19即Android4.4以上的系统，选择照片后不是返回的真实的图片的uri需要进一步的解析
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeOnKitKat(data);
                    }
                    break;
            }
        }
    }

    //API大于等于19时解析返回的uri获取真实的图片路径并把图片显示在imageview上的方法
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.i("zhangdi", uri.toString());
        //如果uri是document类型则取出uri的documentID，如果不是则普通方法处理，如果uri的Authority
        //是media类型，则需要拆分id取出真正的id值，重构uri
        if (DocumentsContract.isDocumentUri(this, uri)) {

            String docId = DocumentsContract.getDocumentId(uri);
            Log.i("zhangdi", docId);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {

                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {

                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equals(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equals(uri.getScheme())) {
            imagePath = uri.getPath();
        }

        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            int w,h;
            w = bitmap.getWidth();
            h = bitmap.getHeight();
            Bitmap scale = Bitmap.createScaledBitmap(bitmap,500,500*h/w,false);
            photo.setImageBitmap(scale);
        } else {
            Toast.makeText(this, "找不到图片路径", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 根据uri和查询条件在provider中查询出图片的真实路径
     */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;
    }

    //API小于19时获取图片路径并显示的方法
    private void handleImageBeforeOnKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
}