=begin 
This will handle the CRUD testing

Here are some sources that were heavily referenced

https://rspec.info/features/3-12/rspec-core/example-groups/basic-structure/
https://www.rubypigeon.com/posts/rspec-core-cheat-sheet/ 
https://dev.to/charliekroon/how-to-test-with-rspec-an-extensive-beginners-guide-93d 
=end

require_relative '../lib/TaskManager.rb'
require_relative '../lib/Task.rb'
require 'csv'

RSpec.describe TaskManager do

  before(:each) do 

    # Calling our file
    File.write('tasks.csv', '')

    # For each of our tests, this will run right before it to ensure the object created
    # is fresh and no lingering details from the previous tests below. 
    @crudmanager = TaskManager.new
  end

  # Here is a test case for adding a task and ensuring each task object gets a new ID
  describe "#addTask - creating a task" do
    it "Here we are adding a new task and also ensuring we increment the Task ID" do 
    taskOne = @crudmanager.addTask("Homework", "Pending")
    expect(taskOne.task_id).to eq(1)
    end
  end

  # Here we are viewing all tasks
  describe "#viewTasks - view a task" do 
    it "Here we return an empty list if no tasks are added" do
      expect(@crudmanager.viewTasks).to eq([])
    end
  end

  # Here we are going to update a task name
  describe "#updateStatus - updating the status of a task" do
    it "Update the task status of an existing task" do
      @crudmanager.addTask("Programming", "Open")
      
      result = @crudmanager.updateStatus(1,"Complete")
      updatedTask = @crudmanager.findTask(1)

      expect(result).to be true
      expect(updatedTask.task_status).to eq("Complete")
    end
  end
    # Here we are going ot try an update a task that does not exist by passing a TaskID that does not exist. 
    describe "Update a task that does not exist" do
    it "If we try to update a task status that does not exist, lets return false" do
      result = @crudmanager.updateStatus(10000,"Pending")
      expect(result).to be false
    end
  end

    # Here we are going to try an delete a task
    describe "#deleteTask - deleting a task" do
    it "Try to delete a task" do
      taskOne = @crudmanager.addTask("Programming", "Pending")
      taskTwo = @crudmanager.addTask("Operating Systems", "Complete")

      # Lets delete the second task 
      result = @crudmanager.deleteTask("2") # assuming ID of second task is 2
      expect(result).to be true
    end
  end

  # Here we are going to try to delete an invalid task
  describe "#deleteTask - returns false with an invalid ID" do
    it "returns false if the task is not valid" do
    @crudmanager.addTask("Programming", "Pending") # assume task id is one
    result = @crudmanager.deleteTask("3") # Try to delete task ID 1
    expect(result).to be false
  
  end
end
  # Here we are going to try to delete an invalid task
  describe "#deleteallTasks - deleting all tasks" do
    it "Try to delete all tasks" do
      taskOne = @crudmanager.addTask("Programming", "Pending") # assume task id is one
      taskTwo = @crudmanager.addTask("Operating Systems", "Complete") # assume task id is two

      @crudmanager.deleteallTasks
      expect(@crudmanager.tasks).to eq([])
    end
  end
end
