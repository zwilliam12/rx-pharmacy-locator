package com.company;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LocatorTest {

    /**
     * Expect a null return if given an empty {@link Pharmacy} list
     */
    @Test
    public void testFindClosestPharmacy_emptyPharmacyList() throws Exception {
        final List<Pharmacy> nullPharmacies = new ArrayList<>();
        assertNull(Locator.findClosestPharmacy(1.0, 1.0, nullPharmacies));
    }

    @Test
    public void testFindClosestPharmacy_oneClosest() throws Exception {
        final List<Pharmacy> pharmacies = new ArrayList<>(2);
        final Pharmacy expectedClosest = new Pharmacy.Builder()
                .name("ClosestRx").latitude(1.0).longitude(1.0).build();

        pharmacies.add(new Pharmacy.Builder().name("FarthestRx").latitude(2.0).longitude(2.0).build());
        pharmacies.add(expectedClosest);

        final PharmacyResponse expectedResponse = new PharmacyResponse(expectedClosest, 48.8202);
        final PharmacyResponse actualResponse = Locator.findClosestPharmacy(0.5,0.5, pharmacies);

        assertEquals(expectedResponse.getPharmacy(), actualResponse.getPharmacy());
        assertEquals(expectedResponse.getDistanceFromPatient(), actualResponse.getDistanceFromPatient(), 0.0001);
    }

    /**
     * If two {@link Pharmacy}s are equi-distant from the patient, return the first one in the list.
     */
    @Test
    public void testFindClosestPharmacy_twoClosest() throws Exception {
        final List<Pharmacy> pharmacies = new ArrayList<>(3);
        final Pharmacy expectedClosest1 = new Pharmacy.Builder().name("ClosestRx").latitude(1.0).longitude(1.0).build();
        final Pharmacy expectedClosest2 = expectedClosest1.copy().name("AlsoClosestRx").build();

        pharmacies.add(new Pharmacy.Builder().name("FarthestRx").latitude(2.0).longitude(2.0).build());
        pharmacies.add(expectedClosest1);
        pharmacies.add(expectedClosest2);

        final PharmacyResponse expectedResponse = new PharmacyResponse(expectedClosest1, 48.8202);
        final PharmacyResponse actualResponse = Locator.findClosestPharmacy(0.5,0.5, pharmacies);

        assertEquals(expectedResponse.getPharmacy(), actualResponse.getPharmacy());
        assertEquals(expectedResponse.getDistanceFromPatient(), actualResponse.getDistanceFromPatient(), 0.0001);
    }

    @Test
    public void testHaversineDistanceFromPatient_1stQuadrant() throws Exception {
        final double lat1 = 2.0;
        final double long1 = 3.0;
        final double lat2 = 7.0;
        final double long2 = 9.0;

        assertEquals(538.1766, Locator.haversineDistanceFromPatient(lat1, long1, lat2, long2), 0.0001);
    }

    @Test
    public void testHaversineDistanceFromPatient_2ndQuadrant() throws Exception {
        final double lat1 = 2.0;
        final double long1 = -3.0;
        final double lat2 = 7.0;
        final double long2 = -9.0;

        assertEquals(538.1766, Locator.haversineDistanceFromPatient(lat1, long1, lat2, long2), 0.0001);
    }

    @Test
    public void testHaversineDistanceFromPatient_3rdQuadrant() throws Exception {
        final double lat1 = -2.0;
        final double long1 = -3.0;
        final double lat2 = -7.0;
        final double long2 = -9.0;

        assertEquals(538.1766, Locator.haversineDistanceFromPatient(lat1, long1, lat2, long2), 0.0001);
    }

    @Test
    public void testHaversineDistanceFromPatient_4thQuadrant() throws Exception {
        final double lat1 = -2.0;
        final double long1 = 3.0;
        final double lat2 = -7.0;
        final double long2 = 9.0;

        assertEquals(538.1766, Locator.haversineDistanceFromPatient(lat1, long1, lat2, long2), 0.0001);
    }

    /**
     * This tests that the correct distance is calculated between two points in different quadrants.
     */
    @Test
    public void testHaversineDistanceFromPatient_crossQuadrant() throws Exception {
        final double lat1 = 5.0;
        final double long1 = -3.0;
        final double lat2 = -1.0;
        final double long2 = 7.0;

        assertEquals(804.5657, Locator.haversineDistanceFromPatient(lat1, long1, lat2, long2), 0.0001);
    }

    /**
     * This test checks that the resulting distance found should be independent of which point is considered
     * 'first' and which point is considered 'second'.
     */
    @Test
    public void testHaversineDistanceFromPatient_switchedPointsEquals() throws Exception {
        final double lat1 = 5.0;
        final double long1 = -3.0;
        final double lat2 = -1.0;
        final double long2 = 7.0;

        assertEquals(Locator.haversineDistanceFromPatient(lat1, long1, lat2, long2),
                Locator.haversineDistanceFromPatient(lat2, long2, lat1, long1),0.0001);
    }
}
