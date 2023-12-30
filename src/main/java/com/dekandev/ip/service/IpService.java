package com.dekandev.ip.service;

import com.dekandev.ip.constants.Constants;
import com.dekandev.ip.dto.IpDto;
import com.dekandev.ip.dto.IpResponse;
import com.dekandev.ip.model.IpEntity;
import com.dekandev.ip.repository.IpRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"ips"})
public class IpService {

    private static final Logger logger = LoggerFactory.getLogger(IpService.class);
    private final IpRepository ipRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public IpService(IpRepository ipRepository, RestTemplate restTemplate) {
        this.ipRepository = ipRepository;
        this.restTemplate = restTemplate;
    }

    @Cacheable(key = "#ip")
    public IpDto getCityByIpAddress(String ip) {
        logger.info("Requested ip : " + ip);
        Optional<IpEntity> responseFromDb = ipRepository.findFirstByRequestedIpAddressOrderByResponseLocalTimeDesc(ip);

        return responseFromDb.map(requestedIp -> {
            if (requestedIp.getResponseLocalTime().isBefore(LocalDateTime.now().minusMinutes(30)))
                return IpDto.convert(getCityFromIpStack(ip));

            return IpDto.convert(requestedIp);
        }).orElseGet(() -> IpDto.convert(getCityFromIpStack(ip)));

    }

    private IpEntity getCityFromIpStack(String ip) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getIpStackUrl(ip), String.class);

        try {
            IpResponse ipResponse = objectMapper.readValue(responseEntity.getBody(), IpResponse.class);
            return saveIpEntity(ip, ipResponse);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private IpEntity saveIpEntity(String ip, IpResponse ipResponse) {
        IpEntity ipEntity = new IpEntity(ip,
                ipResponse.continentCode(),
                ipResponse.countryName(),
                ipResponse.city(),
                LocalDateTime.now()
                );
        return ipRepository.save(ipEntity);
    }

    private String getIpStackUrl(String ip) {
        return Constants.API_URL + ip + Constants.API_KEY;
    }

    @CacheEvict(allEntries = true)
    @PostConstruct
    @Scheduled(fixedRateString = "1800000") // (30 min) * (60 second/ 1 min) * (1000 ms/ 1 second) = 1800000 ms
    public void clearCache(){
        logger.info("Cache Cleared");
    }
}
