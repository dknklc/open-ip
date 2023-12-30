package com.dekandev.ip.controller;

import com.dekandev.ip.controller.validation.IpAddressConstraint;
import com.dekandev.ip.dto.IpDto;
import com.dekandev.ip.service.IpService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/ip")
@Validated
@Tag(name = "Open IP API v1", description = "Open IP API for filter by ip for current information related to this ip")
public class IpController {

    private final IpService ipService;

    public IpController(IpService ipService) {
        this.ipService = ipService;
    }


    @Operation(
            method = "GET",
            summary = "Open IP API summary",
            description = "Open IP API for filter by ip for current information related to this ip",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = IpDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "bad request",
                            content = @Content(schema = @Schema(hidden = true)
                            )
                    )
            }
    )
    @GetMapping("/{ip}")
    @RateLimiter(name = "basic")
    public ResponseEntity<IpDto> getCity(@PathVariable @IpAddressConstraint @NotBlank String ip) {
        return ResponseEntity.ok(ipService.getCityByIpAddress(ip));
    }
}
