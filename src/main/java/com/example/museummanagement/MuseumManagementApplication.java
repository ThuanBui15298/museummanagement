package com.example.museummanagement;

import com.example.museummanagement.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MuseumManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(MuseumManagementApplication.class, args);
    }


}
