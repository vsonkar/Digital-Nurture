
public class BankAccount {

    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited: " + amount + ", Balance: " + balance);
        }
    }

    public synchronized void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + ", Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " failed to withdraw: " + amount + ", Insufficient balance.");
        }
    }

    public int getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Thread t1 = new Thread(new Transaction(account), "Thread-1");
        Thread t2 = new Thread(new Transaction(account), "Thread-2");
        Thread t3 = new Thread(new Transaction(account), "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Balance: " + account.getBalance());
    }
}

class Transaction implements Runnable {

    private final BankAccount account;

    public Transaction(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int amount = (int) (Math.random() * 200) + 1;
            if (Math.random() < 0.5) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }

            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
