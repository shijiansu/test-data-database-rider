package test.java.database_rider.first_try._3_dataset_builder;

import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static org.assertj.core.api.Assertions.assertThat;
import static test.java.database_rider.first_try.Constants.PERSISTENCE_UNIT_1;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.util.EntityManagerProvider;
import java.sql.SQLException;
import java.util.List;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Rule;
import org.junit.Test;
import test.java.database_rider.first_try._1_bdd.User;

public class _1_DatasetBuilderTest {

  @Rule
  public EntityManagerProvider emProvider = EntityManagerProvider.instance(PERSISTENCE_UNIT_1);

  @Rule public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());

  @Test
  @DataSet(provider = UserDataSetProvider.class, cleanBefore = true)
  public void shouldSeeDatabaseProgrammatically() {
    // verify
    List<User> users = em().createQuery("select u from User u").getResultList();
    assertThat(users)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2)
        .extracting("name")
        .contains("@dbunit", "@dbrider");
  }

  @Test
  public void shouldSeeDatabaseProgrammaticallyWithFullyManulDBUnitAPI()
      throws DatabaseUnitException, SQLException {
    // fully manual DBUnit API
    UserDataSetProvider provider = new UserDataSetProvider();
    IDataSet dataset = provider.provide();
    DatabaseOperation.CLEAN_INSERT.execute(
        new DatabaseConnection(emProvider.connection()), dataset);
    // verify
    List<User> users = em().createQuery("select u from User u").getResultList();
    assertThat(users)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2)
        .extracting("name")
        .contains("@dbunit", "@dbrider");
  }

  @Test
  @DataSet(provider = UserDataSetProviderWithColumnsSyntax.class)
  public void shouldSeedDatabaseUsingDataSetProviderWithColumnsSyntax() {
    List<User> users = em().createQuery("select u from User u").getResultList();
    assertThat(users)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2)
        .extracting("name")
        .contains("@dbunit", "@dbrider");
  }
}
