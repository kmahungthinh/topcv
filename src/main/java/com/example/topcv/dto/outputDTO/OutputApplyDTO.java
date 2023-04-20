package com.example.topcv.dto.outputDTO;

import com.example.topcv.entity.Job;
import com.example.topcv.entity.User;
import lombok.Data;

@Data
public class OutputApplyDTO {
  private User user;
  private Job job;
}
