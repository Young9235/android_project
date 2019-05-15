package com.example.project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * 그리드뷰 어댑터
 */
public class GridAdapter extends BaseAdapter  {

    private Calendar mCal;
    private final List<String> list;
    private final LayoutInflater inflater;
    private Context appCompatActivity = null;

    /**
     * 생성자
     */

    public GridAdapter(Context context, List<String> list) {
        appCompatActivity = context;

        this.list = list;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override

    public int getCount() {

        return list.size();

    }

    @Override

    public String getItem(int position) {

        return list.get(position);

    }

    @Override

    public long getItemId(int position) {

        return position;

    }

    @Override

    public View getView (int position, View convertView, ViewGroup parent) {


        ViewHolder holder;



        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);

            holder = new ViewHolder();


            holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);

            holder.totalGridview = (TextView) convertView.findViewById(R.id.total_gridview);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }


        holder.tvItemGridView.setText("" + getItem(position));
        //holder.totalGridview.setText(data.get(position).listMoney);
        //money.setText(data.get(position).listMoney);


        //해당 날짜 텍스트 컬러,배경 변경

        mCal = Calendar.getInstance();

        //오늘 day 가져옴

        Integer today = mCal.get(Calendar.DAY_OF_MONTH);

        String sToday = String.valueOf(today);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            String Today = String.valueOf(i);
            if (Today.equals(getItem(position))) {
                holder.tvItemGridView.setTextColor(appCompatActivity.getResources().getColor(R.color.black));
            }
        }
        if ("일".equals(getItem(0))) {
            holder.tvItemGridView.setTextColor(appCompatActivity.getResources().getColor(R.color.gray));
        }

        sToday = String.valueOf(today);
        if (sToday.equals(getItem(position))) {
            holder.tvItemGridView.setTextColor(appCompatActivity.getResources().getColor(R.color.black));
        }
        return convertView;

    }

}