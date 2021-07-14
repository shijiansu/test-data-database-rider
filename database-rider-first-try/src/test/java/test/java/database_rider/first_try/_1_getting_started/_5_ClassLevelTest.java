package test.java.database_rider.first_try._1_bdd;

import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static org.assertj.core.api.Assertions.assertThat;
import static test.java.database_rider.first_try.Constants.PERSISTENCE_UNIT_1;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.util.EntityManagerProvider;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@MetaDataSet
public class _5_ClassLevelTest {

  // the instance("rider") is as in "persistence.xml" (JPA)
  @Rule
  public EntityManagerProvider emProvider = EntityManagerProvider.instance(PERSISTENCE_UNIT_1);

  @Rule public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());

  @Test
  public void testMetaAnnotationOnClass() {
    List<User> users = em().createQuery("select u from User u").getResultList();
    assertThat(users).isNotNull().isNotEmpty().hasSize(2);
  }

  @Test
  @AnotherMetaDataSet
  public void testMetaAnnotationOnMethod() {
    List<User> users = em().createQuery("select u from User u").getResultList();
    assertThat(users).isNotNull().isNotEmpty().hasSize(1);
  }
}
