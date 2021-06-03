package com.professionalandroid.apps.androidlevelup.parentactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.professionalandroid.apps.androidlevelup.R;

public class ChildActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
    }
}
