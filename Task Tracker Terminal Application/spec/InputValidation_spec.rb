require_relative '../lib/InputValidation.rb'
=begin
The initial testing below is to ensure the user input is within bounds of the menu
and no special characters are allowed. 

Here are some sources that were heavily referenced

https://rspec.info/features/3-12/rspec-core/example-groups/basic-structure/
https://www.rubypigeon.com/posts/rspec-core-cheat-sheet/ 
https://dev.to/charliekroon/how-to-test-with-rspec-an-extensive-beginners-guide-93d 
=end

RSpec.describe InputValidation do 
    # This describes the method inside our InputValidation Module
    describe ".MenuInput" do
        # Ensuring the user input is within bounds for when the user selects a menu item
        # We verify all 1-7 inputs and its expected value otherwise we throw an error as shown in the next test
        it "The user input is within bounds of our menu optionsn 1-7" do
            expect(InputValidation.MenuInput("1")).to eq("1")
            expect(InputValidation.MenuInput("2")).to eq("2")
            expect(InputValidation.MenuInput("3")).to eq("3")
            expect(InputValidation.MenuInput("4")).to eq("4")
            expect(InputValidation.MenuInput("5")).to eq("5")
            expect(InputValidation.MenuInput("6")).to eq("6")
        end
        # Ensure we cannot go out of bounds and no special characters when selecting a menu item
        it "Ensure there is an error if we are out of range and testing special characters" do
            expect(InputValidation.MenuInput("7")).to eq("Hey there, that is the wrong input, try again!")  
            expect(InputValidation.MenuInput("$")).to eq("Hey there, that is the wrong input, try again!")  
            expect(InputValidation.MenuInput("")).to eq("Hey there, that is the wrong input, try again!")  
            expect(InputValidation.MenuInput("0")).to eq("Hey there, that is the wrong input, try again!")  
        end
    end

    # This portion of the testing is confirming the user choice of y for yes and n for no!
    # Will be heavily used during the CRUD environment. Testing the ConfirmAction module directly
    describe ".ConfirmAction" do 

        it "This is to confirm the yes action"  do 
            expect(InputValidation.ConfirmAction("y")).to eq("y")
        end
        it "This is to confirm the no action" do
            expect(InputValidation.ConfirmAction("n")).to eq("n")
        end
        it "Ensure we cannot select anything or than y or n" do
            expect(InputValidation.ConfirmAction("1")).to eq("Hey there, that is the wrong input, try again!")  
            expect(InputValidation.ConfirmAction("$")).to eq("Hey there, that is the wrong input, try again!")  
            expect(InputValidation.ConfirmAction("")).to eq("Hey there, that is the wrong input, try again!")  
        end
    end

    # This will verify the the user selection of the task status! Similar logic to menu testing / validation logic
    # 1. Open
    # 2. Pending
    # 3. Complete
    describe ".ConfirmTaskStatus" do
        it "This is to confirm task status 1" do
            expect(InputValidation.ConfirmTaskStatus("1")).to eq("1")
        end
        it "This is to confirm task status 2" do
            expect(InputValidation.ConfirmTaskStatus("2")).to eq("2")
        end
        it "This is to confirm task status 3" do 
            expect(InputValidation.ConfirmTaskStatus("3")).to eq("3")
        end

        # Testing invalid inputs
        it "This is to ensure we cannot go out out of range or other characters" do 
            expect(InputValidation.ConfirmTaskStatus("0")).to eq("Hey there, that is the wrong input, try again!")  
            expect(InputValidation.ConfirmTaskStatus("$")).to eq("Hey there, that is the wrong input, try again!")  
            expect(InputValidation.ConfirmTaskStatus("4")).to eq("Hey there, that is the wrong input, try again!")  
            expect(InputValidation.ConfirmTaskStatus("")).to eq("Hey there, that is the wrong input, try again!")  
        end 
    end
end