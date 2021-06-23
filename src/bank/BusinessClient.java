package bank;

public class BusinessClient extends Client{

    private String nip;

    public BusinessClient(String name, String surname, String nip) {
        super(name, surname);
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }
}
