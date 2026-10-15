require_relative 'TaskNavigator'
=begin
The purpose of this class to to be lightweight.

This class only sets/gets data for two attributes using attr_accessor
    - task_name, and task_status
    - task_id will remain read only
No additional logic is added here but is crucial for the whole project to run. 
=end
class Task 
    
    # Our default constructor 
    # When we create our Task object, we don't want there to be any attributes that are empty. 
    def initialize(task_id, task_name, task_status)
      @task_id = task_id
      @task_name = task_name
      @task_status = task_status
    end
    
    # We use attr_accessor which act like our setter and getter functions.
    # These will be heavily used for our TaskValidator module (Input Validation) and Manager class (CRUD) with the exception of the task_id this is read only
    attr_accessor :task_name
    attr_accessor :task_status
    attr_reader :task_id
end
