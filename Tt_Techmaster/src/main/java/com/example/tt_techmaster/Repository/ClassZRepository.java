package com.example.tt_techmaster.Repository;

import com.example.tt_techmaster.model.ClassZ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassZRepository extends JpaRepository<ClassZ, Integer> {
}
