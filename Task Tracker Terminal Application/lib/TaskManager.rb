=begin 
This willl handle the CRUD (create read update delete) 

https://www.rubyguides.com/2015/05/working-with-files-ruby/ 
https://docs.ruby-lang.org/en/master/IO.html
https://docs.ruby-lang.org/en/master/File.html 
https://www.youtube.com/watch?v=W5C1PKODOgU
https://www.youtube.com/watch?v=92li0A8d4io
=end 
require_relative 'Task' 
require_relative 'TaskNavigator' 
require 'colorize' 
require 'csv' 

class TaskManager 
 
    def initialize 
        @tasks = [] 
        @nextTaskID = 1 

        # Load existing tasks from our CSV file when the application starts
        if File.exist?('tasks.csv')

        CSV.foreach('tasks.csv') do |row|

        task_id = row[0].to_i
        task_name = row[1]
        task_status = row[2]

        # Create the Task object and add it to our tasks array
        existingTask = Task.new(task_id, task_name, task_status)
        @tasks << existingTask

        # Make sure our next ID starts after the highest existing ID
        if task_id >= @nextTaskID
            @nextTaskID = task_id + 1
        end
    end
    end
    end  

    attr_reader :tasks    

    # Creating a task 
    def addTask(task_name, task_status) 
     
        newTask = Task.new(@nextTaskID, task_name, task_status) # creating our newTask object 
        @tasks << newTask # This saves the new entry into our tasks list, neat huh :) This is like @tasks.push(newTask) 
         
        CSV.open('tasks.csv', 'a') do |csv| 
            csv << [@nextTaskID, task_name, task_status]
        end 

        @nextTaskID = @nextTaskID + 1  
 
        newTask # returning the task object  
 
    end 
 
    # View tasks 
    def viewTasks 
 
        navigator = TaskNavigator.new 
 
        CSV.foreach('tasks.csv') do |task| 
            task_id = task[0].to_i 
            task_name = task[1] 
            task_status = task[2] 
 
            displayColorStatus = navigator.colorizeTaskStatus(task_status) 
 
            puts "Task ID #{task_id} | Task Name : #{task_name} | Task Status: #{displayColorStatus}"     
        end 
 
        @tasks 
    end 
 
    # This method allows us to find the associated task_id in the array we made earlier @tasks 
    # and iterates until the task_id is matched. We leverage this method in our updateStatus method 
 
    def findTask(task_id) 
        @tasks.find { |task| task.task_id == task_id.to_i} 
    end 
 
    # This method allows us to update our status of a specific task based on the ID 
    def updateStatus()
        # Here we call our findTask method and pass our task_id to see if it can be found in the list  
        # and store the output in task, if true, lets update the task_status with the new status! 
        taskStatusToUpdate = findTask(task_id) 
        if taskStatusToUpdate 
            taskStatusToUpdate.task_status = updated_status 

            # Rewrite the CSV file with the updated task status
            CSV.open('tasks.csv', 'w') do |csv|
                @tasks.each do |task|
                    csv << [task.task_id, task.task_name, task.task_status]
                end
            end

            return true 
        else  
            return false 
        end 
    end 
 
    # Delete our task when sending the task_id 
    def deleteTask(task_id) 
        taskDelete = findTask(task_id) # Checking to see if the ID is available in our array 

        if taskDelete # if its true, lets delete the entry 

            @tasks.delete(taskDelete)

            # Rewrite the CSV file without the deleted task
            CSV.open('tasks.csv', 'w') do |csv|
                @tasks.each do |task|
                    csv << [task.task_id, task.task_name, task.task_status]
                end
            end

            return true 
        else 
            return false 
        end 
    end 
 
    # Deleting all tasks 
    def deleteallTasks 
        @tasks.clear # Removes all the elements in the array 

        # Rewrite the CSV file so all tasks are deleted
        CSV.open('tasks.csv', 'w') do |csv|
        end
    end
end 