package com.example.ql.repository;

import com.example.ql.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Integer> {

    Optional<AccountModel>findAllById(int id);
}
