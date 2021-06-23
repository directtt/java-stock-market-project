package bank;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String surname;
    private int id;
    private BrokerageAccount account;

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAccount(BrokerageAccount account) {
        this.account = account;
    }

    public BrokerageAccount getAccount() {
        return account;
    }


}
