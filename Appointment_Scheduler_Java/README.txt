Title - C195 Project Appointment / Customer Record and Scheduler
Author : Jacob D. Sanchez jsan507@wgu.edu 5/10/2023
Application Version 1.2
IDE IntelliJ IDEA Community Edition 2021.1.3
JavaFX Version 17.0.1 Compatible with Java JDK 11.0.11
MySQL Connector Driver Version : mysql-connector-java-8.0.26

DIRECTIONS (How to run program):

Ensure project folder is located in path : C:\Users\LabUser\IdeaProjects\

Login Form

   You will be prompted to login, the username and password are the following options:
 - Username : admin Password : admin
 - Username : test Password : test

 On the login page it will also determine your location, ex) America/Denver
 Depending on your language settings the login form will also be converted
 into french if detected, that includes forms, labels, and error messages. To
 test manually, you may uncomment line 21 //Locale.setDefault(new Locale("fr")); from main.java file at the root of
 this app.

Customer Record Functionalities

    - Deleting customer records

        To perform this action, you will select the option 'view/update/delete' button
        on the left hand side under customer records column. You must select a row
        from the table view, once the row is selected, click the button 'Delete'.
        Due to foreign key constraints, the appointment record associated to the
        customer record will be deleted first before the customer record
        deletion is processed. An alert will show confirming that you want to perform this action or not.
        The Customer_ID will also be visible in a textfield but is disabled.

    - Updating and adding customer records

        To add a customer, click on the 'create' button under the customer records column.
        There will be text fields for customer name, address, postal code, and phone number.
        The customer ID will automatically be generated. First level divisions and Country
        options will be visible in two separate combo boxes. Depending on the country
        selection, first-level-divisions will automatically be filtered that are related to the
        country selection. Any fields or combo boxes missing will be tracked and will be alerted to enter all required fields.

        Note: The address text field should not include first-level division and country data.

        To update a customer, click on the 'view/update/delete' option. There will be a TableView
        displaying all original customer data. Once there is a desired customer you wish to update,
        simple select a row from the TableView and click on 'Details'. This will populate the text fields
        below the TableView. There will also be two combo boxes for both first-level-divisions and
        Country. Once the customer record is updated, there will be an alert showing that the customer
        has been updated. The TableView will also automatically refresh once the update is processed.


        Note: The address text field should not include first-level division and country data.

Appointment Scheduling / Creation

    - Scheduling an appointment

        To schedule or book an appointment, simply click on 'create' on the right hand side
        under the appointments column. The following will be recorded :
        Appointment_ID, title, description, location, contact, type,
        start date and time, end date and time, Customer_ID, and User_ID. There will be three combo boxes,
        one for Customer_ID, Contact, and User_ID. The Appointment_ID will automatically increment so that is
        not collected in the form. The Start Time and End Time fields must be in format : HH:MM:SS. This is
        noted within the application/gui as well. Any fields or combo boxes missing will be tracked and will be alerted
        to enter all required fields.


    - Updating an appointment record and schedule

        To update/view appointments, click on 'view/update/delete' button. There will be a TableView displaying
        all appointments. The following will be displayed in the TableView : Appointment_ID,
        title, description, location, contact, type, start date and time,
        end date and time, Customer_ID, and User_ID. The Start and End dates will also be displayed in local time zone.
        To update a specific appointment schedule, select a specific row from the TableView and then click on 'Details'.
        This will populate all original appointment information in the text fields below.
        There will also be three ComboBoxes, one for User_ID, contact and Customer_ID. Appointment_ID will also
        be visible but disabled in the textfield. Any fields or combo boxes missing will be tracked and will be alerted to
        enter all required fields.


        The Start Time and End Time fields must be in format : HH:MM:SS. This is
        noted within the application/gui as well.

    - Scheduling Functionality Views

        To filter appointments by current month, week or all, click on 'view/update/delete' button. There will be a
        TableView displaying all appointments. The following will be displayed in the TableView : Appointment_ID, title, description, location, contact, type, start date and time,
        end date and time, Customer_ID, and User_ID. Below the table view are three radio buttons, each
        option will filter the appointments table by the current month, week or by all. An alert will
        also be displayed confirming of the change.

    - Deleting / Canceling an appointment

        To Cancel or delete an appointment, click on 'view/update/delete' button. There will be a TableView displaying
        all appointments. The following will be displayed in the TableView : Appointment_ID,title,
        description, location, contact, type, start date and time,end date and time,
        Customer_ID, and User_ID. The Start and End dates will also be displayed in local time zone. To delete / cancel
        an appointment, select a specific row from the TableView and click on 'Delete Appointment'. You will be
        asked to confirm whether you want to delete the specific appointment record. This prompt will include
        the Appointment_ID and Type that had gotten cancelled/delete once processed.

Input Validation / Logical Checks

    - Login Validation

        Enter a username with incorrect password on the login form
            An alert will be shown that password did not match.
        Enter a username that does not exist on the login form
            An alert will be shown that the username was not found.

        - Scheduling Overlap

             To test schedule overlap on creation, simply click on 'create' on the right hand side
             under the appointments column and follow instructions above to create an appointment.
             Once the appointment is created, attempted to create a second appointment by clicking
             'create'. You will be alerted that there are overlapping times with an existing appointment.

             To test schedule overlap on updating, simple click on 'view/update/delete' on the right
             hand side under the appointments column. Select a row from the TableView and click on 'Details'.
             Fields will be populated with the exception of the ComboBoxes, once those are selected you may
             attempt to adjust the start date, start time and end time of a previous appointment. On 'Update'
             You will be alerted that there are overlapping times with an existing appointment.

        - Operating hours

             when scheduling or updating an appointment outside of business hours which is 8:00 a.m. to 10:00 p.m. EST,
             including weekends you will be alerted that it is an invalid time. To create or update appointment schedules,
             review instructions above.

        - 15 minute alert

            On the main page where the menu options are, an alert will be displayed/triggered if there is an appointment
            within 15 minutes of you localtime which may or may not be in est.

Reports

    - Total number of customer appointments by type and month

        On the main page, click on the 'Reports' button, there will be two combo boxes,
        one is for the month and the other is for the appointment type. Once those options are selected,
        an alert will be displayed showing the total, month and appointment type.

    - A schedule for each contact

         On the main page, click on the 'Reports' button, a TableView will be displayed with columns :
         appointment ID, title, type and description, start date and time, end date and time, and customer ID.
         Beneath, there is a combo box to select a contact, once the desired contact is selected the table view will
         automatically filter.

    - Total Customer count

         On the main page, click on the 'Reports' button, there is a `click` button that will count the total #
         of customers.

Track User Activity

       Login activity will be tracked in a LoginAcitivty.txt file located in the root of this application.
       This will track all unsuccessful and successful logins including timestamps.
-----------------------------------------------------


Description of additional report : This report will count the total number
of customers in the customers table.
