# Jacob D. Sanchez Student ID 001242735
import csv
import datetime

# Hash Table Using Chaining Class
class ChainingHashtable:
    # Constructor with optional initial capacity parameter.
    # Assigns all buckets with an empty list.
    # Space Complexity O(1)
    # Time Complexity O(1)
    def __init__(self, initial_capacity = 10):
        # initialize the hash table with empty bucket list entries.
        self.table = []
        for i in range (initial_capacity):
            self.table.append([])

    # Inserts a new item into the hash table.
    # Space Complexity O(1)
    # Time Complexity O(1)
    def insert(self, key, item): # does insert and update
        # get the bucket list where this item will go.
        bucket = hash (key) % len(self.table)
        bucket_list = self.table[bucket]

        # Update key if it is already in the bucket
        for kv in bucket_list:
            #print (key_value)
            if kv[0] == key:
               kv[1] = item
               return True

        # if not, insert the item to the end of the bucket list.
        key_value = [key, item]
        bucket_list.append(key_value)
        return True

    # Searches for an item with a matching key in the hash table.
    # Returns the item if found, or None if not found.
    #Space Complexity: O (N)
    #Time Complexity: O (N)
    def search(self, key):
        # get the bucket list where this key would be.
        bucket = hash (key) % len (self.table)
        bucket_list = self.table[bucket]

        # Search for the key in the bucket list
        for kv in bucket_list:
            #print (key_value)
            if kv[0] == key:
                return kv[1] # value
        return None

    # Removes an item with a matching key from the hash table.
    # Time Complexity: O (N)
    #
    # Space Complexity: O (N)
    def remove(self, key):
        # get the bucket list where this item will be removed from.
        bucket = hash(key) % len(self.table)
        bucket_list = self.table[bucket]

        # remove the item from the bucket list if it is present.
        for kv in bucket_list:
            # print (ley_value)
            if kv[0] == key:
                bucket_list.remove([kv[0], kv[1]])

# Hash Table Instance - Works with conjunction with our loadPackageData and chaining Hash Table class.
# Time complexity O(1)
# Space Complexity O(N)
PackageHash = ChainingHashtable()

#This creates our class for packages

class Package:
    # Space Complexity O(1)
    # Time Complexity O(1)
    def __init__(self, PackageID, Address, City, State, Zip, DeliveryDeadline, Weight, Notes, Status):
        self.PackageID = PackageID
        self.Address = Address
        self.City = City
        self.State = State
        self.Zip = Zip
        self.DeliveryDeadline = DeliveryDeadline
        self.Weight = Weight
        self.Notes = Notes
        self.Status = Status
        self.DepartureTime = None
        self.DeliveryTime = None

    # Space Complexity O(1)
    # Time Complexity O(1)
    def __str__(self):
        return "Package ID: %s, Address: %s %s %s %s, Deadline: %s, Weight: %s , Status: %s" % (self.PackageID, self.Address,self.City, self.State, self.Zip, self.DeliveryDeadline, self.Weight, self.Status)

    # This will allow us to update our packages.
    # Time Complexity O(1)
    # Space Complexity O(1)
    def packageStatus(self, timeOption):
        if self.DeliveryTime < timeOption:
            self.Status = "Delivered on {}".format(self.DeliveryTime)
        elif self.DepartureTime > timeOption:
            self.Status = "At Hub"
        else:
            self.Status = "On The Way"
        if timeOption >= datetime.timedelta(hours=int(10), minutes=int(20), seconds=int(00)) and self.PackageID == 9:
            self.Address = "410 S State St"
            self.City = "Salt Lake City"
            self.State = "UT"
            self.Zip = "84111"

