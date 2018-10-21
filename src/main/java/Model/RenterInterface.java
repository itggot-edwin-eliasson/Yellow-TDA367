package Model;

import java.io.Serializable;

public interface RenterInterface extends Serializable {

    public void setPhoneNr(String phoneNr);

    public String getName();

    public String getPhoneNr();


    public void setName(String name);
}
