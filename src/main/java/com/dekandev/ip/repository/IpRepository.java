package com.dekandev.ip.repository;

import com.dekandev.ip.model.IpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IpRepository extends JpaRepository<IpEntity, String> {
    // select * from IpEntity where requestedIpAddress = ip order by updatedTime desc limit 1;
    Optional<IpEntity> findFirstByRequestedIpAddressOrderByResponseLocalTimeDesc(String ip);
}
