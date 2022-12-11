# LittlePayCodingExercise

## How the hypothetical system works

Before boarding a bus at a bus stop, passengers tap their credit card (identified by the PAN, or Primary Account
Number) on a card reader. This is called a tap on. When the passenger gets off the bus, they tap their card
again. This is called a tap off. The amount to charge the passenger for the trip is determined by the stops where
the passenger tapped on and tapped off. The amount the passenger will be charged for the trip will be
determined as follows:

Trips between Stop 1 and Stop 2 cost $3.25
Trips between Stop 2 and Stop 3 cost $5.50
Trips between Stop 1 and Stop 3 cost $7.30

Note that the above prices apply for travel in either direction (e.g. a passenger can tap on at stop 1 and tap off
at stop 2, or they can tap on at stop 2 and can tap off at stop 1. In either case, they would be charged $3.25).

### Completed Trips
If a passenger taps on at one stop and taps off at another stop, this is called a complete trip. The amount the
passenger will be charged for the trip will be determined based on the table above. For example, if a passenger
taps on at stop 3 and taps off at stop 1, they will be charged $7.30.

### Incomplete trips
If a passenger taps on at one stop but forgets to tap off at another stop, this is called an incomplete trip. The
passenger will be charged the maximum amount for a trip from that stop to any other stop they could have
travelled to. For example, if a passenger taps on at Stop 2, but does not tap off, they could potentially have
travelled to either stop 1 ($3.25) or stop 3 ($5.50), so they will be charged the higher value of $5.50.

### Cancelled trips
If a passenger taps on and then taps off again at the same bus stop, this is called a cancelled trip. The
passenger will not be charged for the trip.

## The problem

Given an input file in CSV format containing a single tap on or tap off per line you will need to create an output
file containing trips made by customers.

taps.csv [input file]

You are welcome to assume that the input file is well formed and is not missing data.
Example input file:

```
ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN
1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559
2, 22-01-2018 13:05:00, OFF, Stop2, Company1, Bus37, 5500005555555559
```

trips.csv [output file]

You will need to match the tap ons and tap offs to create trips. You will need to determine how much to charge
for the trip based on whether it was complete, incomplete or cancelled and where the tap on and tap off
occurred. You will need to write the trips out to a file called trips.csv.

Example output file:

```
Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN,
Status
22-01-2018 13:00:00, 22-01-2018 13:05:00, 900, Stop1, Stop2, $3.25, Company1, B37,
5500005555555559, COMPLETED
```

## Assumptions
* Input data is expected to be curated and in the expected format. Code does not handle invalid data
* Trip is considered to be complete only if it contains consecutive taps of the given PAN are ON followed by OFF and both belong to same BusId, CompanyId
* If 2nd Tap is not OFF or does not belong to same Bus, Company, then it is considered to be on a different bus. Hence, previous tap ON will be charged full fee.
* Any tap which starts with OFF where no matching ON is present is considered an orphan tap and is charged full fee.
* Time difference between taps are not considered when checking the cancelled trip

## Usage

### Compile and build

Run following command to build
```
mvn clean package
```

This command will generate **target/LittlePay.jar** executable

### Test and Report
Run below commands for unit tests and generate report

```
mvn clean test
mvn jacoco:report 
```

Coverage report can be viewed in **/target/site/jacoco/index.html**

### Run the code

Below commands can be used for run the executables once code is compiled and built.
```
java -jar target/LittlePay.jar <path-to-input-csv> <path-to-output-csv>
```
