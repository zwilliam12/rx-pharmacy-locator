# rx-pharmacy-locator
Basic API to locate the nearest pharmacy given latitude/longitude input

------TO RUN THE PROGRAM------

Prerequisites: Java jdk is installed and properly set in the user's PATH variable.

Steps:
1. Download the rx-pharmacy-locator project.
2. Within the command prompt, navigate into the rx-pharmacy-locator project, and then continue into the out/production/rx-pharmacy-locator directory.
3. To run the program, execute the following command:
   
        java com.company.PharmacyLocator patientLatitude patientLongitude absolutePharmaciesCsv
   
   where patientLatitude and patientLongitude are the latitude and longitude of the patient and absolutePharmaciesCsv is the absolute pathname of the csv file containing all          pharmacies.
4. The program will output the resulting closest pharmacy to the command prompt, and additionally will create a html file in the base level of the project, called htmlOutput.html.    This file can be opened in a web browser for viewing.
