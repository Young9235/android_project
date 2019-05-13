package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 리스트형 달력 어뎁터
 **/
public class CustomAdapter extends BaseAdapter {
        //생성자 포인터의 개념과 비슷? MainActivity를 가리키기 위함
        MainActivity ma;
        int layout;
        AbookListVO[] data;

        CustomAdapter(MainActivity ma, int view, AbookListVO[] data) {
            this.ma = ma;
            this.layout = view;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.length;               // 5개의 view의 모습이 list항목의 하나로 나온다.
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = ma.getLayoutInflater();
            View view = inflater.inflate(layout, null);
            TextView day = view.findViewById(R.id.list_day);
            TextView money = view.findViewById(R.id.list_money);

            day.setText(data[position].listDay);
            money.setText(data[position].listMoney);

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