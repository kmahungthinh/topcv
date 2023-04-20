package com.example.topcv.service;

import com.example.topcv.dto.inputDTO.InputUserDTO;
import com.example.topcv.dto.outputDTO.OutputUserDTO;

public interface UserService {

  /** Tìm tài khoản người tìm việc theo ID */
  OutputUserDTO findById(Integer userId);

  /** Tạo mới tài khoản người tìm việc */
  boolean createUser(InputUserDTO data);

  /** Cập nhật tài khoản người tìm việc theo ID */
  boolean updateUser(int userId, InputUserDTO data);
}
