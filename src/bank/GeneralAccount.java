package bank;

public class GeneralAccount {
    private float balance;

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public boolean deposit(float amount) {

        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be > 0!");
            }
            else {
                balance = balance + amount;
                return true;
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void transfer(float amount, int id, ClientCollection c) {

        try {
            withdraw(amount);
            c.clients.get(id).getAccount().deposit(amount);
        } catch (Exception e) {
            GUI err = new GUI();
            int errNum = 0;
            err.errorMessage(errNum);
        }
    }

    public boolean withdraw(float amount) {

        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be > 0!");
            }
            else if (amount > balance) {
                throw new IllegalArgumentException("You cannot overdraw!");
            }
            else {
                balance = balance - amount;
                return true;
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
