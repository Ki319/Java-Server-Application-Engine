package com.polaris.engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL
{

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;

	public MySQL(String databaseURL, String user, String password) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseURL, user, password);
		statement = connection.createStatement();
	}

	public void createTable(String tableName, String arguments)
	{
		try
		{
			statement.executeUpdate("CREATE TABLE " + tableName + " (" + arguments + ")");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void addTableValue(String tableName, String arguments)
	{
		try
		{
			statement.executeUpdate("INSERT INTO " + tableName + " VALUES (" + arguments + ")");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public ResultSet getAllTableValues(String tableName, String arguments)
	{
		try
		{
			return statement.executeQuery("SELECT " + arguments + " FROM " + tableName);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getTableValues(String tableName, String arguments, String ifCondition)
	{
		try
		{
			return statement.executeQuery("SELECT " + arguments + " FROM " + tableName + " WHERE " + ifCondition);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getAllSortedTableValues(String tableName, String arguments, String order)
	{
		try
		{
			return statement.executeQuery("SELECT " + arguments + " FROM " + tableName + " ORDER BY " + order);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getSortedTableValues(String tableName, String arguments, String ifCondition, String order)
	{
		try
		{
			return statement.executeQuery("SELECT " + arguments + " FROM " + tableName + " WHERE " + ifCondition + " ORDER BY " + order);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void updateTableValue(String tableName, String setValues, String ifCondition)
	{
		try 
		{
			statement.executeUpdate("UPDATE " + tableName + "SET " + setValues + " WHERE " + ifCondition);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteTableValue(String tableName, String ifCondition)
	{
		try
		{
			statement.executeUpdate("DELETE FROM " + tableName + " WHERE " + ifCondition);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteTable(String tableName)
	{
		try 
		{
			statement.executeUpdate("DROP TABLE " + tableName);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public PreparedStatement preparedStatement(String stmt) throws SQLException
	{
		return connection.prepareStatement(stmt);
	}

	public void close()
	{	
		try
		{
			if(statement != null)
			{
				statement.close();
			}

			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			
			if(connection != null)
			{
				connection.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
