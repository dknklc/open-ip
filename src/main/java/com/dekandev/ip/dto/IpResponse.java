package com.dekandev.ip.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IpResponse(
        String ip,
        String type,
        @JsonProperty("continent_code")
        String continentCode,
        @JsonProperty("continent_name")
        String continentName,
        @JsonProperty("country_code")
        String countryCode,
        @JsonProperty("country_name")
        String countryName,
        @JsonProperty("region_code")
        String regionCode,
        @JsonProperty("region_name")
        String regionName,
        String city
) { }
