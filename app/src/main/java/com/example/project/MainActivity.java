package com.example.project;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    LinearLayout calendar, list, diary;
    private TextView tvDate;
    private GridAdapter gridAdapter;
    private ArrayList<String> dayList;
    private GridView gridView;
    private Calendar mCal;

    /**
     * 하단 버튼 설정
     **/
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    calendar.setVisibility(View.VISIBLE);
                    list.setVisibility(View.GONE);
                    diary.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    calendar.setVisibility(View.GONE);
                    list.setVisibility(View.VISIBLE);
                    diary.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    calendar.setVisibility(View.GONE);
                    list.setVisibility(View.GONE);
                    diary.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.abook_calendar);
        list = findViewById(R.id.abook_list);
        diary = findViewById(R.id.abook_diary);

        tvDate = (TextView) findViewById(R.id.tv_date);
        gridView = (GridView) findViewById(R.id.gridview);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /**  달력표 **/
        // 오늘에 날짜를 세팅 해준다.

        long now = System.currentTimeMillis();

        final Date date = new Date(now);

        //연,월,일을 따로 저장

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);

        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);

        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);


        /** 리스트 달력 **/
        LayoutInflater inflater = getLayoutInflater();   //Layout XML 디자인 모습을 자바의 View 형태로 변환하는 것

        inflater.inflate(R.layout.list_view, null);

        ListView listView = findViewById(R.id.main_list_view);

        AbookListVO vo1 =
                new AbookListVO("1", "60,000");
        AbookListVO vo2 =
                new AbookListVO("2", "20,000");
        AbookListVO vo3 =
                new AbookListVO("3", "18,000");
        AbookListVO vo4 =
                new AbookListVO("4", "40,000");
        AbookListVO
                vo5 = new AbookListVO("5", "80,000");

        AbookListVO data[] = {
                vo1, vo2, vo3, vo4, vo5
        };

        CustomAdapter adapter = new CustomAdapter(MainActivity.this, R.layout.list_view, data);
        listView.setAdapter(adapter);

        /** 환경설정 버튼 **/
        final ImageView setButton = findViewById(R.id.setting);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
            }
        });

        /** 달력표 **/
        //현재 날짜 텍스트뷰에 뿌려줌

        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));


        //gridview 요일 표시

        dayList = new ArrayList<String>();

        dayList.add("일");

        dayList.add("월");

        dayList.add("화");

        dayList.add("수");

        dayList.add("목");

        dayList.add("금");

        dayList.add("토");


        mCal = Calendar.getInstance();

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)

        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);

        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);

        //1일 - 요일 매칭 시키기 위해 공백 add

        for (int i = 1; i < dayNum; i++) {

            dayList.add("");

        }

        setCalendarDate(mCal.get(Calendar.MONTH) + 1);


        gridAdapter = new GridAdapter(getApplicationContext(), dayList);

        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setContentView(R.layout.detail);
                //Intent intent = new Intent(getApplicationContext(),detail_display.class);
            }
        });
    }

    /**
     * 해당 월에 표시할 일 수 구함
     */

    private void setCalendarDate(int month) {

        mCal.set(Calendar.MONTH, month - 1);


        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {

            dayList.add("" + (i + 1));

        }


    }

    /**
     * 그리드뷰 어댑터
     */

    private class GridAdapter extends BaseAdapter {


        private final List<String> list;

        private final LayoutInflater inflater;


        /**
         * 생성자
         */

        public GridAdapter(Context context, List<String> list) {

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

        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder holder = null;


            if (convertView == null) {

                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);

                holder = new ViewHolder();


                holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);


                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();

            }

            holder.tvItemGridView.setText("" + getItem(position));


            //해당 날짜 텍스트 컬러,배경 변경

            mCal = Calendar.getInstance();

            //오늘 day 가져옴

            Integer today = mCal.get(Calendar.DAY_OF_MONTH);

            String sToday = String.valueOf(today);

            for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                String Today = String.valueOf(i);
                if (Today.equals(getItem(position))) {
                    holder.tvItemGridView.setTextColor(getResources().getColor(R.color.gray));
                }
            }
            if ("일".equals(getItem(0))) {
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.gray));
            }

            sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))) {
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.black));
            }
            return convertView;

        }

    }

    private class ViewHolder {

        TextView tvItemGridView;

    }

    /**
     * 리스트형 달력 데이터
     **/
    class AbookListVO {
        String listDay;
        String listMoney;

        AbookListVO(String listDay, String listMoney) {
            this.listDay = listDay;
            this.listMoney = listMoney;
        }
    }

    /**
     * 리스트형 달력 어뎁터
     **/
    class CustomAdapter extends BaseAdapter {
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
}
