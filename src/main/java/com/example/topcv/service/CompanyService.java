package com.example.topcv.service;

import com.example.topcv.dto.inputDTO.InputCompanyDTO;
import com.example.topcv.dto.outputDTO.OutputCompanyDTO;

public interface CompanyService {

  /** Tìm tài khoản nhà tuyển dụng theo ID */
  OutputCompanyDTO findById(Integer companyId);

  /** Tạo mới tài khoản nhà tuyển dụng */
  boolean createCompany(InputCompanyDTO data);

  /** Cập nhật tài khoản nhà tuyển dụng theo ID */
  boolean updateCompany(int companyId, InputCompanyDTO data);
}
