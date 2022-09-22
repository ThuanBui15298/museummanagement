package com.example.museummanagement.repository;

import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.SightSeeingRule;
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
public interface SightSeeingRuleRepository extends JpaRepository<SightSeeingRule, Long> {
    Optional<SightSeeingRule> findByName(String name);

    List<SightSeeingRule> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from synthetic s where s.status = 1 and s.type = 1 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<SightSeeingRule> findAllBySearch(Pageable pageable, @Param("search") String search);

}
