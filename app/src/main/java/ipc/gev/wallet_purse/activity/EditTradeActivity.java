package ipc.gev.wallet_purse.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import ipc.gev.wallet_purse.R;
import ipc.gev.wallet_purse.db.DB;

public class EditTradeActivity extends AppCompatActivity {

    DB db;

    EditText text_et;
    EditText loc_et;
    EditText date_et;
    EditText price_et;

    //DatePickerDialog variables
    private int year;
    private int month;
    private int day;
    Calendar calendar = Calendar.getInstance();
    int c_year = calendar.get(Calendar.YEAR);
    int c_month = calendar.get(Calendar.MONTH);
    int c_day = calendar.get(Calendar.DAY_OF_MONTH);

    long currentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        text_et = (EditText) findViewById(R.id.editSomeText);
        loc_et = (EditText) findViewById(R.id.editLocation);
        date_et = (EditText) findViewById(R.id.editDate);
        price_et = (EditText) findViewById(R.id.editPrice);

        db = DB.getInstance(this);
        Intent intent = getIntent();

        String text = intent.getStringExtra("text");
        String loc = intent.getStringExtra("loc");
        String date = intent.getStringExtra("date");
        String price = intent.getStringExtra("price");
        String id = intent.getStringExtra("_id");

        currentID = Long.parseLong(id,10);

        text_et.setText(text);
        loc_et.setText(loc);
        date_et.setText(date);
        price_et.setText(price);


    }



    public void date(View view) {
        new DatePickerDialog(this, datePickerListener,c_year, c_month,c_day).show();
    }

    public void editTrade(View view) {
        if (validate()){
            String e_text = text_et.getText().toString();
            String e_loc = loc_et.getText().toString();
            String e_date = date_et.getText().toString();
            int e_price = Integer.parseInt(price_et.getText().toString());
            db.updateTradeByID(currentID,e_text,e_loc,e_price,e_date);
            finish();
        }
    }
    private boolean validate(){
        boolean isCorrect = true;
        if (text_et.getText().toString().equals("")){
            text_et.setError("Required");
            isCorrect = false;
        }
        if (loc_et.getText().toString().equals("")){
            loc_et.setError("Required");
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
