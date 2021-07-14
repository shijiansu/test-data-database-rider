package test.java.database_rider.first_try._1_bdd;

import static com.github.database.rider.core.util.EntityManagerProvider.clear;
import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static com.github.database.rider.core.util.EntityManagerProvider.tx;
import static org.assertj.core.api.Assertions.assertThat;
import static test.java.database_rider.first_try.Constants.PERSISTENCE_UNIT_1;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.util.EntityManagerProvider;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class) // can remove
public class _1_DatabaseRiderTest {

  // the instance("rider") is as in "persistence.xml" (JPA)
  @Rule
  public EntityManagerProvider emProvider = EntityManagerProvider.instance(PERSISTENCE_UNIT_1);

  // @Rule public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());

  // use Rule Chain - [EntityManagerProvider rule] executes before DBUnit rule
  @Rule
  public TestRule rules =
      RuleChain.outerRule(emProvider).around(DBUnitRule.instance(emProvider.connection()));

  @Test
  @DataSet({
    "_1_getting_started/users.yml",
    "_1_getting_started/tweets-empty.yml"
  }) // from database-rider
  public void shouldListUsers() {
    // em() initialized by EntityManagerProvider rule
    List<User> users = em().createQuery("select u from User u").getResultList();
    assertThat(users).isNotNull().isNotEmpty().hasSize(2);
  }

  @Test
  @DataSet(value = "_1_getting_started/users.yml", disableConstraints = true)
  public void shouldUpdateUserWithTransaction() {
    clear(PERSISTENCE_UNIT_1); // clears JPA first level cache
    User user = (User) em().createQuery("select u from User u where u.id = 1").getSingleResult();
    assertThat(user).isNotNull();
    assertThat(user.getName()).isEqualTo("@realpestano");
    tx().begin();
    user.setName("@rmpestano");
    em().merge(user);
    tx().commit();
    assertThat(user.getName()).isEqualTo("@rmpestano");
  }

  @Test
  @DataSet("_1_getting_started/users.yml")
  public void shouldDeleteUserWithTransaction() {
    clear(PERSISTENCE_UNIT_1); // clears JPA first level cache
    User user = (User) em().createQuery("select u from User u where u.id = 1").getSingleResult();
    assertThat(user).isNotNull();
    assertThat(user.getName()).isEqualTo("@realpestano");
    tx().begin();
    em().remove(user);
    tx().commit();
    List<User> users = em().createQuery("select u from User u").getResultList();
    assertThat(users).hasSize(1);
  }

  @Test
  @DataSet("_1_getting_started/users.yml")
  @ExpectedDataSet(value = "_1_getting_started/user-expected.yml", ignoreCols = "id")
  public void shouldAssertDatabaseUsingExpectedDataSet() {
    User user = (User) em().createQuery("select u from User u where u.id = 1").getSingleResult();
    assertThat(user).isNotNull();
    tx().begin();
    em().remove(user);
    tx().commit();
  }

  @Test
  // You don’t need to initialize a dataset but can use cleanBefore to clear database
  // before testing.
  // When you use a dataset like users.yml in @DataSet dbunit will use CLEAN_INSERT
  // seeding strategy
  // (by default) for all declared tables in dataset. This is why we didn’t needed
  // cleanBefore in
  // any other example tests.
  @DataSet(cleanBefore = true) // 这里没有传入dataset, 所以配置手动clean
  @ExpectedDataSet("_1_getting_started/users-expected-regex.yml")
  public void shouldAssertDatabaseUsingRegex() {
    User u = new User();
    u.setName("expected user1");
    User u2 = new User();
    u2.setName("expected user2");
    tx().begin();
    em().persist(u);
    em().persist(u2);
    // commit will push to database also commit transaction
    tx().commit();
  }

  @Test
  // Transactional attribute will make Database Rider start a transaction before test and commit the
  // transaction after test execution but before expected dataset comparison.
  @DataSet(cleanBefore = true, transactional = true)
  @ExpectedDataSet("_1_getting_started/users-expected-regex.yml")
  public void shouldManageTransactionAutomatically() {
    Assertions.assertThat(EntityManagerProvider.isEntityManagerActive());
    User u = new User();
    u.setName("expected user1");
    User u2 = new User();
    u2.setName("expected user2");
    em().persist(u);
    em().persist(u2);
    // FIXED for
    // https://database-rider.github.io/database-rider/1.2.8/documentation.html#_assertion_using_automatic_transaction
    // apply to database, but no commit transaction, commit handles by "transactional = true"
    em().flush();
  }

  // "Warning: Nashorn engine is planned to be removed from a future JDK release"
  @Test
  @DataSet(
      value = "_1_getting_started/tweets-with-javascript.yml",
      cleanBefore = true,
      disableConstraints = true)
  public void shouldSeedDatabaseUsingJavaScriptInDataset() {
    Tweet tweet =
        (Tweet) em().createQuery("select t from Tweet t where t.id = 1").getSingleResult();
    assertThat(tweet).isNotNull();
    assertThat(tweet.getLikes()).isEqualTo(50);
  }
}
