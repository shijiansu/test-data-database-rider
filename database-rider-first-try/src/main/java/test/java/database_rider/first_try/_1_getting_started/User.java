package test.java.database_rider.first_try._1_bdd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

  // update full class signature at "persistence.xml"

  @Id @GeneratedValue private long id;

  private String name;
}
