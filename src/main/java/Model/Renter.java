package Model;


public class Renter implements RenterInterface{

    private String name;
    private String phoneNr;

    /**
     * Creates an instance of a renter.
     * @param name name of the renter.
     * @param phoneNr phone number of the renter.
     */
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
