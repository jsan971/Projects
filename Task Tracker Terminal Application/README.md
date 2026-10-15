Application Name : doit
Contributors : Jacob Sanchez & Lee Houchin
Course : Software Engineering CSCE 606 700

This is a command-line application that was built using ruby. 

Core Features Include : 
- CRUD: Create, Read, Update, and Delete tasks
- Persistent Storage : Automatically writes tasks generated to tasks.csv to prevent the loss of data after adding tasks
- Task Status Tracking : The ability to add or modify a tasks status, the options include (Open, Pending, Complete)
- Color Coded Output : Better readability for task status in their with a specific color
    - Open = green
    - Pending - blue
    - Complete - Red

Data Validation : There is a dedicated module with reusable methods that will manage the data validation of the user input of tasks and statuses.

Classes : 
    - Task : This provides the details regarding the project and contributors
    - Manager : This class provides the CRUD environment for our tasks and status
    - TaskValidator : This is our module that will contain reusable methods to validate the user input of tasks or status

## Installation Instructions (assuming you are running windows (WSL), Linux, or MacOS)
git clone https://github.com/tamu-edu-students/CSCE606-700-Project1-Group6.git

cd application directory

## Ruby Requirements

This project requires the version of 3.4.10 

## Ubuntu / Windows WSL Installation

`sudo apt update`
`sudo apt install -y rbenv`
`rbenv install 3.4.10`

## Configure your environment

`rbenv local 3.4.10`

`gem install bundler`

`bundle install`

## How to run tests

To run all the tests. Uses the rspec version in our gemfile. 
`bundle exec rspec` 

## How to run tests - Validation files are in directory spec/
`rspec spec/example_spec.rb` 