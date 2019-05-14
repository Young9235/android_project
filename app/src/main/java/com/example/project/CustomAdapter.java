package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 리스트형 달력 어뎁터
 **/
public class CustomAdapter extends BaseAdapter {
    MainActivity ma;
    int layout;
    ArrayList<AbookListVO> data;
    ArrayList<String> dayList;

    CustomAdapter(MainActivity ma, int view, ArrayList<AbookListVO> data, ArrayList<String> dayList) {
        this.ma = ma;
        this.layout = view;
        this.data = data;
        this.dayList = dayList;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ma.getLayoutInflater();
        View view = inflater.inflate(layout, null);
        TextView day = view.findViewById(R.id.list_day);
        TextView money = view.findViewById(R.id.list_money);
        TextView dayWeek = view.findViewById(R.id.list_day_week);

        day.setText(data.get(position).listDay);
        money.setText(data.get(position).listMoney);
        dayWeek.setText(data.get(position).listDayWeek);

        return view;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}