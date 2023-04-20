package com.example.topcv.service;

public interface EmailService {

  /** Gửi email chứa mã xác thực về tài khoản người dùng */
  void sendEmailVerifyCode(String email, String verifyCode);
}
