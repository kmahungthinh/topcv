package com.example.topcv.repository;

import com.example.topcv.entity.Company;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
  Optional<Company> findByIdAndStateNot(Integer integer, String stateDeleted);

  Optional<Company> findByEmailAndStateNot(String email, String stateDeleted);
}
