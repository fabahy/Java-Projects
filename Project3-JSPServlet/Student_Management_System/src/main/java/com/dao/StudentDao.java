package com.dao;

import com.entity.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
	public Connection conn;

	public static String ORDER = "descending";
	public static List<Student> listStd;

	public StudentDao(Connection conn) {
		this.conn = conn;
	}

	public boolean addStudent(Student s) {
		boolean rs = false;
		try {
			String sql = "insert into student(name,grade,birthday,address,note) value (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setString(2, String.valueOf(s.getGrade()));
			ps.setString(3, s.getBirthday());
			ps.setString(4, s.getAddress());
			ps.setString(5, s.getNote());

			int i = ps.executeUpdate();

			if (i == 1) {
				rs = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean updateStudent(Student s) {
		boolean rs = false;
		try {
			String sql = "update student set name=?,grade=?,birthday=?,address=?,note=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setFloat(2, s.getGrade());
			ps.setString(3, s.getBirthday());
			ps.setString(4, s.getAddress());
			ps.setString(5, s.getNote());
			ps.setInt(6, Integer.parseInt(s.getID()));
			int i = ps.executeUpdate();
			if (i == 1) {
				rs = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean deleteStudent(int id) {
		boolean rs = false;
		try {
			String sql = "delete from student where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i == 1) {
				rs = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public List<Student> sortStudents(String field, String order) {
		List<Student> list = new ArrayList<Student>();
		Student s = null;
		if (order.equals("ascending")) {
			if (field.equals("name")) {
				try {
					String sql = "select * from student order by name asc";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						s = new Student();
						s.setID(rs.getString(1));
						s.setName(rs.getString(2));
						s.setGrade(rs.getFloat(3));
						Date getDateDB = rs.getDate(4);
						SimpleDateFormat sFormat = new SimpleDateFormat("dd/MM/yyyy");
						String birthDay = sFormat.format(getDateDB);
						s.setBirthday(birthDay);
						s.setAddress(rs.getString(5));
						s.setNote(rs.getString(6));
						list.add(s);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (field.equals("grade")) {
				try {
					String sql = "select * from student order by grade asc";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						s = new Student();
						s.setID(rs.getString(1));
						s.setName(rs.getString(2));
						s.setGrade(rs.getFloat(3));
						Date getDateDB = rs.getDate(4);
						SimpleDateFormat sFormat = new SimpleDateFormat("dd/MM/yyyy");
						String birthDay = sFormat.format(getDateDB);
						s.setBirthday(birthDay);
						s.setAddress(rs.getString(5));
						s.setNote(rs.getString(6));
						list.add(s);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (order.equals("descending")) {
			if (field.equals("name")) {
				try {
					String sql = "select * from student order by name desc";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						s = new Student();
						s.setID(rs.getString(1));
						s.setName(rs.getString(2));
						s.setGrade(rs.getFloat(3));
						Date getDateDB = rs.getDate(4);
						SimpleDateFormat sFormat = new SimpleDateFormat("dd/MM/yyyy");
						String birthDay = sFormat.format(getDateDB);
						s.setBirthday(birthDay);
						s.setAddress(rs.getString(5));
						s.setNote(rs.getString(6));
						list.add(s);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (field.equals("grade")) {
				try {
					String sql = "select * from student order by grade desc";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						s = new Student();
						s.setID(rs.getString(1));
						s.setName(rs.getString(2));
						s.setGrade(rs.getFloat(3));
						Date getDateDB = rs.getDate(4);
						SimpleDateFormat sFormat = new SimpleDateFormat("dd/MM/yyyy");
						String birthDay = sFormat.format(getDateDB);
						s.setBirthday(birthDay);
						s.setAddress(rs.getString(5));
						s.setNote(rs.getString(6));
						list.add(s);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public List<Student> getAllStudents() {
		List<Student> list = new ArrayList<Student>();
		Student s = null;
		try {
			String sql = "select * from student order by id asc";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Student();
				s.setID(rs.getString(1));
				s.setName(rs.getString(2));
				s.setGrade(rs.getFloat(3));
				Date getDateDB = rs.getDate(4);
				SimpleDateFormat sFormat = new SimpleDateFormat("dd/MM/yyyy");
				String birthDay = sFormat.format(getDateDB);
				s.setBirthday(birthDay);
				s.setAddress(rs.getString(5));
				s.setNote(rs.getString(6));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Student getStudentByID(int ID) {
		Student s = null;
		try {
			String sql = "select * from student where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Student();
				s.setID(rs.getString(1));
				s.setName(rs.getString(2));
				s.setGrade(rs.getFloat(3));
				s.setBirthday(rs.getString(4));
				s.setAddress(rs.getString(5));
				s.setNote(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public List<Student> getStudentsByName(String InputName) {
		List<Student> list = new ArrayList<Student>();
		Student s = null;
		try {
			Statement st = conn.createStatement();
			String sql = "select * from student where name like '%" + InputName + "%'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				s = new Student();
				s.setID(rs.getString(1));
				s.setName(rs.getString(2));
				s.setGrade(rs.getFloat(3));
				s.setBirthday(rs.getString(4));
				s.setAddress(rs.getString(5));
				s.setNote(rs.getString(6));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
