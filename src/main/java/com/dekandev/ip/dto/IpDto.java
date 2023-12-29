package com.dekandev.ip.dto;

import com.dekandev.ip.model.IpEntity;

public record IpDto (
        String requestedIpAddress,
        String continentName,
        String countryName,
        String city
) {
    public static IpDto convert(IpEntity from) {
        return new IpDto(from.getRequestedIpAddress(), from.getContinentName(), from.getCountryName(), from.getCity());
    }
}
