package com.studytrails.tutorials.springhibernatejpa;

import javax.sql.DataSource;

public class DbUtil {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void initialize(){
	/*	DataSource dataSource = getDataSource();
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE PERSON (ID INT, NAME VARCHAR(45), EMAIL VARCHAR(45))");
statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}
}