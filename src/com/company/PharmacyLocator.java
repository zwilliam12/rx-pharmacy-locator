package com.company;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PharmacyLocator {
    public static void main(String[] args) throws IOException {
        System.out.println("\nHello, let's find the closest pharmacy to this patient!\n");

        BufferedReader reader = Files.newBufferedReader(Paths.get("../../../htmlTemplate.html"));
        String htmlString = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        String inputLine = "";
        String splitBy = ",";

        List<Pharmacy> formattedPharmacies = new ArrayList<>();
        List<String[]> unformattedPharmacies = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(args[2]));

        // Store all lines in an array so we can ignore the headings
        while ((inputLine = br.readLine()) != null) {
            String[] pharmacyLine = inputLine.split(splitBy);
            unformattedPharmacies.add(pharmacyLine);
        }

        // Ignoring the headings line
        unformattedPharmacies.remove(0);

        // Format each of the remaining lines into Pharmacy objects
        for (final String[] line : unformattedPharmacies) {
            formattedPharmacies.add(new Pharmacy.Builder()
                    .name(line[0].replaceAll("^\"|\"$", "").trim()) // Need to remove trailing whitespace
                    .address(line[1].replaceAll("^\"|\"$", ""))
                    .city(line[2].replaceAll("^\"|\"$", ""))
                    .state(line[3].replaceAll("^\"|\"$", ""))
                    .zipCode(line[4].replaceAll("^\"|\"$", ""))
                    .latitude(Double.parseDouble(line[5]))
                    .longitude(Double.parseDouble(line[6]))
                    .build());
        }

        // Return the closest pharmacy in a response object
        final Double patientLatitude = Double.parseDouble(args[0]);
        final Double patientLongitude = Double.parseDouble(args[1]);

        final PharmacyResponse response = Locator.findClosestPharmacy(patientLatitude, patientLongitude, formattedPharmacies);
        System.out.println("Closest Pharmacy: " + response.getNameAndAddressInfo());
        System.out.println("Distance From Patient: " + response.getDistanceFromPatient() + " miles");

        // For good measure, output into html google maps
        htmlString = htmlString.replace("$pharmacyLabel", "\"" + response.getPharmacy().getName() + "\"");
        htmlString = htmlString.replace("$pharmLat", response.getPharmacy().getLatitude().toString());
        htmlString = htmlString.replace("$pharmLong", response.getPharmacy().getLongitude().toString());
        htmlString = htmlString.replace("$patLat", patientLatitude.toString());
        htmlString = htmlString.replace("$patLong", patientLongitude.toString());

        BufferedWriter writer = new BufferedWriter(new FileWriter("../../../mapOutput.html"));
        writer.write(htmlString);
        writer.close();
    }
}