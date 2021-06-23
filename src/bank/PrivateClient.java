package bank;

public class PrivateClient extends  Client{

    private String pesel;

    public PrivateClient(String name, String surname, String pesel) {
        super(name, surname);
        this.pesel = pesel;
    }

    public String getPesel() {
        return pesel;
    }
}
