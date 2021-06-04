package com.professionalandroid.apps.androidlevelup.applist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;

import com.professionalandroid.apps.androidlevelup.R;

import java.util.List;

public class ApplistActivity extends AppCompatActivity {
    private List<ApplicationInfo> infos;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);

        mListView = (ListView) findViewById(R.id.list_view);
        // 기기에 설치된 모든 앱 목록
        getInstalledApps();
        listViewSetAdapter(infos);

    }
    private void getInstalledApps() {
        PackageManager pm = getPackageManager();
        infos = pm.getInstalledApplications(PackageManager.GET_META_DATA);
    }
    private void listViewSetAdapter(List<ApplicationInfo> infos) {
        AppInfoAdapter adapter = new AppInfoAdapter(infos);
        mListView.setAdapter(adapter);
    }
}