# This will load the package file csv into our hash table
# Time Complexity O(N)
# Space Complexity O(N)
def loadPackageData(wgupspackagefile, PackageHash):
    with open(wgupspackagefile) as Packages:
        packageData = csv.reader(Packages, delimiter=',')
        next(packageData) # skipping header
        for package in packageData:
            PackageID = int(package[0])
            Address = package[1]
            City = package[2]
            State = package[3]
            Zip = package[4]
            DeliveryDeadline = package[5]
            Weight = package[6]
            Notes = package[7]
            Status = 'Hub Location'

            # Package Object
            packageObject = Package(PackageID, Address, City, State, Zip, DeliveryDeadline,Weight,Notes,Status)

            PackageHash.insert(PackageID, packageObject)

# Load Packages in Hash Table
# Time Complexity O(N)
# Space Complexity O(N)
loadPackageData('wgupspackagefile.csv', PackageHash)

# Time complexity : O(1) Space Complexity : O(1)
class Truck:
    def __init__(self, TruckAddress, TruckSpeed, TruckPackages, TruckTimeRemaining):
        self.TrackMiles = 0.0
        self.TruckAddress = TruckAddress
        self.TruckSpeed = TruckSpeed
        self.TruckPackages = TruckPackages
        self.TruckTimeRemaining = TruckTimeRemaining
        self.Time = TruckTimeRemaining

# this will calculate the distance between two addresses
# Time Complexity O(1)
# Space Complexity O(1)
def distanceBetween(address1, address2):
    try:
        index1 = addressData.index(address1)
        index2 = addressData.index(address2)
        distance_str = distanceData[index1][index2]

        if distance_str:
            distance = float(distance_str)
            return distance
        else:
            return 0.0  # Return None for empty distances
    except (ValueError, IndexError):
        return 0.0

# This will get the min distance from the current location and truck packages
# Time Complexity O(N)
# Space Complexity O (1)
def minDistance(currentAddress, truckPackages):
    minDist = float('inf')
    addressNotVisited = ''

    for pID in truckPackages:
        packageObject = PackageHash.search(pID)
        address = packageObject.Address

        dist = distanceBetween(currentAddress, address)

        if dist < minDist:
            minDist = dist
            addressNotVisited = address
            getPackageID = pID

    return addressNotVisited, getPackageID, minDist

# This will deliver packages and the best route to take with the greedy algorithm
# Time Complexity O(N^2)
# Space Complexity O(1)
def truckDeliverPackages(truck):
    while len(truck.TruckPackages) > 0:
        addressNotVisited, getPackageID, getDist = minDistance(truck.TruckAddress, truck.TruckPackages)
        truck.TrackMiles = truck.TrackMiles + getDist

        DeliveryTime = (getDist / truck.TruckSpeed) * 60 * 60

        var = datetime.timedelta(seconds=DeliveryTime)

        truck.Time = truck.Time + var

        packageObject = PackageHash.search(getPackageID)

        packageObject.DeliveryTime = truck.Time

        packageObject.DepartureTime = truck.TruckTimeRemaining

        truck.TruckPackages.remove(getPackageID)
        truck.TruckAddress = addressNotVisited

    return truck.TrackMiles

# This reads our distance csv file and loads it in
# Time Complexity O (N)
# Space Complexity O (N)
def loadDistanceData(distancesCSV, distanceData):
    with open(distancesCSV) as csvfile:
        read_csv = csv.reader(csvfile, delimiter=',')
        for row in read_csv:
            distanceData.append(row)

# This reads our address data and loads it in
# Time Complexity O (N)
# Space Complexity O (N)
def loadAddressData(addressCSV, addressList):
    with open (addressCSV) as Addresses:
        addressReader = csv.reader(Addresses)
        for row in addressReader:
            address = row[1]
            addressList.append(address)

# Creating a list to load distance data
#Time Complexity O(1)
#Space Complexity O(1)
distanceData = []

#Time Complexity O(N)
#Space Complexity O(N)
loadDistanceData("wgupsdistances.csv", distanceData)

# Creating an address list to load our address data
#Time Complexity O(1)
#Space Complexity O(1)
addressData = []
loadAddressData("wgupsaddresses.csv", addressData)

