package test.java.database_rider.first_try._2_operation;

import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static com.github.database.rider.core.util.EntityManagerProvider.instance;
import static com.github.database.rider.core.util.EntityManagerProvider.tx;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;
import static test.java.database_rider.first_try.Constants.NEW_LINE;
import static test.java.database_rider.first_try.Constants.PERSISTENCE_UNIT_1;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.DataSetFormat;
import com.github.database.rider.core.api.exporter.DataSetExportConfig;
import com.github.database.rider.core.api.exporter.ExportDataSet;
import com.github.database.rider.core.exporter.DataSetExporter;
import com.github.database.rider.core.util.EntityManagerProvider;
import java.io.File;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.junit.Rule;
import org.junit.Test;
import test.java.database_rider.first_try._1_bdd.User;

public class _1_ExportAndImportTest {

  // the instance("rider") is as in "persistence.xml" (JPA)
  @Rule public EntityManagerProvider emProvider = instance(PERSISTENCE_UNIT_1);

  @Rule public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());

  @Test
  @DataSet(cleanBefore = true)
  public void shouldExportYMLDataSetUsingDataSetExporter()
      throws SQLException, DatabaseUnitException {
    tx().begin();
    User u1 = new User();
    u1.setName("u1");
    // just insert a user and assert it is present in exported dataset
    em().persist(u1);
    tx().commit();

    // export
    DataSetExporter.getInstance()
        .export(
            emProvider.connection(),
            new DataSetExportConfig().outputFileName("data/_2_operation_out/yml/user.yml"));

    // load data file and verify again
    File ymlDataSet = new File("data/_2_operation_out/yml/user.yml");
    assertThat(ymlDataSet).exists();
    assertThat(contentOf(ymlDataSet))
        .contains(
            "USER:"
                + NEW_LINE
                + "  - ID: "
                + u1.getId()
                + NEW_LINE
                + "    NAME: \"u1\""
                + NEW_LINE);
  }

  @Test
  @DataSet("_1_getting_started/users.yml")
  @ExportDataSet(format = DataSetFormat.XML, outputName = "data/_2_operation_out/xml/allTables.xml")
  public void shouldExportAllTablesInXMLFormat() {}

  @Test
  @DataSet(cleanBefore = true)
  public void shouldExportYMLDataSetWithExplicitSchemaProgrammatically()
      throws SQLException, DatabaseUnitException {
    tx().begin();
    User u1 = new User();
    u1.setName("u1");
    em().persist(u1);
    tx().commit();
    // with schema name
    DataSetExporter.getInstance()
        .export(
            emProvider.connection(),
            new DataSetExportConfig().outputFileName("data/_2_operation_out/yml/user2.yml"),
            "public");
    // verify
    File ymlDataSet = new File("data/_2_operation_out/yml/user2.yml");
    assertThat(ymlDataSet).exists();
    assertThat(contentOf(ymlDataSet))
        .contains(
            "USER:"
                + NEW_LINE
                + "  - ID: "
                + u1.getId()
                + NEW_LINE
                + "    NAME: \"u1\""
                + NEW_LINE);
  }
}
