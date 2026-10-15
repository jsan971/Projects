=begin
This tests our task statuses that can be set to a specific color! 
    - Open = green
    - Pending - blue
    - Complete - Red
=end
require_relative '../lib/TaskNavigator'

require 'colorize' # to test our colorize feature for task statuses

RSpec.describe TaskNavigator do 
    describe '#colorizeTaskStatus' do

        # Here we just create a new navigator obj for each test
        let(:navigator) {TaskNavigator.new}

        it "If task status is Pending" do
            expectedColor = navigator.colorizeTaskStatus("Pending")
            expect(expectedColor).to eq("Pending".blue)
        end

        it "If task status is Open" do
            expectedColor = navigator.colorizeTaskStatus("Open")
            expect(expectedColor).to eq("Open".green)
        end

        it "If task status is Complete" do
            expectedColor = navigator.colorizeTaskStatus("Complete")
            expect(expectedColor).to eq("Complete".red)
        end
    end
end