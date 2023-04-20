package com.example.topcv.repository;

import com.example.topcv.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {}
