## Backlog

09/27/2026
Application is complete. 

Features available 
1. Create a task
2. View a task
3. Update a status
4. Delete a task
5. Delete all tasks
6. Quit

Status color features
- Open = green
- Pending - blue
- Complete - Red

Persistent Storage solution
- Leveraged CSV as ruby had more built in features that worked better with our objects versus a file
and allowing us to dissect the values from our csv. 



09/21/2026
To Do:

Delete a task (Pending test case and Implementation)
Delete all tasks (Pending test case and Implementation)
Implement file storage feature
Implement test case for colorized statuses when viewing tasks statuses
The final stretch we also need to see if we can squeeze in the user stories, @LLeeHouchin I know you had mentioned having experience with the user stories, I may lean on you for that.

Here is where we are.

The options from 1-3 are complete (program also quits gracefully when entering 6):

1. Create a task
2. View a task
3. Update a status
4. Delete a task
5. Delete all tasks
6. Quit

Whenever the user tries to update a task status or updates a tasks status, it will confirm with the user whether they want to apply those changes. I also went back and removed the RasieError and introduced a more user friendly approach where if they are out of bounds or special chars it will loop until they enter correctly. Test cases have also been updated accordingly.


---------------------------
09/11/2026

Complete: 
- Built test case for task status selection
- Built validation for task status in module InputValidation
- Built a light test case just to ensure task name cannot be empty

To Do 
- Design user stories (last)
- Once these cases are setup we can dive into the CRUD code 
- Test cases for CRUD setup (Create update delete)

---------------------------
09/10/2026
To Do
- Build out the test cases for the task statuses (Pending, In Progres, Complete)
- Build out the test case for the task name but emphasize descriptions are case sensitive
- Test cases for CRUD setup (Create update delete)
- Once these cases are setup we can dive into the CRUD code 
- Design user stories (last)

Complete
- Built test case for yes/no during confirmation of action during the CRUD
- Developed module in InputValidation.rb to handle the yes/no during confirmation of action during the CRUD
---------------------------
09/07-08/2026
To Do:
- building test cases for input validation (yes/no) 
- once test cases for input validation regarding confirmation, we will need to additional validation for our task statuses 
and a small test when it comes to inputing a task, ensuring no special characters or empty spaces. 

Complete: 
- Built test case for input validation for user menu
- Seperated classes by file for organization

---------------------------
09/06/2026
To Do:
Continue building test cases for user input / validation
Implement CRUD environment in TaskManager.rb
Implement file storing capabilities
Build user stories

Complete : 

- Test case for colorization of statuses
- Test case for the Task class and its attributes that we will use to store our data for. 
    ex) Pending 
        Complete 
        In progress
- Menu design has been created in TaskNavigator
- Implementation of colors for task status
    ex) Pending = green, 
        Complete = red
        In progress = blue

---------------------------
09/04/2026
- To Do
Work on determing how to implement file handling in ruby
- Drafting our Manager and Menu class
- Drafting our Module for TaskValidation
- User Stories
- Test Cases
- Feature Ideas (stretch)

---------------------------
The backlog should include:

User stories or feature ideas
Task breakdowns for implementation
Status of work items, such as:
To Do
In Progress
Done