package Model;


public class Renter {

    private String name;
    private String phoneNr;

    public Renter (String name, String phoneNr, Observable o){
        this.name = name;
        this.phoneNr = phoneNr;

    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getName() {
        return name;
    }


}
