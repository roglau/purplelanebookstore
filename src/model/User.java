package model;

import java.sql.*;

import connect.Connect;

public class User {
	
	private String name, email, password, gender, role;
	private int id;
	
	public static User currUser;
	
	public static boolean checkEmail(String email) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from users where email = ?");
		try {
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				return true;
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void insertUser(String name, String email, String password, String role, String gender) {
		// TODO Auto-generated method stub
		PreparedStatement ps=Connect.getConnection().prepareStatement("insert into users (name,email,password,role,gender) values (?,?,?,?,?)");
		try {
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, role);
			ps.setString(5, gender);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static User getUser(String email, String pass){
		User user = null;
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from users where email like ? and password like ?");
		try {
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name = rs.getString("name");
				String email2 =rs.getString("email");
				String password2 =rs.getString("password");
				String gender  = rs.getString("gender");
				String role = rs.getString("role");
				
				user = new User(id, name, email2, password2, gender, role);
				
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User(int id, String name, String email, String password, String gender, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.role = role;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
	
}
