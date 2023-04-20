package com.example.topcv.dto.outputDTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class OutputCompanyDTO {
  private Integer id;
  private String avatar;
  private String name;
  private String email;
  private String password;
  private String phone;
  private String address;
  private String introduce;
  private String verifyCode;
  private String state;
  private LocalDate createDate;
}
