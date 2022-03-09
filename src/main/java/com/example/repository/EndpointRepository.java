package com.example.repository;

import com.example.model.EndPointData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EndpointRepository extends JpaRepository<EndPointData, Long> {
    List<EndPointData> findByPublished(boolean published);
    List<EndPointData> findByTitleContaining(String title);
}
