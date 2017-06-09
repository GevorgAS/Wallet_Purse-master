package ipc.gev.wallet_purse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ipc.gev.wallet_purse.R;
import ipc.gev.wallet_purse.db.entity.Trade;

/**
 * Created by Gevorg on 02.06.2017.
 */

public class TradeAdapter extends BaseAdapter {
    private ArrayList<Trade> trades;
    private LayoutInflater inflater;

    public TradeAdapter(Context c,ArrayList<Trade> t){
        trades = t;
        inflater = (LayoutInflater.from(c));
    }
    @Override
    public int getCount() {
        return trades.size();
    }

    @Override
    public Object getItem(int position) {
        return trades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder{
        TextView price;
        TextView loc;
        TextView text;
        TextView date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_list_item,null);
        Holder holder = new Holder();
        holder.price = (TextView) view.findViewById(R.id.price);
        holder.loc = (TextView) view.findViewById(R.id.location);
        holder.text = (TextView) view.findViewById(R.id.some_text);
        holder.date = (TextView) view.findViewById(R.id.date);

        holder.price.setText(trades.get(position).getPrice()+"");
        holder.loc.setText(trades.get(position).getLocation());
        holder.text.setText(trades.get(position).getName());
        holder.date.setText(trades.get(position).getDate());

        return view;
    }
}
