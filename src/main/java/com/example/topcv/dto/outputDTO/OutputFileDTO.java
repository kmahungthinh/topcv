package com.example.topcv.dto.outputDTO;

import com.example.topcv.entity.User;
import java.time.LocalDate;
import lombok.Data;

@Data
public class OutputFileDTO {
  private Integer id;
  private String name;
  private String url;
  private String state;
  private User user;
  private LocalDate createDate;
}
