package ipc.gev.wallet_purse.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ipc.gev.wallet_purse.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void find(View view) {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    public void add(View view) {
        Intent intent = new Intent(this,AddTradeActivity.class);
        startActivity(intent);
    }
}
