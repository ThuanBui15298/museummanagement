package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Instruction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InstructionRepository  extends JpaRepository<Instruction, Long> {

    Optional<Instruction> findByName(String name);

    List<Instruction> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from synthetic s where s.status = 1 and s.type = 2 and lower(concat(coalesce(s.name,''), coalesce(s.title ,''))) like lower(:search)", nativeQuery = true)
    Page<Instruction> findAllBySearch(Pageable pageable, @Param("search") String search);
}
