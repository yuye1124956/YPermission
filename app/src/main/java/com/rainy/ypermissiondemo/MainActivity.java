package com.rainy.ypermissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.rainy.ypermission.OnPermissionResult;
import com.rainy.ypermission.YPermission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getPermission(View v) {
        YPermission.with(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .start(new OnPermissionResult() {
                    @Override
                    public void onResult(boolean granted) {
                        Toast.makeText(MainActivity.this, String.valueOf(granted), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
