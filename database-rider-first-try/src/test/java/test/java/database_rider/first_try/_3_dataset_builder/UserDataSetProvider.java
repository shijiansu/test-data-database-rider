package test.java.database_rider.first_try._3_dataset_builder;

import com.github.database.rider.core.api.dataset.DataSetProvider;
import com.github.database.rider.core.dataset.builder.DataSetBuilder;
import org.dbunit.dataset.IDataSet;

public class UserDataSetProvider implements DataSetProvider {

  @Override
  public IDataSet provide() {
    DataSetBuilder builder = new DataSetBuilder();
    builder
        .table("user")
        .row()
        .column("id", 1)
        .column("name", "@dbunit")
        .row()
        .column("id", 2)
        .column("name", "@dbrider");
    return builder.build();
  }
}
