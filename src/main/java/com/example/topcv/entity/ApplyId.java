package com.example.topcv.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;
import org.hibernate.Hibernate;

@Data
@Embeddable
public class ApplyId implements Serializable {

  private static final long serialVersionUID = -7088913057627886307L;

  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @Column(name = "job_id", nullable = false)
  private Integer jobId;

  @Override
  public int hashCode() {
    return Objects.hash(jobId, userId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    ApplyId entity = (ApplyId) o;
    return Objects.equals(this.jobId, entity.jobId) && Objects.equals(this.userId, entity.userId);
  }
}