# This is where we are loading truck one, we are calling in our Truck class to pass values
#Time Complexity O(1)
#Space Complexity O(1)
TruckOnePackages = Truck("4001 South 700 East", 18, [1, 13, 35, 39, 19,37,29, 30, 31, 34, 20, 40, 16, 14, 15, 10], datetime.timedelta(hours=8,minutes=0, seconds=0))
#Time Complexity O(1)
#Space Complexity O(1)
TruckOneMiles = round(truckDeliverPackages(TruckOnePackages), 2)

# This is where we are loading truck two, we are calling in our Truck class to pass values
#Time Complexity O(1)
#Space Complexity O(1)
TruckTwoPackages = Truck("4001 South 700 East", 18, [36, 25, 38, 28, 6, 18, 3, 32], datetime.timedelta(hours=9,minutes=43, seconds=0))

#Time Complexity O(1)
#Space Complexity O(1)
TruckTwoMiles = round(truckDeliverPackages(TruckTwoPackages),2)

#Time Complexity O(1)
#Space Complexity O(1)
# This is where we are loading truck three, we are calling in our Truck class to pass values
TruckThreePackages = Truck("4001 South 700 East", 18, [2, 11, 5, 7, 8, 9, 26, 12, 24, 21, 23, 22, 17, 4, 27, 33], datetime.timedelta(hours=11,minutes=0, seconds=0))

#Time Complexity O(1)
#Space Complexity O(1)
TruckThreeMiles = round(truckDeliverPackages(TruckThreePackages),2)

#Time Complexity O(1)
#Space Complexity O(1)
TotalTruckMiles = round(TruckOneMiles + TruckTwoMiles + TruckThreeMiles,2) # calculating the total truck miles of the trucks

# This is our lookup function
# Time Complexity O(N)
while True:

    print("Total Mileage was   : ", TotalTruckMiles,"\n")
    print("Truck one mileage   : ", TruckOneMiles, "Completed Delivery at : ", TruckOnePackages.Time,"\n")
    print("Truck two mileage   : ", TruckTwoMiles, "Completed Delivery at : ", TruckTwoPackages.Time, "\n")
    print("Truck three mileage : ", TruckThreeMiles,"Completed Delivery at : ", TruckThreePackages.Time, "\n")

    user_input = input("Note: Please type 'Y' to continue or 'N' to exit: ").strip().lower()
    if user_input != 'y' :
        print("Goodbye.")
        exit()
    elif user_input == 'y':
        try:
            # Ask the user for a specific time based on a certain format
            InputTime = input("Enter a time to check package status (HH:MM:SS) format required : ")
            (h,m,s) = InputTime.split(":")
            convert_timedelta = datetime.timedelta(hours=int(h), minutes=int(m), seconds=int(s))

            # This will ask the user if they want to see all packages or a single package
            SecondChoice = input("All Packages (Type 'all' then enter) for Single Package (Type 'single' then enter) : ").lower()

            # If the user selects single, a single package will be displayed
            if SecondChoice == 'single':
                try:
                    # Here we specify the range of the package ids to be entered, if invalid, the program will exit
                    PackageIDInput = input("Enter a package ID (1-40 ONLY) : ")
                    package = PackageHash.search(int(PackageIDInput))
                    package.packageStatus(convert_timedelta)
                    print("\n")
                    print(str(package))
                    print("\n")
                except ValueError:
                    print("Sorry, Incorrect input,  Goodbye.")
                    exit()
            # If the user selects all, all packages will be displayed for that time frame
            elif SecondChoice == 'all':
                try:
                    print("\n")
                    # Time Complexity O(N)
                    # Space Complexity O(1)
                    for PackageID in range(1,41):
                        package = PackageHash.search(PackageID)
                        package.packageStatus(convert_timedelta)
                        print(PackageHash.search(PackageID))
                    print("\n")
                except ValueError:
                    print("Sorry, Incorrect input,  Goodbye.")
                    exit()
            else:
                print("Sorry, Incorrect input,  Goodbye.")
                exit()
        except ValueError:
            print("Sorry, Incorrect input,  Goodbye.")
            exit()