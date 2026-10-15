# Here we are loading the Task.rb file so we are able to test the code Task Class
# This is necessary to run our test against the Task class
require_relative '../lib/Task.rb'


# This is a simple grouping for our class called Task
describe Task do

=begin 
This section test the creation of a task and it's different statuses!
We first have to create our task object to which we can assign dummy data 
such as an ID, TaskName and it's status. 

To ensure accuracy and data consistency, I made sure to include the different statuses 
available to the user. 

The colorization feature is tested in TaskNavigator_spec.rb


Here are some sources that were heavily referenced

https://rspec.info/features/3-12/rspec-core/example-groups/basic-structure/
https://www.rubypigeon.com/posts/rspec-core-cheat-sheet/ 
https://dev.to/charliekroon/how-to-test-with-rspec-an-extensive-beginners-guide-93d 
=end
    it 'Initialized task_id, task_name, and task_status of Complete' do
    
        # creating our task object, here we are simulating our Task object
        task = Task.new(1, 'Do operating systems homework', "Complete")
    
        # Here we are checking if the values were saved and are mapped correctly!
        # This helps with data consistency 
        expect(task.task_id).to eq(1)
        expect(task.task_name).to eq("Do operating systems homework")
        expect(task.task_status).to eq("Complete")
    end
    
        it 'Initialized task_id, task_name, and task_status of Pending' do
    
        # creating our task object, here we are simulating our Task object
        task = Task.new(1, 'Do operating systems homework', "Pending")
    
        # Here we are checking if the values were saved and are mapped correctly!
        # This helps with data consistency 
        expect(task.task_id).to eq(1)
        expect(task.task_name).to eq("Do operating systems homework")
        expect(task.task_status).to eq("Pending")
    end

    it 'Initialized task_id, task_name, and task_status of In Progress' do
    
        # creating our task object, here we are simulating our Task object
        task = Task.new(1, 'Do operating systems homework', "In Progress")
    
        # Here we are checking if the values were saved and are mapped correctly!
        # This helps with data consistency 
        expect(task.task_id).to eq(1)
        expect(task.task_name).to eq("Do operating systems homework")
        expect(task.task_status).to eq("In Progress")
    end
end