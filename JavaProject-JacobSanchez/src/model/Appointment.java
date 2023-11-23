

package model;

import javafx.scene.Node;
import javafx.scene.control.TableRow;

import java.sql.Timestamp;

/* This class creates our appointment model which will allow us to have a CRUD setup. */
public class Appointment {

    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private Timestamp Start;
    private Timestamp End;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;

    /** This is our constructor for our Appointment Class.
     *
     * @param Appointment_ID For Appointment_ID
     * @param Title For Title
     * @param Description For Description
     * @param Location For Location
     * @param Type For Type
     * @param Start For Start
     * @param End For End
     * @param Customer_ID For Customer_ID
     * @param User_ID For User_ID
     * @param Contact_ID For Contact_ID
     */
    public Appointment(int Appointment_ID, String Title, String Description, String Location,
                       String Type, Timestamp Start, Timestamp End, int Customer_ID, int User_ID, int Contact_ID){

        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
    }

    /** This method gets the Appointment_ID.
     This returns an integer.
      @return returns Appointment_ID
     */
    public int getAppointment_ID(){
        return Appointment_ID;
    }
    /** This method sets the Appointment_ID.
     This sets the Appointment_ID parameter with our constructor.
     @param  Appointment_ID  The Appointment ID of our appointment.
     */
    public void setAppointment_ID(int Appointment_ID){
        this.Appointment_ID = Appointment_ID;
    }
    /** This method gets the Title.
     This returns a String.
     @return returns Title
     */
    public String getTitle(){
        return Title;
    }
    /** This method sets the Title.
     This sets the Title parameter with our constructor.
     @param  Title  The title of our appointment.
     */
    public void setTitle(String Title){
        this.Title = Title;
    }
    /** This method gets the Description.
     This returns a String.
     @return returns Description.
     */
    public String getDescription(){
        return Description;
    }
    /** This method sets the Description.
     This sets the Description parameter with our constructor.
     @param  Description  The Description of our appointment.
     */
    public void setDescription(String Description){
        this.Description = Description;
    }
    /** This method gets the Location.
     This returns a String.
     @return returns Location.
     */
    public String getLocation(){
        return Location;
    }
    /** This method sets the Location.
     This sets the Location parameter with our constructor.
     @param  Location  The Location of our appointment.
     */
    public void setLocation(String Location){
        this.Location = Location;
    }
    /** This method gets the Type.
     This returns a String.
     @return returns Type.
     */
    public String getType(){
        return Type;
    }
    /** This method sets the Type.
     This sets the Type parameter with our constructor.
     @param  Type  The Type of appointment.
     */
    public void setType(String Type){
        this.Type = Type;
    }
    /** This method gets the Customer_ID.
     This returns an int.
     @return returns Customer_ID.
     */
    public int getCustomer_ID(){
        return Customer_ID;
    }
    /** This method sets the Customer_ID.
     This sets the Customer_ID parameter with our constructor.
     @param  Customer_ID of the appointment.
     */
    public void setCustomer_ID(int Customer_ID){
        this.Customer_ID = Customer_ID;
    }
    /** This method gets the User_ID.
     This returns an int.
     @return returns User_ID.
     */
    public int getUser_ID(){
        return User_ID;
    }
    /** This method sets the User_ID.
     This sets the User_ID parameter with our constructor.
     @param  User_ID of the appointment.
     */
    public void setUser_ID(int User_ID){
        this.User_ID = User_ID;
    }
    /** This method gets the Contact_ID.
     This returns an int.
     @return returns Contact_ID.
     */
    public int getContact_ID(){
        return Contact_ID;
    }
    /** This method sets the Contact_ID.
     This sets the Contact_ID parameter with our constructor.
     @param  Contact_ID of the appointment.
     */
    public void setContact_ID(int Contact_ID){
        this.Contact_ID = Contact_ID;
    }
    /** This method gets the Start timestamp.
     This returns a Timestamp.
     @return returns Start.
     */
    public Timestamp getStart(){
        return Start;
    }
    /** This method sets the Start timestamp.
     This sets the Start parameter with our constructor.
     @param  Start of the appointment.
     */
    public void setStart(Timestamp Start){
        this.Start = Start;
    }
    /** This method gets the End timestamp.
     This returns a Timestamp.
     @return returns End.
     */
    public Timestamp getEnd(){
        return End;
    }
    /** This method sets the End Timestamp.
     This sets the End parameter with our constructor.
     @param  End of the appointment.
     */
    public void setEnd(Timestamp End){
        this.End = End;
    }

}

