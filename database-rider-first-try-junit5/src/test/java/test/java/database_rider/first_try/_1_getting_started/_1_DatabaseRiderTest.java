package test.java.database_rider.first_try._1_bdd;

import static com.github.database.rider.junit5.util.EntityManagerProvider.em;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.github.database.rider.junit5.util.EntityManagerProvider;
import java.util.List;
import org.junit.jupiter.api.Test;

// @ExtendWith(DBUnitExtension.class)
@DBRider // 包装类, 包括@ExtendWith(DBUnitExtension.class), 并可和Spring Context里面的DataSource bean整合
public class _1_DatabaseRiderTest {

  // by reflection so just declare a field or a method with ConnectionHolder as return type
  private ConnectionHolder connectionHolder =
      () -> EntityManagerProvider.instance("rider-pu1").connection();

  @Test
  @DataSet("_1_getting_started/users.yml")
  public void shouldListUsers() {
    // 小心, 这个EntityManagerProvider需要导入的是junit5对应的, 不是rider-core里面的
    List<User> users = em().createQuery("select u from User u").getResultList();
    assertThat(users).isNotNull().isNotEmpty().hasSize(2);
  }
}
