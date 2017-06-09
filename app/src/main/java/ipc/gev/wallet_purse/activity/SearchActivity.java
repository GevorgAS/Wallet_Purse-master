package ipc.gev.wallet_purse.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import ipc.gev.wallet_purse.R;
import ipc.gev.wallet_purse.adapter.TradeAdapter;
import ipc.gev.wallet_purse.db.DB;
import ipc.gev.wallet_purse.db.entity.Trade;

public class SearchActivity extends AppCompatActivity {
    DB db;
    EditText searchByDateET;
    ListView listView;
    TextView exit_text;
    RadioButton tradeRadio;
    TradeAdapter tradeAdapter;
    ArrayList<Trade> trades;

    // DatePickerDialog
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchByDateET = (EditText)findViewById(R.id.searchByDate);
        db = DB.getInstance(this);
        exit_text = (TextView)findViewById(R.id.exit_text);
        listView = (ListView) findViewById(R.id.lv);
        trades = new ArrayList<>();
        tradeAdapter = new TradeAdapter(this,trades);


        tradeRadio = (RadioButton)findViewById(R.id.tradeRadioChecked);

        dialogTrade();

    }

    public void get(View view) {
        String search_by_date = searchByDateET.getText().toString();
        int sumExits = db.sumTrades(search_by_date,Trade.EXITS);
        int sumRevenues = db.sumTrades(search_by_date,Trade.REVENUES);
        if (tradeRadio.isChecked()) {
            trades = (ArrayList<Trade>) db.searchByDate(search_by_date,Trade.EXITS);
            tradeAdapter = new TradeAdapter(this, trades);
            listView.setAdapter(tradeAdapter);
            tradeAdapter.notifyDataSetChanged();
            exit_text.setVisibility(View.VISIBLE);
            exit_text.setText(sumExits+" ՀՀ դրամ");
        }else{
            trades = (ArrayList<Trade>) db.searchByDate(search_by_date,Trade.REVENUES);
            tradeAdapter = new TradeAdapter(this, trades);
            listView.setAdapter(tradeAdapter);
            tradeAdapter.notifyDataSetChanged();
            exit_text.setVisibility(View.VISIBLE);
            exit_text.setText(sumRevenues+" ՀՀ դրամ");
        }
    }


    private void dialogTrade(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                builder.setTitle("Edit")
                        .setMessage("Edit or Remove this Trade")
                        .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTrade(position);
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTrade(position);
                    }
                })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }


    public void setDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int c_year = calendar.get(Calendar.YEAR);
        int c_month = calendar.get(Calendar.MONTH);
        int c_day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, datePickerListener,c_year, c_month,c_day).show();
    }
    private void deleteTrade(int position){
        Trade trade = trades.get(position);
        long id_get = trade.getId();
        db.deleteTradeByID(id_get);
        trades.remove(position);
        tradeAdapter.notifyDataSetChanged();
    }
    private void editTrade(int p){
        Trade trade = trades.get(p);
        long id = trade.getId();
        String text = trade.getName();
        String loc = trade.getLocation();
        String date = trade.getDate();
        int price = trade.getPrice();

        Intent i = new Intent(SearchActivity.this,EditTradeActivity.class);
        i.putExtra("_id",id+"");
        i.putExtra("text",text);
        i.putExtra("loc",loc);
        i.putExtra("date",date);
        i.putExtra("price",price+"");
        startActivity(i);
    }
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            String mm = (month+1)<10?"0" + (month+1):""+(month+1);
            searchByDateET.setText(new StringBuilder().append(day)
                    .append("/").append(mm).append("/").append(year)
                    .append(" "));

        }
    };
}
