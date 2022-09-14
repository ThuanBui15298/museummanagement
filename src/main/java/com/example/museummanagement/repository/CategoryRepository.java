package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Optional<Category> findByIdAndStatus(Long id, Integer status);

    List<Category> findAllByIdInAndStatus( List<Long> id, Integer status);

    @Query(value = "SELECT * FROM category c WHERE c.name = :name ", nativeQuery = true)
    Page<Category> getAllCategory(@Param("name") String name, Pageable pageable) ;

}
