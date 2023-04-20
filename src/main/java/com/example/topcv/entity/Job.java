package com.example.topcv.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "jobs")
public class Job {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "salary", nullable = false, length = 100)
  private String salary;

  @Column(name = "number_recruit", nullable = false)
  private Integer numberRecruit;

  @Column(name = "working_form", nullable = false, length = 100)
  private String workingForm;

  @Column(name = "level", nullable = false, length = 100)
  private String level;

  @Column(name = "gender", length = 10)
  private String gender;

  @Column(name = "experience", nullable = false)
  private Integer experience;

  @Lob
  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "state", nullable = false, length = 20)
  private String state;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  @Exclude
  private Company company;

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
    Job job = (Job) o;
    return id != null && Objects.equals(id, job.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
