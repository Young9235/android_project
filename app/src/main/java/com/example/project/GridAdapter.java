package com.example.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import static com.example.project.Detail.getToday_date;

/**
 * 그리드뷰 어댑터
 */
public class GridAdapter extends BaseAdapter  {

    private Calendar mCal;
    private final List<String> list;
    private final LayoutInflater inflater;
    private Context appCompatActivity = null;
    final static String TABLE_NAME = "MyAccountList";
    final static String KEY_DATE = "date";
    public static String View_DATE = getToday_date();
    private static final String TAG = "MainActivity";

    private TextView sum_view;

    MyDBHelper mHelper;
    SQLiteDatabase db;
    Cursor cursor;
    MyCursorAdapter myAdapter;

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

        AppCompatActivity aca = new AppCompatActivity();

        ViewHolder holder;


/***/
        EditText eContext = (EditText) aca.findViewById( R.id.edit_context );
        EditText ePrice = (EditText) aca.findViewById( R.id.edit_price );
        EditText ePay = (EditText) aca.findViewById( R.id.edit_pay );
        sum_view = (TextView) aca.findViewById(R.id.total_sum);

        String contexts = eContext.getText().toString();
        int price = Integer.parseInt( ePrice.getText().toString() );
        String pay = ePay.getText().toString();
        String today_Date = getToday_date();
        Log.d(TAG, "값 확인" + contexts +", " + price + ", " + pay + ", " + today_Date);

        //문자열은 ''로 감싸야 한다.
        String query = String.format(
                "INSERT INTO %s VALUES ( null, '%s', %d, '%s', '%s' );", TABLE_NAME, contexts, price, pay, View_DATE);
        db.execSQL( query );

        String querySelectAll = String.format( "SELECT * FROM %s WHERE date = '%s'", TABLE_NAME, View_DATE);
        cursor = db.rawQuery( querySelectAll, null );
        myAdapter.changeCursor( cursor );
        //myAdapter.notifyDataSetChanged();

        eContext.setText( "" );
        ePrice.setText( "" );
        ePay.setText( "" );



/***/
        // 총합 가격 표시
        String queryPriceSum = String.format( " SELECT SUM(price) FROM %s WHERE date = '%s'", TABLE_NAME, View_DATE);
        cursor = db.rawQuery( queryPriceSum, null );
        cursor.moveToNext();
        String sum = String.valueOf(cursor.getInt(0));
        Log.d(TAG, "sum : " + sum);
        sum_view.setText(sum);



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
        holder.totalGridview.setText(sum);


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