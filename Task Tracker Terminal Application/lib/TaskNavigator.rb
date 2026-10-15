require_relative 'TaskNavigator'
require_relative 'Task'

=begin 
The purpose of this class is to display the main menu for our program.
We will also accept the user input from the user and call various methods from our other classes such as Task, TaskManager, 
and from our Module that will handle input validation. 
=end
class TaskNavigator

    # Displays the overall options available for this program.
    def displayMenu
        puts "\n******************************************"
        puts "Welcome to the doit! - Task Tracker\nTasks created will be added in tasks.csv"
        puts "******************************************\n"
        puts "1. Create a task"
        puts "2. View all tasks"
        puts "3. Update a status"
        puts "4. Delete a task"
        puts "5. Delete all tasks"
        puts "6. Quit"
    end

    # Menu options for TaskStatus
    # 1. Open
    # 2. Pending
    # 3. Complete
    def TaskStatusSelection()
        puts "Please select from the following options!"
        puts "1. Open"
        puts "2. Pending"
        puts "3. Complete" 
    end

    # Implementing colorize for our statuses
    # Open = green
    # Pending - blue
    # Complete - Red

    def colorizeTaskStatus(task_status)

        if task_status == "Pending"
            task_status.blue
        elsif task_status == "Open"
            task_status.green
        elsif task_status == "Complete"
            task_status.red
        end  
    end
end
