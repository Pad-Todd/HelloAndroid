package com.professionalandroid.apps.androidlevelup.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.professionalandroid.apps.androidlevelup.R;

public class BaseAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);
    }
}