package test.java.database_rider.first_try._1_bdd;

import static test.java.database_rider.first_try.Constants.PERSISTENCE_UNIT_1;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.core.util.EntityManagerProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

// There are two types of configuration in Database Rider: DataSet and DBUnit.
@RunWith(JUnit4.class)
// https://database-rider.github.io/database-rider/latest/documentation.html?theme=foundation#Configuration
public class _2_DatabaseRiderConfigurationTest {

  // the instance("rider") is as in "persistence.xml" (JPA)
  @Rule
  public EntityManagerProvider emProvider = EntityManagerProvider.instance(PERSISTENCE_UNIT_1);

  @Rule public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());

  @DataSet(
      value = "_1_getting_started/users.yml",
      strategy = SeedStrategy.CLEAN_INSERT,
      disableConstraints = true,
      cleanAfter = true,
      transactional = true)
  @Test
  public void shouldLoadDataSetConfigFromAnnotation() {}

  @Test
  @DBUnit(cacheConnection = true, cacheTableNames = false, allowEmptyFields = true, batchSize = 50)
  public void shouldLoadDBUnitConfigViaAnnotation() {
    // or configure at "src/test/resources/dbunit.yml".
    // @DBUnit annotation takes precedence over dbunit.yml global configuration
    // which will be used only if the annotation is not present.
  }
}
