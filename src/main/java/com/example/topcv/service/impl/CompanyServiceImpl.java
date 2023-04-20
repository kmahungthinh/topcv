package com.example.topcv.service.impl;

import com.example.topcv.common.encryptor.AttributeEncryptor;
import com.example.topcv.common.enums.StateConstant;
import com.example.topcv.common.mapper.GenericMapper;
import com.example.topcv.common.util.Utils;
import com.example.topcv.dto.inputDTO.InputCompanyDTO;
import com.example.topcv.dto.outputDTO.OutputCompanyDTO;
import com.example.topcv.entity.Company;
import com.example.topcv.repository.CompanyRepository;
import com.example.topcv.service.CompanyService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {

  private final GenericMapper genericMapper;
  private final EmailServiceImpl emailService;
  private final CompanyRepository companyRepository;
  private final AttributeEncryptor attributeEncryptor;

  @Autowired
  public CompanyServiceImpl(
      GenericMapper genericMapper,
      EmailServiceImpl emailService,
      CompanyRepository companyRepository,
      AttributeEncryptor attributeEncryptor) {
    this.genericMapper = genericMapper;
    this.emailService = emailService;
    this.companyRepository = companyRepository;
    this.attributeEncryptor = attributeEncryptor;
  }

  @Override
  public OutputCompanyDTO findById(Integer userId) {
    Company company =
        companyRepository.findByIdAndStateNot(userId, StateConstant.DELETED.name()).orElse(null);
    if (company != null) {
      company.setPassword(attributeEncryptor.convertToEntityAttribute(company.getPassword()));
    }
    return genericMapper.mapToType(company, OutputCompanyDTO.class);
  }

  @Override
  @Transactional
  public boolean createCompany(InputCompanyDTO data) {
    Company company =
        companyRepository
            .findByEmailAndStateNot(data.getEmail(), StateConstant.DELETED.name())
            .orElse(null);
    if (company == null) {
      String verifyCode = Utils.createVerifyCode();
      company = genericMapper.mapToType(data, Company.class);
      company.setPassword(attributeEncryptor.convertToDatabaseColumn(data.getPassword()));
      company.setState(StateConstant.WAIT.name());
      company.setCreateDate(LocalDate.now());
      company.setVerifyCode(verifyCode);
      emailService.sendEmailVerifyCode(data.getEmail(), verifyCode);
      companyRepository.save(company);
      return true;
    }
    return false;
  }

  @Override
  @Transactional
  public boolean updateCompany(int companyId, InputCompanyDTO data) {
    Company company =
        companyRepository
            .findByEmailAndStateNot(data.getEmail(), StateConstant.DELETED.name())
            .orElse(null);
    if (company == null) {
      company =
          companyRepository
              .findByIdAndStateNot(companyId, StateConstant.DELETED.name())
              .orElse(null);
      if (company != null) {
        genericMapper.copyNonNullProperties(data, company);
        companyRepository.save(company);
        return true;
      }
      return false;
    }
    return false;
  }
}
