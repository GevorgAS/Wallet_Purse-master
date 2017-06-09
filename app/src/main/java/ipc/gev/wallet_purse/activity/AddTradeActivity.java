package ipc.gev.wallet_purse.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;

import ipc.gev.wallet_purse.R;
import ipc.gev.wallet_purse.db.DB;
import ipc.gev.wallet_purse.db.entity.Trade;

public class AddTradeActivity extends AppCompatActivity {
    DB db;
    EditText name_et;
    EditText location_et;
    EditText price_et;
    EditText date_et;
    RadioButton rb;
    //DatePickerDialog variables

    int year;
    int month;
    int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name_et = (EditText) findViewById(R.id.tradeName);
        location_et = (EditText) findViewById(R.id.tradeLocation);
        price_et = (EditText) findViewById(R.id.tradePrice);
        date_et = (EditText) findViewById(R.id.tradeDate);
        rb = (RadioButton) findViewById(R.id.tradeChecked);
        db = DB.getInstance(this);
    }



    public void date(View view) {
        Calendar calendar = Calendar.getInstance();
        int c_year = calendar.get(Calendar.YEAR);
        int c_month = calendar.get(Calendar.MONTH);
        int c_day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, datePickerListener,c_year, c_month,c_day).show();
    }

    public void saveTrade(View view) {

        if (validate()) {
            if (rb.isChecked()) {
                String name = name_et.getText().toString();
                String location = location_et.getText().toString();
                int price = Integer.parseInt(price_et.getText().toString());
                String date = date_et.getText().toString();
                Trade trade = new Trade(name, location, price, date,Trade.EXITS);
                db.createTrade(trade);
                finish();
            }else{
                String name = name_et.getText().toString();
                String location = location_et.getText().toString();
                int price = Integer.parseInt(price_et.getText().toString());
                String date = date_et.getText().toString();
                Trade trade = new Trade(name, location, price, date,Trade.REVENUES);
                db.createTrade(trade);
                finish();
            }
        }
    }
    private boolean validate(){
        boolean isCorrect = true;
        if (name_et.getText().toString().equals("")){
            name_et.setError("Required");
            isCorrect = false;
        }
        if (location_et.getText().toString().equals("")){
            location_et.setError("Required");
            isCorrect = false;
        }
        if (price_et.getText().toString().equals("")){
            price_et.setError("Required");
            isCorrect = false;
        }
        if (date_et.getText().toString().equals("")){
            date_et.setError("Required");
            isCorrect = false;
        }
        return isCorrect;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int c_year,
                              int c_month, int c_day) {

            year = c_year;
            month = c_month;
            day = c_day;

            String mm = (month+1)<10?"0" + (month+1):""+(month+1);
            date_et.setText(new StringBuilder().append(day)
                    .append("/").append(mm).append("/").append(year)
                    .append(" "));

        }
    };
}
