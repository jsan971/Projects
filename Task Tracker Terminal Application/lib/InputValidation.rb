# This module is responsible for our input validations from the user. 
module InputValidation 

    # For this method we ensure the 1-7 display menu options are valid before proceeding with the selected choice!
    # To keep things simple I decided to use standard if/elsif/else statements for the validation of the menu item selected. 
    # It is likely possible to include a range but I couldn't get the implementation exactly right. 
    def self.MenuInput(userinput)
       # This removed any additional spacing, ex) " 6" , "6 ", etc
       userinput = userinput.strip.downcase
    
       # ensuring the user input is within range of our menu
       if userinput == "1" || userinput == "2" || userinput == "3" || userinput == "4" || userinput == "5" || userinput == "6" 
           userinput # returns user input
       else 
            "Hey there, that is the wrong input, try again!"
       end
    end
    # This method is rather light. We are only testing yes / no for our CRUD environment
    # We expect will transform the input to lower case and strip spaces
    def self.ConfirmAction(confirminput)
        confirminput = confirminput.strip.downcase
        
        # Check if confirminput == y, if so, return
        if confirminput == 'y'
            confirminput # returns the confirm input of y
        # Check if confirminput == n, if so, return
        elsif confirminput == 'n'
            confirminput # returns the confirm input of n
        else 
            "Hey there, that is the wrong input, try again!"
        end
    end

    # This method is to validate the user input of the task status selection
    def self.ConfirmTaskStatus(confirmstatusinput)
        # removes any additonal spacing, downcase not necessary but included it anyways
        cleaninput = confirmstatusinput.strip.downcase

        if cleaninput == "1" || cleaninput == "2" || cleaninput == "3"
            cleaninput
        else
            "Hey there, that is the wrong input, try again!" 
        end
    end
end