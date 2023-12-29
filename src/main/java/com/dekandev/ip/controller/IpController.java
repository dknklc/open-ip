package com.dekandev.ip.controller;

import com.dekandev.ip.controller.validation.IpAddressConstraint;
import com.dekandev.ip.dto.IpDto;
import com.dekandev.ip.service.IpService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/ip")
@Validated
public class IpController {

    private final IpService ipService;

    public IpController(IpService ipService) {
        this.ipService = ipService;
    }

    @GetMapping("/{ip}")
    public ResponseEntity<IpDto> getCity(@PathVariable @IpAddressConstraint @NotBlank String ip) {
        return ResponseEntity.ok(ipService.getCityByIpAddress(ip));
    }
}
