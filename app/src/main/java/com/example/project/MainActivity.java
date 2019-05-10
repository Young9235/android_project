package com.example.project;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    LinearLayout calendar, list, diary;

    /** 하단 버튼 설정 **/
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

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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
    }

}

/** 리스트형 달력 데이터 **/
class AbookListVO {
    String listDay;
    String listMoney;
    AbookListVO(String listDay, String listMoney) {
        this.listDay = listDay;
        this.listMoney = listMoney;
    }
}

/** 리스트형 달력 어뎁터 **/
class CustomAdapter extends BaseAdapter {
    //생성자 포인터의 개념과 비슷? MainActivity를 가리키기 위함
    MainActivity ma;
    int layout;
    AbookListVO[] data;

    CustomAdapter(MainActivity ma, int view, AbookListVO[] data){
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
