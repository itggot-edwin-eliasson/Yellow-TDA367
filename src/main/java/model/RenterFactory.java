package model;

public class RenterFactory {
    public RenterInterface createRenter(String renterName, String renterPhoneNumber){
        return new Renter(renterName, renterPhoneNumber);
    }
}
