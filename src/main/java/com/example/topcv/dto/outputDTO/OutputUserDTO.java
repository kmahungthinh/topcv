package com.example.topcv.dto.outputDTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class OutputUserDTO {
  private Integer id;
  private String avatar;
  private String name;
  private String sex;
  private LocalDate dob;
  private String email;
  private String password;
  private String phone;
  private String address;
  private String verifyCode;
  private String state;
  private LocalDate createDate;
}
