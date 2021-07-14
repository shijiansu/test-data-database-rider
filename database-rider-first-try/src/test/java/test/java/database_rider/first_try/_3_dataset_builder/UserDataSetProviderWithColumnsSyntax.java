package test.java.database_rider.first_try._3_dataset_builder;

import com.github.database.rider.core.api.dataset.DataSetProvider;
import com.github.database.rider.core.dataset.builder.DataSetBuilder;
import org.dbunit.dataset.IDataSet;

public class UserDataSetProviderWithColumnsSyntax implements DataSetProvider {

  @Override
  public IDataSet provide() {
    DataSetBuilder builder = new DataSetBuilder();
    IDataSet iDataSet =
        builder
            .table("user")
            .columns("id", "name")
            .values(1, "@dbunit")
            .values(2, "@dbrider")
            .build();
    return iDataSet;
  }
}
