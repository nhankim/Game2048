package com.example.administrator.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.administrator.custom.OVuong;
import com.example.administrator.game2048.R;

import java.util.List;

public class AdapterOSo extends ArrayAdapter<Integer> {
    @NonNull Activity context;
    int resource;
    @NonNull List<Integer> objects;
    public AdapterOSo(@NonNull Activity context, int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View motO = inflater.inflate(this.resource,null);

        OVuong oVuong = motO.findViewById(R.id.oVuong);
        oVuong.chinhText(this.objects.get(position));
        return motO;
    }

}
