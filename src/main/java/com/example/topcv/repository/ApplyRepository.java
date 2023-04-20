package com.example.topcv.repository;

import com.example.topcv.entity.Apply;
import com.example.topcv.entity.ApplyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, ApplyId> {}
