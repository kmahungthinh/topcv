package com.example.topcv.entity;

import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(name = "applies")
public class Apply {

  @EmbeddedId private ApplyId id;

  @MapsId("userId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @Exclude
  private User user;

  @MapsId("jobId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "job_id", nullable = false)
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
    Apply apply = (Apply) o;
    return id != null && Objects.equals(id, apply.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
