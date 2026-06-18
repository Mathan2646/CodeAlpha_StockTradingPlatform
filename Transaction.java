public class Transaction
{
    private String type;
    private String symbol;
    private int quantity;
    private double price;
    private double total;

    public Transaction(String type, String symbol, int quantity, double price)
    {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }

    public String toString()
    {
        return type + " | Stock: " + symbol + " | Quantity: " + quantity + " | Price: Rs." + price + " | Total: Rs." + total;
    }
}
