package com.professionalandroid.apps.androidlevelup.fragmentcallback;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.professionalandroid.apps.androidlevelup.R;

public class FragmentCallbackMainActivity extends AppCompatActivity implements ColorListFragment.OnColorSelectedListener{

    private ColorFragment mColorFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_callback_main);

        mColorFragment = (ColorFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_color);
    }

    @Override
    public void onColorSelected(int color) {
        mColorFragment.setColor(color);
    }
}