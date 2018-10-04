package Model;


public class Renter implements RenterInterface{

    private String name;
    private String phoneNr;

    public Renter (String name, String phoneNr){
        this.name = name;
        this.phoneNr = phoneNr;
    }

    @Override
    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhoneNr(){return phoneNr;}

    @Override
    public void setName(String name) {this.name = name;}
}
