package com.example.customers.datalayer;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@EqualsAndHashCode
@Getter
public class BillingAddress {

    private String streetAddress;
    private String postalCode;
    private String city;
    private String province;

    public BillingAddress() {
    }

    public BillingAddress(@NotNull String streetAddress, @NotNull String postalCode, @NotNull String city, @NotNull String province) {

        Objects.requireNonNull(this.streetAddress = streetAddress);
        Objects.requireNonNull(this.postalCode = postalCode);
        Objects.requireNonNull(this.city = city);
        Objects.requireNonNull(this.province = province);
    }

}
