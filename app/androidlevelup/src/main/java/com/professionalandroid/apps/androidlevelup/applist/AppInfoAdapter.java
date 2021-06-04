package com.professionalandroid.apps.androidlevelup.applist;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.professionalandroid.apps.androidlevelup.R;

import java.util.List;

public class AppInfoAdapter extends BaseAdapter {
    private List<ApplicationInfo> mInfos;

    public AppInfoAdapter(List<ApplicationInfo> data) {
        this.mInfos = data;
    }

    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView != null){
            holder = getViewHolder(parent);
        }
        else {
            holder = getViewHolder(convertView);
        }

        ApplicationInfo info = mInfos.get(position);
        Drawable icon = info.loadIcon(parent.getContext().getPackageManager());
        String name = String.valueOf(info.loadLabel(parent.getContext().getPackageManager()));

        holder.imageView.setImageDrawable(icon);
        holder.textView.setText(name);

        return convertView;
    }

    private ViewHolder getViewHolder(ViewGroup parent) {
        View convertView;
        ViewHolder holder;
        holder = new ViewHolder();
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent,false);
        holder.imageView = (ImageView) convertView.findViewById(R.id.icon_image);
        holder.textView = (TextView) convertView.findViewById(R.id.app_name_text);
        convertView.setTag(holder);
        return holder;
    }
    private ViewHolder getViewHolder(View convertView) {
        ViewHolder holder;
        holder = (ViewHolder) convertView.getTag();
        return holder;
    }

    private class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }
}
