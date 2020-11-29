package com.company;

/**
 * Class that contains the basic data attributes to describe a Pharmacy and its location.
 */
public class Pharmacy {

    // ===== Instance =============================================================================================

    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private Double latitude;
    private Double longitude;

    /** Internal constructor */
    private Pharmacy(final Builder builder) {
        name = builder.name;
        address = builder.address;
        city = builder.city;
        state = builder.state;
        zipCode = builder.zipCode;
        latitude = builder.latitude;;
        longitude = builder.longitude;
    }

    // ===== Getter Methods =======================================================================================

    /**
     * Returns the name for this Pharmacy object.
     *
     * @return name
     */
    public String getName() { return name; }

    /**
     * Returns the address for this Pharmacy object.
     *
     * @return address
     */
    public String getAddress() { return address; }

    /**
     * Returns the city for this Pharmacy object.
     *
     * @return city
     */
    public String getCity() { return city; }

    /**
     * Returns the state for this Pharmacy object.
     *
     * @return state
     */
    public String getState() { return state; }

    /**
     * Returns the zip code for this Pharmacy object.
     *
     * @return zipCode
     */
    public String getZipCode() { return zipCode; }

    /**
     * Returns the latitude for this Pharmacy object.
     *
     * @return latitude
     */
    public Double getLatitude() { return latitude; }

    /**
     * Returns the longitude for this Pharmacy object.
     *
     * @return longitude
     */
    public Double getLongitude() { return longitude; }

    // ===== Object Methods =======================================================================================

    /**
     * Creates a new {@link Builder} that is an exact copy of this {@link Pharmacy} object.
     */
    public Builder copy() { return new Builder(this); }

    // ===== Builder Class ========================================================================================

    /**
     * The builder for the {@link Pharmacy} class.
     */
    public static class Builder {
        private String name;
        private String address;
        private String city;
        private String state;
        private String zipCode;
        private Double latitude;
        private Double longitude;

        /**
         * The basic constructor for creating new {@link Pharmacy} objects.
         */
        public Builder() { }

        /**
         * The copy constructor for creating new {@link Pharmacy} objects.
         *
         * This constructor will copy all attributes for the given {@link Pharmacy} over to this builder. Calling
         * {@link #build()} on this builder without modifying any attributes will result in a new {@link Pharmacy}
         * object which is equal to the one provided.
         */
        public Builder(final Pharmacy copy) {
            name(copy.name);
            address(copy.address);
            city(copy.city);
            state(copy.state);
            zipCode(copy.zipCode);
            latitude(copy.latitude);
            longitude(copy.longitude);
        }

        /**
         * Sets the value of {@link Pharmacy#getName()}.
         *
         * @param name the name of the pharmacy
         * @return this builder instance for convenience.
         */
        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the value of {@link Pharmacy#getAddress()}.
         *
         * @param address the address of the pharmacy
         * @return this builder instance for convenience.
         */
        public Builder address(final String address) {
            this.address = address;
            return this;
        }

        /**
         * Sets the value of {@link Pharmacy#getCity()}.
         *
         * @param city the city of the pharmacy
         * @return this builder instance for convenience.
         */
        public Builder city(final String city) {
            this.city = city;
            return this;
        }

        /**
         * Sets the value of {@link Pharmacy#getState()}.
         *
         * @param state the state of the pharmacy
         * @return this builder instance for convenience.
         */
        public Builder state(final String state) {
            this.state = state;
            return this;
        }

        /**
         * Sets the value of {@link Pharmacy#getZipCode()}.
         *
         * @param zipCode the zipCode of the pharmacy
         * @return this builder instance for convenience.
         */
        public Builder zipCode(final String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        /**
         * Sets the value of {@link Pharmacy#getLatitude()}.
         *
         * @param latitude the latitude of the Pharmacy
         * @return this builder instance for convenience.
         */
        public Builder latitude(final Double latitude) {
            this.latitude = latitude;
            return this;
        }

        /**
         * Sets the value of {@link Pharmacy#getLongitude()}.
         *
         * @param longitude the longitude of the pharmacy
         * @return this builder instance for convenience.
         */
        public Builder longitude(final Double longitude) {
            this.longitude = longitude;
            return this;
        }

        /**
         * Constructs a new {@link Pharmacy} object with all of the values defined by this builder.
         */
        public Pharmacy build() { return new Pharmacy(this); }
    }
}
