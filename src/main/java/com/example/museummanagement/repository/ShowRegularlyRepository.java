package com.example.museummanagement.repository;

import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.ShowRegularly;
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
public interface ShowRegularlyRepository extends JpaRepository<ShowRegularly, Long> {
    Optional<ShowRegularly> findByName(String name);

    List<ShowRegularly> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.type = 2 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<ShowRegularly> findAllBySearch(Pageable pageable, @Param("search") String search);

}
