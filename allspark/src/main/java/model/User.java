package model;

import java.math.BigInteger;

/**
 * Created by Eric on 4/8/16.
 */
public class User {

    private BigInteger id;

    private String firstName;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
