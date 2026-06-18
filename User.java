import java.util.*;

public class User
{
    private String name;
    private double balance;
    private HashMap<String, Integer> holdings;

    public User(String name, double balance)
    {
        this.name = name;
        this.balance = balance;
        holdings = new HashMap<>();
    }

    public String getName()
    {
        return name;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public HashMap<String, Integer> getHoldings()
    {
        return holdings;
    }
}
