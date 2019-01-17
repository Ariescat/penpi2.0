package org.penpi.core.commons.utils;

import java.io.InputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DbUnitUtils {

	public static void appendDbUnitData(DataSource dataSource, String... xmlPaths) throws DatabaseUnitException, SQLException {
		IDatabaseConnection conn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
		try {
			for (String xmlPath : xmlPaths) {
				DatabaseConfig dbConfig = conn.getConfig();
				dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
				IDataSet ds = getXmlDataSet(xmlPath);
				ReplacementDataSet replacementDataSet = createReplacementDataSet(ds);
				DatabaseOperation.INSERT.execute(conn, replacementDataSet);
			}
		} finally {
			conn.close();
		}
	}
	
	private static IDataSet getXmlDataSet(String xmlPath) throws DataSetException  {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		InputStream input = DbUnitUtils.class.getResourceAsStream(xmlPath);
		return builder.build(input);
	}
	
	private static ReplacementDataSet createReplacementDataSet(IDataSet dataSet) {
		ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
		replacementDataSet.addReplacementObject("[null]", null);
		return replacementDataSet;
	}
}
