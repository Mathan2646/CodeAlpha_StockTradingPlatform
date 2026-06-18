import java.util.*;


public class STP
{
    static ArrayList<Stock> stocks = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner s = new Scanner(System.in);
    static User user = new User("Mathan", 50000);

    public static void main(String[] args)
    {
        loadStocks();
        System.out.println("\n===== Stock Trading Platform =====");
        System.out.println("User Name: " + user.getName());
        System.out.println("Balance: Rs." + user.getBalance());
        System.out.println("1. View Market Data");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. View Portfolio");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
        int choice;
        do
        {
            System.out.print("Enter your choice: ");
            choice = s.nextInt();
            switch(choice)
            {
                case 1:
                    showMarketData();
                    break;
                case 2:
                    buyStock();
                    break;
                case 3:
                    sellStock();
                    break;
                case 4:
                    viewPortfolio();
                    break;
                case 5:
                    viewTransactions();
                    break;
                case 6:
                    System.out.println("Exiting Program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while(choice != 6);
    }
    public static void loadStocks()
    {
        stocks.add(new Stock("TCS", "Tata Consultancy Services", 3850));
        stocks.add(new Stock("INFY", "Infosys", 1490));
        stocks.add(new Stock("HDFC", "HDFC Bank", 1675));
        stocks.add(new Stock("RELI", "Reliance", 2920));
        stocks.add(new Stock("WIPRO", "Wipro", 470));
        stocks.add(new Stock("ICICI", "ICICI Bank", 1120));
        stocks.add(new Stock("SBI", "State Bank of India", 840));
        stocks.add(new Stock("HCL", "HCL Technologies", 1450));
    }
    public static void showMarketData()
    {
        System.out.println("\n--- Market Data ---");
        for(Stock st : stocks)
        {
            System.out.println(st);
        }
    }
    public static Stock findStock(String symbol)
    {
        for(Stock st : stocks)
        {
            if(st.getSymbol().equalsIgnoreCase(symbol))
            {
                return st;
            }
        }
        return null;
    }
    public static void buyStock()
    {
        s.nextLine();
        System.out.print("Enter stock symbol to buy: ");
        String symbol = s.nextLine();

        Stock st = findStock(symbol);

        if(st == null)
        {
            System.out.println("Stock not found!");
            return;
        }
        System.out.print("Enter quantity: ");
        int quantity = s.nextInt();

        if(quantity <= 0)
        {
            System.out.println("Invalid quantity!");
            return;
        }

        double totalCost = st.getPrice() * quantity;

        if(totalCost > user.getBalance())
        {
            System.out.println("Insufficient balance!");
            return;
        }
        user.setBalance(user.getBalance() - totalCost);
        HashMap<String, Integer> holdings = user.getHoldings();
        if(holdings.containsKey(symbol.toUpperCase()))
        {
            holdings.put(symbol.toUpperCase(), holdings.get(symbol.toUpperCase()) + quantity);
        }
        else
        {
            holdings.put(symbol.toUpperCase(), quantity);
        }
        transactions.add(new Transaction("BUY", symbol.toUpperCase(), quantity, st.getPrice()));
        System.out.println("Stock bought successfully.");
    }
    public static void sellStock()
    {
        s.nextLine();
        System.out.print("Enter stock symbol to sell: ");
        String symbol = s.nextLine().toUpperCase();
        Stock st = findStock(symbol);
        if(st == null)
        {
            System.out.println("Stock not found!");
            return;
        }
        HashMap<String, Integer> holdings = user.getHoldings();
        if(!holdings.containsKey(symbol))
        {
            System.out.println("You do not own this stock.");
            return;
        }
        System.out.print("Enter quantity to sell: ");
        int quantity = s.nextInt();
        if(quantity <= 0)
        {
            System.out.println("Invalid quantity!");
            return;
        }
        int ownedQuantity = holdings.get(symbol);
        if(quantity > ownedQuantity)
        {
            System.out.println("Not enough shares to sell.");
            return;
        }
        double totalAmount = st.getPrice() * quantity;
        user.setBalance(user.getBalance() + totalAmount);
        if(quantity == ownedQuantity)
        {
            holdings.remove(symbol);
        }
        else
        {
            holdings.put(symbol, ownedQuantity - quantity);
        }
        transactions.add(new Transaction("SELL", symbol, quantity, st.getPrice()));
        System.out.println("Stock sold successfully.");
    }
    public static void viewPortfolio()
    {
        HashMap<String, Integer> holdings = user.getHoldings();
        if(holdings.isEmpty())
        {
            System.out.println("Portfolio is empty.");
            return;
        }
        System.out.println("\n--- Portfolio ---");
        double totalValue = 0;
        for(String symbol : holdings.keySet())
        {
            int quantity = holdings.get(symbol);
            Stock st = findStock(symbol);
            if(st != null)
            {
                double stockValue = st.getPrice() * quantity;
                totalValue += stockValue;
                System.out.println("Stock: " + symbol + " | Quantity: " + quantity + " | Current Price: Rs." + st.getPrice() + " | Value: Rs." + stockValue);
            }
        }
        System.out.println("Cash Balance: Rs." + user.getBalance());
        System.out.println("Total Portfolio Value: Rs." + (totalValue + user.getBalance()));
    }
    public static void viewTransactions()
    {
        if(transactions.isEmpty())
        {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\n--- Transaction History ---");
        for(Transaction t : transactions)
        {
            System.out.println(t);
        }
    }
}
