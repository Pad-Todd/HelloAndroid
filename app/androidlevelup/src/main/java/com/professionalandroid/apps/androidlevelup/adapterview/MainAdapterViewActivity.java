package com.professionalandroid.apps.androidlevelup.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.professionalandroid.apps.androidlevelup.R;

import java.util.ArrayList;

public class MainAdapterViewActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adapter_view);

        DataSet();

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainAdapterViewActivity.this, position + "번째 아이템 선택", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void DataSet() {

        ArrayList<String> data = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            data.add("data " + i);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, data);
    }
}