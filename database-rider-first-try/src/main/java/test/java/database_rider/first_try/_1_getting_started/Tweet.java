package test.java.database_rider.first_try._1_bdd;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Tweet {

  // update full class signature at "persistence.xml"

  @Id @GeneratedValue private String id;

  @Size(min = 1, max = 140)
  private String content;

  private Integer likes;

  @Temporal(TemporalType.TIMESTAMP)
  private Calendar date;

  private Long timestamp;

  @ManyToOne(fetch = FetchType.LAZY)
  User user;
}
