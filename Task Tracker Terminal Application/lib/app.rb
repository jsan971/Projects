# These are just require relatives of our necessary classes and module
require_relative 'TaskNavigator'
require_relative 'Task'
require_relative 'InputValidation'
require_relative 'TaskManager'
require 'colorize'
# Creating our crud manager object
crudmanager = TaskManager.new

loop do 
    # Creating our navigator object and calling the instance method to display our menus
    navigator = TaskNavigator.new
    navigator.displayMenu

    # Here we start to grab the user input from the user 
    puts "\nSelect an option from the menu!"
    userinput = gets.chomp 

    # Here we get to leverage our InputValidation module to verify the user input is within range and no special characters
    # We also assign the return value in InputValidation to our ValidatedInput variable. 
    validatedInput = InputValidation.MenuInput(userinput) 

    # If the user entered one it means they created a task
    if validatedInput == "1"
        puts "Selected : Created a task\n"

        # Gathering user input of the task
        puts "Please enter a task name (case sensitive) : "
        getTask = gets.chomp

        # Getting the status selection from the user and verifying the status based on the menu!
        puts "\nNow lets enter a task status!"

        status = ""

        # Created loop do so we can retry the input until it is correct, once correct we can break out and set the status based on the input
        loop do 
            navigator.TaskStatusSelection
            confirmSelection = gets.chomp
            validateSelection = InputValidation.ConfirmTaskStatus(confirmSelection)

            if validateSelection == "1"
                status = "Open"
                break
            elsif validateSelection == "2"
                status = "Pending"
                break
            elsif validateSelection == "3"
                status = "Complete"
                break
            end
        end

            # Created a loop do so we can retry the input until correct, once correct we apply the changes  and call our addTask method
            loop do
                puts "Would you like to apply these changes? (y/n) " 
                confirmAction = gets.chomp
                validateAction = InputValidation.ConfirmAction(confirmAction)

                if validateAction == 'y'
                    puts "\nChanges have been applied. Go back to the menu to view tasks..\n"
                    crudmanager.addTask(getTask, status)
                    break
                elsif validateAction == 'n'
                    puts "\nChanges not appplied..\n"
                    break
                end
            end
    
    # This allows us to view all our tasks that have been created.
    elsif validatedInput == "2"
       puts "Selected View Tasks"
       crudmanager.viewTasks
       puts "Press any key to continue..."
       gets
       puts "***********************************************************************************************"

    elsif validatedInput == "3"
        puts "\nSelected Update a task status\n"
     
        puts "***********************************************************************************************"
        crudmanager.viewTasks
        puts "***********************************************************************************************"

        status =""
        puts "\nWhat is the task status you would like to update? Enter the Task ID"
        getTaskID = gets.chomp

        # Created loop do so we can retry the input until it is correct, once correct we can break out and set the status based on the input
        loop do 
            navigator.TaskStatusSelection
            confirmSelection = gets.chomp
            validateSelection = InputValidation.ConfirmTaskStatus(confirmSelection)

            if validateSelection == "1"
                status = "Open"
                break
            elsif validateSelection == "2"
                status = "Pending"
                break
            elsif validateSelection == "3"
                status = "Complete"
                break
            end
        end

         # Created a loop do so we can retry the input until correct, once correct we apply the changes  and call our addTask method
            loop do
                puts "Would you like to apply these changes? (y/n) " 
                confirmAction = gets.chomp
                validateAction = InputValidation.ConfirmAction(confirmAction)

                if validateAction == 'y'
                    processChanges = crudmanager.updateStatus(getTaskID, status)
                     # Whatever our method returns lets output to the terminal if there are issues or successful. 
                        if processChanges 
                            puts "\nProcessed changes! Task status has been updated"
                        else 
                            puts "\n Invalid Task ID!"
                        end
                    break
                elsif validateAction == 'n'
                    puts "\nChanges not appplied..\n"
                    break
                end
            end
        
    elsif validatedInput == "4"
        puts "\nSelected Delete a task\n"

        puts "***********************************************************************************************"
        crudmanager.viewTasks
        puts "***********************************************************************************************"

        status =""
        puts "\nWhat is the task you would like to delete? Enter the Task ID"
        getTaskID = gets.chomp

        # Created a loop do so we can retry the input until correct, once correct we apply the changes  and call our addTask method
        loop do
            puts "Are you sure you want to delete this task? (y/n) " 
            confirmAction = gets.chomp
            validateAction = InputValidation.ConfirmAction(confirmAction)

            if validateAction == 'y'
                processChanges = crudmanager.deleteTask(getTaskID)
                    # Whatever our method returns lets output to the terminal if there are issues or successful. 
                    if processChanges 
                        puts "\nProcessed changes! The task has been deleted"
                    else
                        puts "Sorry there was an error"
                    end
                    break
                elsif validateAction == 'n'
                    puts "\nChanges not appplied..\n"
                    break
                end
            end
    elsif validatedInput == "5"
        # Created a loop do so we can retry the input until correct, once correct we apply the changes  and call our addTask method
        loop do
            puts "Are you sure you want to delete all tasks? (y/n) " 
            confirmAction = gets.chomp
            validateAction = InputValidation.ConfirmAction(confirmAction)

            if validateAction == 'y'
                processChanges = crudmanager.deleteallTasks()
                puts "\nAll tasks have been cleared"
                    break
                elsif validateAction == 'n'
                    puts "\nChanges not appplied..\n"
                    break
                end
            end
    elsif validatedInput == "6"
        puts "It's not goodbye.. It's see you later :)"
        break
    end
end