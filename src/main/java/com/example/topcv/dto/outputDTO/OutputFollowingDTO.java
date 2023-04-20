package com.example.topcv.dto.outputDTO;

import com.example.topcv.entity.Company;
import com.example.topcv.entity.Job;
import com.example.topcv.entity.User;
import lombok.Data;

@Data
public class OutputFollowingDTO {
  private Integer id;
  private User user;
  private Company company;
  private Job job;
}
