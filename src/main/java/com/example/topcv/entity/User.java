package com.example.topcv.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Lob
  @Column(name = "avatar", nullable = false)
  private String avatar;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "sex", nullable = false, length = 10)
  private String sex;

  @Column(name = "dob", nullable = false)
  private LocalDate dob;

  @Column(name = "email", nullable = false, length = 320)
  private String email;

  @Column(name = "password", nullable = false, length = 500)
  private String password;

  @Column(name = "phone", nullable = false, length = 20)
  private String phone;

  @Lob
  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "verify_code", nullable = false, length = 100)
  private String verifyCode;

  @Column(name = "state", nullable = false, length = 20)
  private String state;

  @Column(name = "create_date", nullable = false)
  private LocalDate createDate;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    User user = (User) o;
    return id != null && Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
