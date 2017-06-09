package ipc.gev.wallet_purse.db.entity;



public class Trade {
    private long id;
    private String name;
    private String location;
    private int price;
    private String date;
    private int status;
    public static final int REVENUES = 1;
    public static final int EXITS = 0;



    public Trade(){

    }
    public Trade(long id){
        this.id = id;
    }

    public Trade(long id, String name, String location, int price, String date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.date = date;
    }

    public Trade(String name, String location, int price, String date, int status) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.date = date;
        this.status = status;
    }

    public Trade(long id, String name, String location, int price, String date, int status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.date = date;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Trade(String name, String location, int price, String date) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Trade{" + id+
                " name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}
