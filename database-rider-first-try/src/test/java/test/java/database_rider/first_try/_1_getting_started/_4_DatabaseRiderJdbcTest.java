package test.java.database_rider.first_try._1_bdd;

import static test.java.database_rider.first_try.Constants.PERSISTENCE_UNIT_1;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.util.EntityManagerProvider;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Rule;
import org.junit.Test;

public class _4_DatabaseRiderJdbcTest {

  // the instance("rider") is as in "persistence.xml" (JPA)
  @Rule
  public EntityManagerProvider emProvider = EntityManagerProvider.instance(PERSISTENCE_UNIT_1);

  @Rule
  public DBUnitRule dbUnitRule =
      DBUnitRule.instance(
          DriverManager.getConnection("jdbc:hsqldb:mem:test;DB_CLOSE_DELAY=-1", "sa", ""));

  public _4_DatabaseRiderJdbcTest() throws SQLException {}

  @Test
  public void test() {}
}
