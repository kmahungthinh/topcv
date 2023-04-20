package com.example.topcv.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "followings")
public class Following {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @Exclude
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  @Exclude
  private Company company;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "job_id")
  @Exclude
  private Job job;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Following following = (Following) o;
    return id != null && Objects.equals(id, following.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
