package com.example.topcv.dto.outputDTO;

import com.example.topcv.entity.Company;
import java.time.LocalDate;
import lombok.Data;

@Data
public class OutputJobDTO {
  private Integer id;
  private String name;
  private String salary;
  private Integer numberRecruit;
  private String workingForm;
  private String level;
  private String gender;
  private Integer experience;
  private String content;
  private String state;
  private Company company;
  private LocalDate createDate;
}
