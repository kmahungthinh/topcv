package com.example.topcv.controller;

import com.example.topcv.dto.inputDTO.InputUserDTO;
import com.example.topcv.dto.outputDTO.OutputUserDTO;
import com.example.topcv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public String userFindById(@PathVariable(name = "id") Integer userId) {
    OutputUserDTO user = userService.findById(userId);
    return "";
  }

  @PostMapping("/create")
  public String userCreate(@ModelAttribute InputUserDTO data) {
    userService.createUser(data);
    return "";
  }

  @PostMapping("/{id}/update")
  public String userUpdate(
      @PathVariable(name = "id") Integer userId, @ModelAttribute InputUserDTO data) {
    userService.updateUser(userId, data);
    return "";
  }
}
