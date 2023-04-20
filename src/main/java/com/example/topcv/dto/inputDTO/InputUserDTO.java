package com.example.topcv.dto.inputDTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class InputUserDTO {
  private String avatar;
  private String name;
  private String sex;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String dob;

  private String email;
  private String password;
  private String phone;
  private String address;
}
