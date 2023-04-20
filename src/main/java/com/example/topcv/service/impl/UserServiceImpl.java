package com.example.topcv.service.impl;

import com.example.topcv.common.encryptor.AttributeEncryptor;
import com.example.topcv.common.enums.StateConstant;
import com.example.topcv.common.mapper.GenericMapper;
import com.example.topcv.common.util.Utils;
import com.example.topcv.dto.inputDTO.InputUserDTO;
import com.example.topcv.dto.outputDTO.OutputUserDTO;
import com.example.topcv.entity.User;
import com.example.topcv.repository.UserRepository;
import com.example.topcv.service.UserService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final GenericMapper genericMapper;
  private final UserRepository userRepository;
  private final EmailServiceImpl emailService;
  private final AttributeEncryptor attributeEncryptor;

  @Autowired
  public UserServiceImpl(
      GenericMapper genericMapper,
      UserRepository userRepository,
      EmailServiceImpl emailService,
      AttributeEncryptor attributeEncryptor) {
    this.genericMapper = genericMapper;
    this.userRepository = userRepository;
    this.emailService = emailService;
    this.attributeEncryptor = attributeEncryptor;
  }

  @Override
  public OutputUserDTO findById(Integer userId) {
    User user =
        userRepository.findByIdAndStateNot(userId, StateConstant.DELETED.name()).orElse(null);
    if (user != null) {
      user.setPassword(attributeEncryptor.convertToEntityAttribute(user.getPassword()));
    }
    return genericMapper.mapToType(user, OutputUserDTO.class);
  }

  @Override
  @Transactional
  public boolean createUser(InputUserDTO data) {
    User user =
        userRepository
            .findByEmailAndStateNot(data.getEmail(), StateConstant.DELETED.name())
            .orElse(null);
    if (user == null) {
      String verifyCode = Utils.createVerifyCode();
      user = genericMapper.mapToType(data, User.class);
      user.setDob(LocalDate.now());
      user.setPassword(attributeEncryptor.convertToDatabaseColumn(data.getPassword()));
      user.setState(StateConstant.WAIT.name());
      user.setCreateDate(LocalDate.now());
      user.setVerifyCode(verifyCode);
      emailService.sendEmailVerifyCode(data.getEmail(), verifyCode);
      userRepository.save(user);
      return true;
    }
    return false;
  }

  @Override
  @Transactional
  public boolean updateUser(int userId, InputUserDTO data) {
    User user =
        userRepository
            .findByEmailAndStateNot(data.getEmail(), StateConstant.DELETED.name())
            .orElse(null);
    if (user == null) {
      user = userRepository.findByIdAndStateNot(userId, StateConstant.DELETED.name()).orElse(null);
      if (user != null) {
        genericMapper.copyNonNullProperties(data, user);
        userRepository.save(user);
        return true;
      }
      return false;
    }
    return false;
  }
}
