package com.example.museummanagement.repository;

import com.example.museummanagement.entity.ProfessionalFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ProfessionalFunctionRepository extends JpaRepository<ProfessionalFunction, Long> {

    Optional<ProfessionalFunction> findByName(String name);

    List<ProfessionalFunction> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from synthetic s where s.status = 1 and s.type = 5 and lower(concat(coalesce(s.name,''), coalesce(s.title ,''))) like lower(:search)", nativeQuery = true)
    Page<ProfessionalFunction> findAllBySearch(Pageable pageable, @Param("search") String search);
}
