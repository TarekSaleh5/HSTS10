package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.persistence.criteria.CriteriaBuilder.Case;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class loginAPI { ///// remember to check if connected

	public static boolean IsConnected = false;

	public static void checkSpecificUser(Command command, ConnectionToClient client) {

		String messageArrayList[] = new String[3];
		messageArrayList = (String[]) command.getCommand();
		String userName = messageArrayList[0];
		String password = messageArrayList[1];
		String type = messageArrayList[2];
		System.out.print("type = " + type + "\n");
		String temp[] = new String[2];
		String temp2[] = new String[3];

		try {
			String name, pass, url;
			url = "jdbc:mysql://127.0.0.1/hstsdatabase";
			name = "root";
			pass = "t12345";
			String sql;
			Connection myConnection = DriverManager.getConnection(url, name, pass);
			Statement stmt = (Statement) myConnection.createStatement();

			switch (type) {

			case ("Student"):
				System.out.println("student");
				sql = "SELECT * FROM student WHERE userName = '" + userName + "' AND password = '" + password
						+ "'";
				ResultSet rs1 = stmt.executeQuery(sql);

				if (rs1.next()) {
					if (rs1.getBoolean("isConnected")) {
						temp2[0] = "isconnected";
					} else {
						temp2[0] = "true";
						String sql5 = "UPDATE student SET isConnected = 1 WHERE userName = " + "'" + userName + "'";
						stmt.executeUpdate(sql5);
					}
					sql = "SELECT * FROM student WHERE userName = '" + userName + "' AND password = '" + password + "'";
					rs1 = stmt.executeQuery(sql);
					if (rs1.next()) {
						temp2[2] = Integer.toString(rs1.getInt("id"));
					}
				} else {
					temp2[0] = "false";
				}
				temp2[1] = "student";
				command.setCommand(temp2);
				break;

			case ("Teacher"):
				System.out.println("teacher");
			sql = "SELECT * FROM teacher WHERE userName = '" + userName + "' AND password = '" + password
					+ "'";
			ResultSet rs2 = stmt.executeQuery(sql);

			if (rs2.next()) {
				if (rs2.getBoolean("isConnected")) {
					temp2[0] = "isconnected";
				} else {
					temp2[0] = "true";
					String sql5 = "UPDATE teacher SET isConnected = 1 WHERE userName = " + "'" + userName + "'";
					stmt.executeUpdate(sql5);
				}
				sql = "SELECT * FROM teacher WHERE userName = '" + userName + "' AND password = '" + password + "'";
				rs2 = stmt.executeQuery(sql);
				if (rs2.next()) {
					temp2[2] = Integer.toString(rs2.getInt("id"));
				}
			} else {
				temp2[0] = "false";
			}
			temp2[1] = "teacher";
			command.setCommand(temp2);
			break;

			case ("Manager"):
				System.out.println("manager");
			sql = "SELECT * FROM manager WHERE userName = '" + userName + "' AND password = '" + password
					+ "'";
			ResultSet rs3 = stmt.executeQuery(sql);

			if (rs3.next()) {
				if (rs3.getBoolean("isConnected")) {
					temp2[0] = "isconnected";
				} else {
					temp2[0] = "true";
					String sql5 = "UPDATE manager SET isConnected = 1 WHERE userName = " + "'" + userName + "'";
					stmt.executeUpdate(sql5);
				}
				sql = "SELECT * FROM manager WHERE userName = '" + userName + "' AND password = '" + password + "'";
				rs3 = stmt.executeQuery(sql);
				if (rs3.next()) {
					temp2[2] = Integer.toString(rs3.getInt("id"));
				}
			} else {
				temp2[0] = "false";
			}
			temp2[1] = "manager";
			command.setCommand(temp2);
			break;
			
			case ("nointerfacechoosen"):
				temp2[0] = "";
				temp2[2] = "";
				temp2[1] = "nointerfacechoosen";
				command.setCommand(temp2);
				break;
				
			}
			
			

			try {
				client.sendToClient(command);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static String toString(int int1) {
		// TODO Auto-generated method stub
		return null;
	}

}