Feature: Add a task to the task list 
  As a user,
  So that I can capture a new task, 
  I want to add a new task to a task list
Scenario: Add a task
  Given I am in the doit application,
  When I want to add a task,
  Then I should I should enter the number for "Add a new task" from the main menu.
  Given I am in the task entry screen,
  Then I should enter the name of the task,
  Then I should enter the task status based on the task status menu,
  Finally, I respond 'y' if I am sure I want to add the task
  Or 'n' if I want to cancel the action.

Feature: Update a task in the task list
  As a user,
  So that I can update task status,
  I want to update a task in the task list
Scenario: Update a task
  Given I am in the doit application,
  When I want to update a task,
  Then I should I should enter the number for "Update a task" from the main menu.
  Given I am in the menu for updating tasks,
  When provide the task id from the list of tasks I want to update,
  Then I select the new status from the menu on the screen,
  Finally, I respond 'y' if I am sure I want to update the task
  Or 'n' if I want to cancel the action.

Feature: Review the task list
  As a user,
  So that I can review the complete list of tasks,
  I want to view all the tasks being managed
Scenario: List all managed tasks
  Given I am in the doit application,
  When I want to list all the tasks,
  Then I select "View a task" from the main menu

Feature: Deleting a task
  As a user,
  So that I can delete a task,
  I want to select the task I want to delete from the task list.
Scenario: Deleting a specific task
  Given I am in the doit application,
  When I want to delete a specific task, 
  Then I select "Delete a task" from the main menu.
  Given I am at the prompt askin for the task ID
  Then I enter the task ID for the task.
  When asked to confirm the deletion,
  Finally, I respond 'y' if I am sure I want to delete the task
  Or 'n' if I want to cancel the action.

Feature: Delete all tasks
  As a user,
  So I can clear all tasks,
  I want to delete all tasks in the list.
Scenario: Delete all tasks
  Given I am in the doit application,
  When I want to clear the task list,
  Then I select "Delete all tasks" from the main menu.
  Given I am prompted to confirm deleting all tasks,
  Then I respond 'y' if I am sure I want to clear all tasks
  Or 'n' if I want to cancel the action.

Feature: Save task list to file
  As a user,
  So I can save the current tasks after the application is closed,
  I want to save all the tasks to a file.
Scenario: Saving the task list to file
  Given I am in the doit application,
  When I want to save all the tasks,
  Then I select "Save task list to file" from the main menu
  Given a prompt to enter the name of the file,
  Then I enter the name to save the task list.

Feature: Load task list into the app
  As a user,
  So I can continue to work on my saved task list,
  I want to load the task list from file.
Scenario: Loading a task list
  Given I am in the doit application,
  When I want to load a task list,
  Then I select "Load a task list" from the main menu.
  Given a prompt to enter the name of the file,
  Then I enter the name to load the task list from file.
  Finally, the list of tasks is displayed after loading is complete.

Feature: I want to quit the application
  As a user,
  So that I can end my session,
  I want to quit the application.
Scenario: Quitting the application
  Given I am in the doit application,
  When I want to close the application,
  Then I select "Quit" from the main menu.
