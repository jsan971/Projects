/ Pairing log 

## Session 1 09/03/2026
Driver: Jacob Sanchez / Individual

Work Completed : 
 - Wrote spec/task_spec.rb to test the core data we will be storing (task_id, task_name, task_status)
 - Simulated a test case 1 to verify instantion
 - Created the main core class called Task that stores attributes of the overall project which I also initialized to properly hold these values
 - I also leveraged attr_accessor which demonstrates metaprogramming (setter and getter functions) learned in chapter 2

# Session 2 - 09/03-06/2026
Driver : Jacob Sanchez / Individual

Work Completed : 
- Introduced file seperation for easier readability. Ex.) seperating classes into designated files. 
- Core application will be app.rb where we can then require those class files as needed. Keeping app.rb lightweight.
- Updated comments across the application for readbility. 
- Updated README.md
- Added a more comprehensive test to Task_spec.rb
- Added a test case for colorization of task statuses (Pending, In Progress, Complete)
- Updated GEM file to only include necessary gems needed to run the application

# Session 3 - 09/6-7/2026
Driver: Jacob Sanchcez / Individual
- Updated test cases in TaskNavigator_spec.rb 
- Updated test cases in Task_spec.rb
- Updated attributes for task class (task_id read only) and task_name and task_status as keyword arguments 

# Session 4 - 09/7-8/2026
Driver: Jacob Sanchcez / Individual
- Added test code for user input validation of the menu options, ensured values are within range and no special characters allowed
- Seperated our classes by files, run main application app.rb

# Session 5 - 09/10/2026
Driver: Jacob Sanchez / Individual
- Added a test case for confirmation of action during the CRUD setup (y/n)
- Developed the module for this in InputValidation.rb called ConfirmAction

# Session 6 - 09/11/2026
Driver: Jacob Sanchez / Individual

- Added test case for task status selection
- Added input validation for task status selection in InputValidation module file

Driving most of the program has been challening.  

# Session 7 - 09/15/2026

Discussion: Jacob Sanchez + Lee Houchin
Discussed next steps of the project and what is pending
- implement test cases for the crud setup TaskManager_spec.rb
- implement crud code in TaskManager.rb
- Figuring out how to store tasks created by the user, ex) arrays  and how to increment tasks for 
retrieval and contiuation. 
- Once we have the desired CRUD and data structures in place, we can start implementing our file storage.  

# Session 21 - 09/21/2026
Driver : Jacob Sanchez
Observer : Lee Houchin collaborated on data structures but I drove most of the implementation for these bits 
Completed
- Modified menu to simplify menu
- added ability to find a task based on ID (added test cases as well)
- added ability to update a task status (added test cases as well)
- Implemented the following abilities in app.rb
- Create a task
- View a task
- Update a status
- Implemented confirmation of changes when adding a new task or updating its status
