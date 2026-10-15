package model;

/* This class creates our User model which we will use to encapsulate our data for our login. */

public class User {
   String username;
    String password;

    /** This is our Constructor for our User Class

     * @param username
     * @param password
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /** This method gets the username.
     This returns an string.
     @return returns username
     */
    public String getUsername(){
        return username;
    }
    /** This method sets the Customer_ID.
     This sets the Customer_ID parameter with our constructor.
     @param  username  The Customer_ID ID of our Customer.
     */
    public void setUsername(String username){
        this.username = username;
    }

    /** This method gets the password.
     This returns an String.
     @return returns password
     */
    public String getPassword(){
        return password;
    }

    /** This method gets the password.
     This returns an String.
     @return returns password
     */
    public void setPassword(String  password) {
        this.password = password;
    }
}
