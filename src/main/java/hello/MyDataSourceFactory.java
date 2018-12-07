package hello;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

//import oracle.jdbc.pool.OracleDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class MyDataSourceFactory {

	public static DataSource getMySQLDataSource() {
		Properties prop = new Properties();
		FileInputStream fis = null;
		MysqlDataSource msds = new MysqlDataSource();
		try {
			fis = new FileInputStream("src/main/resources/db.properties");
			prop.load(fis);
			msds.setURL(prop.getProperty("MYSQL_DB_URL"));
			msds.setUser(prop.getProperty("MYSQL_DB_USERNAME"));
			msds.setPassword(prop.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msds;
	}
	
	/*
	public static EntityManagerFactory emf() {
		
		EntityManagerFactory emf = new EntityManagerFactory() ;
		return null;
		
	}
	*/
	
	public static DataSource getJDBCDataSource() throws SQLException {
		Properties prop = new Properties();
		FileInputStream fis = null;
		OracleDataSource msds = new OracleDataSource();
		try {
			fis = new FileInputStream("src/main/resources/db.properties");
			prop.load(fis);
			msds.setURL(prop.getProperty("ORACLE_DB_URL"));
			msds.setUser(prop.getProperty("ORACLE_DB_USERNAME"));
			msds.setPassword(prop.getProperty("ORACLE_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msds;
	}
	
}
