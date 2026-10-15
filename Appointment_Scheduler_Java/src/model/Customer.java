package model;

/* This class creates our Customer model which will allow us to have a CRUD setup. */

public class Customer {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private int Division_ID;

    /** This is our Constructor for our Customer Class

     * @param Customer_ID Customer_ID
     * @param Customer_Name Customer_Name
     * @param Address Address
     * @param Postal_Code Postal_Code
     * @param Phone Phone
     * @param Division_ID Division_ID
     */
    public Customer(int Customer_ID, String Customer_Name, String Address, String Postal_Code,
                    String Phone, int Division_ID){
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Division_ID = Division_ID;
    }

    /** This method gets the Customer_ID.
     This returns an integer.
     @return returns Customer_ID
     */
    public int getCustomer_ID(){
        return Customer_ID;
    }
    /** This method sets the Customer_ID.
     This sets the Customer_ID parameter with our constructor.
     @param  Customer_ID  The Customer_ID ID of our Customer.
     */
    public void setCustomer_ID(int Customer_ID){
        this.Customer_ID = Customer_ID;
    }
    /** This method gets the Customer_Name.
     This returns an String.
     @return returns Customer_Name
     */
    public String getCustomer_Name(){
        return Customer_Name;
    }
    /** This method sets the Customer_Name.
     This sets the Customer_Name parameter with our constructor.
     @param  Customer_Name  The Customer_Name of our Customer.
     */
    public void setCustomer_Name(String Customer_Name){
        this.Customer_Name = Customer_Name;
    }
    /** This method gets the Address.
     This returns an String.
     @return returns Address
     */
    public String getAddress(){
        return Address;
    }
    /** This method sets the Address.
     This sets the Address parameter with our constructor.
     @param  Address  The Address of our Customer.
     */
    public void setAddress(String Address){
        this.Address = Address;
    }
    /** This method gets the Postal_Code.
     This returns an String.
     @return returns Postal_Code
     */
    public String getPostal_Code(){
        return Postal_Code;
    }
    /** This method sets the Postal_Code.
     This sets the Postal_Code parameter with our constructor.
     @param  Postal_Code  The Address of our Customer.
     */
    public void setPostal_Code(String Postal_Code){
        this.Postal_Code = Postal_Code;
    }
    /** This method gets the Phone.
     This returns an String.
     @return returns Phone
     */
    public String getPhone(){
        return Phone;
    }
    /** This method sets the Phone.
     This sets the Phone parameter with our constructor.
     @param  Phone  The Phone number of our Customer.
     */
    public void setPhone(String Phone){
        this.Phone = Phone;
    }
    /** This method gets the Division_ID.
     This returns an integer.
     @return returns Division_ID
     */
    public int getDivision_ID(){
        return Division_ID;
    }
    /** This method sets the Division_ID.
     This sets the Division_ID parameter with our constructor.
     @param  Division_ID  The Division_ID ID of our Customer.
     */
    public void setDivision_ID(int Division_ID){
        this.Division_ID = Division_ID;
    }
}
