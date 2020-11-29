package com.company;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PharmacyTest {

    // ===== Getter Methods ======================================================================================

    @Test
    public void testGetName_defaultValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().build();
        assertNull(pharmacy.getName());
    }

    @Test
    public void testGetName_assignedValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().name("1").build();
        assertEquals("1", pharmacy.getName());
    }

    @Test
    public void testGetAddress_defaultValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().build();
        assertNull(pharmacy.getAddress());
    }

    @Test
    public void testGetAddress_assignedValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().address("1").build();
        assertEquals("1", pharmacy.getAddress());
    }

    @Test
    public void testGetCity_defaultValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().build();
        assertNull(pharmacy.getCity());
    }

    @Test
    public void testGetCity_assignedValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().city("1").build();
        assertEquals("1", pharmacy.getCity());
    }

    @Test
    public void testGetState_defaultValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().build();
        assertNull(pharmacy.getState());
    }

    @Test
    public void testGetState_assignedValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().state("1").build();
        assertEquals("1", pharmacy.getState());
    }

    @Test
    public void testGetZipCode_defaultValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().build();
        assertNull(pharmacy.getZipCode());
    }

    @Test
    public void testGetZipCode_assignedValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().zipCode("1").build();
        assertEquals("1", pharmacy.getZipCode());
    }

    @Test
    public void testGetLatitude_defaultValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().build();
        assertNull(pharmacy.getLatitude());
    }

    @Test
    public void testGetLatitude_assignedValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().latitude(1.0).build();
        assertEquals(1.0, pharmacy.getLatitude(), 0.1);
    }

    @Test
    public void testGetLongitude_defaultValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().build();
        assertNull(pharmacy.getLongitude());
    }

    @Test
    public void testGetLongitude_assignedValue() throws Exception {
        final Pharmacy pharmacy = new Pharmacy.Builder().longitude(1.0).build();
        assertEquals(1.0, pharmacy.getLongitude(), 0.1);
    }

    // ===== Object Methods =======================================================================================

    @Test
    public void testCopy() throws Exception {
        final String testName = "test1";
        final String testAddress = "test2";
        final String testCity = "test3";
        final String testState = "test4";
        final String testZipCode = "test5";
        final double testLatitude = 1.0;
        final double testLongitude = 2.0;

        final Pharmacy original = new Pharmacy.Builder().name(testName).address(testAddress).city(testCity).state(testState)
                .zipCode(testZipCode).latitude(testLatitude).longitude(testLongitude).build();
        final Pharmacy copy = original.copy().build();

        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getAddress(), copy.getAddress());
        assertEquals(original.getCity(), copy.getCity());
        assertEquals(original.getState(), copy.getState());
        assertEquals(original.getZipCode(), copy.getZipCode());
        assertEquals(original.getLatitude(), copy.getLatitude());
        assertEquals(original.getLongitude(), copy.getLongitude());
    }
}
