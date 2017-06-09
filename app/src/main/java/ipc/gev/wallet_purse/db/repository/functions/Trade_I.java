package ipc.gev.wallet_purse.db.repository.functions;

import java.util.List;

import ipc.gev.wallet_purse.db.entity.Trade;

/**
 * Created by Gevorg on 27.05.2017.
 */

public interface Trade_I {
    void createTrade(Trade trade);
    List<Trade> searchByDate(String date,int status);
    void deleteTradeByID(long id);
    void updateTradeByID(long id,String text,String location,int price,String date);
    int sumTrades(String date, int status);
}
