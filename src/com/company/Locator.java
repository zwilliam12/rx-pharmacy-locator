package com.company;

import java.util.List;

/**
 * API for finding GPS location of closest Pharmacies to the patient.
 */
public class Locator {
    /**
     * Searches for the {@link Pharmacy} within the given {@link List<Pharmacy>} that is closest to the given latitude and longitude coordinates.
     * Note: If more than one pharmacies happen to be the same distance away, then choose the first as the closest.
     *
     * @param latitude
     * @param longitude
     * @param pharmacies
     * @return the closest {@link Pharmacy} or null if the given {@link List<Pharmacy>} is empty
     */
    public static PharmacyResponse findClosestPharmacy(final double latitude, final double longitude, final List<Pharmacy> pharmacies) {
        if (pharmacies.isEmpty()) {
            return null;
        }

        Pharmacy closest = pharmacies.remove(0);
        double closestDistance = haversineDistanceFromPatient(latitude, longitude, closest.getLatitude(), closest.getLongitude());

        // Test if each pharmacy is closer. If so, we have found a replacement closest
        for (final Pharmacy pharmacy : pharmacies) {
            final double newDistance = haversineDistanceFromPatient(latitude, longitude, pharmacy.getLatitude(), pharmacy.getLongitude());

            if (newDistance < closestDistance) {
                closest = pharmacy;
                closestDistance = newDistance;
            }
        }

        return new PharmacyResponse(closest, closestDistance);
    }

    /**
     * Uses the Haversine formula to find the orthodromic distance (in miles) between the two given points: a patient
     * and a pharmacy.
     *
     * Information on the Haversine Formula can be found at http://www.movable-type.co.uk/scripts/latlong.html
     *
     * @param lat1
     * @param long1
     * @param lat2
     * @param long2
     * @return the orthodromic distance
     */
    public static double haversineDistanceFromPatient(final double lat1, final double long1, final double lat2, final double long2) {
        // haversine(theta) = sin^2(theta/2)
        // distance = radiusOfEarth * arccos[(sin(lat1) * sin(lat2)) + cos(lat1) * cos(lat2) * cos(long2 – long1)]

        // Get the radian distances between each of the latitudes and longitudes
        final double radianLatDistance = Math.toRadians(lat2 - lat1);
        final double radianLongDistance = Math.toRadians(long2 - long1);

        // Apply the formula
        final double haversineLat = Math.pow(Math.sin(radianLatDistance / 2), 2);
        final double haversineLong = Math.pow(Math.sin(radianLongDistance / 2), 2);

        final double a = haversineLat + haversineLong * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        final double radiusOfEarthInMiles = 3956;
        final double c = 2 * Math.asin(Math.sqrt(a));

        return radiusOfEarthInMiles * c;
    }
}



/**
 * Packages the two outputs, Pharmacy object and distance from patient to Pharmacy object, into a response wrapper
 * object for consumer convenience.
 */
class PharmacyResponse {
    private final Pharmacy pharmacy;
    private final Double distanceFromPatient;

    PharmacyResponse(final Pharmacy pharmacy, final Double distanceFromPatient) {
        this.pharmacy = pharmacy;
        this.distanceFromPatient = distanceFromPatient;
    }

    // ===== Getter Methods ======================================================================================

    /**
     * Returns the name of the pharmacy for this PharmacyResponse object.
     *
     * @return pharmacyName
     */
    public String getNameAndAddressInfo() { return pharmacy.getName()
            + ", " + pharmacy.getAddress() + ", " + pharmacy.getCity() + ", " + pharmacy.getState() + " "
            + pharmacy.getZipCode(); }

    /**
     * Returns the pharmacy for this PharmacyResponse object.
     */
    public Pharmacy getPharmacy() { return pharmacy; }

    /**
     * Returns the distance from the patient for this PharmacyResponse object.
     *
     * @return distanceFromPatient
     */
    public Double getDistanceFromPatient() { return distanceFromPatient; }
}