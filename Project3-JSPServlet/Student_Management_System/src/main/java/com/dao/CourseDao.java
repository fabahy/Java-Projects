package com.dao;

import com.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
	private Connection conn;

	public static String ORDER = "descending";
	public static List<Course> listCrs;

	public CourseDao(Connection conn) {
		this.conn = conn;
	}

	public boolean addCourse(Course c) {
		boolean rs = false;
		try {
			String sql = "insert into course(name,lecture,year,note) value (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getLecture());
			ps.setInt(3, c.getYear());
			ps.setString(4, c.getNote());

			int i = ps.executeUpdate();

			if (i == 1) {
				rs = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean updateCourse(Course c) {
		boolean rs = false;
		try {
			String sql = "update course set name=?,lecture=?,year=?,note=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getLecture());
			ps.setInt(3, c.getYear());
			ps.setString(4, c.getNote());
			ps.setInt(5, Integer.parseInt(c.getID()));
			int i = ps.executeUpdate();
			if (i == 1) {
				rs = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean deleteCourse(int id) {
		boolean rs = false;
		try {
			String sql = "delete from course where id=?";
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

	public List<Course> sortCourses(String field, String order) {
		List<Course> list = new ArrayList<Course>();
		Course c = null;
		if (order.equals("ascending")) {
			try {
				String sql = "select * from course order by name asc";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					c = new Course();
					c.setID(rs.getString(1));
					c.setName(rs.getString(2));
					c.setLecture(rs.getString(3));
					c.setYear(rs.getInt(4));
					c.setNote(rs.getString(5));
					list.add(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (order.equals("descending")) {
			try {
				String sql = "select * from course order by name desc";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					c = new Course();
					c.setID(rs.getString(1));
					c.setName(rs.getString(2));
					c.setLecture(rs.getString(3));
					c.setYear(rs.getInt(4));
					c.setNote(rs.getString(5));
					list.add(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public List<Course> getAllCourses() {
		List<Course> list = new ArrayList<Course>();
		Course c = null;
		try {
			String sql = "select * from course order by id asc";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Course();
				c.setID(rs.getString(1));
				c.setName(rs.getString(2));
				c.setLecture(rs.getString(3));
				c.setYear(rs.getInt(4));
				c.setNote(rs.getString(5));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Course getCourseByID(int ID) {
		Course c = null;
		try {
			String sql = "select * from course where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Course();
				c.setID(rs.getString(1));
				c.setName(rs.getString(2));
				c.setLecture(rs.getString(3));
				c.setYear(rs.getInt(4));
				c.setNote(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public List<Course> getCoursesByName(String InputName) {
		List<Course> list = new ArrayList<Course>();
		Course c = null;
		try {
			Statement st = conn.createStatement();
			String sql = "select * from course where name like '%" + InputName + "%'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				c = new Course();
				c.setID(rs.getString(1));
				c.setName(rs.getString(2));
				c.setLecture(rs.getString(3));
				c.setYear(rs.getInt(4));
				c.setNote(rs.getString(5));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean enrollStudent(String id_std, String id_crs, String grade) {
		boolean rs = false;
		try {
			String sql = "insert into enrollment(student_id,course_id,grade) value (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id_std);
			ps.setString(2, id_crs);
			ps.setFloat(3, Float.parseFloat(grade));

			int i = ps.executeUpdate();

			if (i == 1) {
				rs = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public List<Course> getCoursesEnrollment(int ID) {
		List<Course> list = new ArrayList<Course>();
		Course c = null;
		try {
			String sql = "select c.id, c.name, c.lecture, c.year, c.note from student s join enrollment e on s.id = e.student_id join course c on c.id = e.course_id where s.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Course();
				c.setID(rs.getString(1));
				c.setName(rs.getString(2));
				c.setLecture(rs.getString(3));
				c.setYear(rs.getInt(4));
				c.setNote(rs.getString(5));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Course> getGradeCoursesEnrollment(int ID) {
		List<Course> list = new ArrayList<Course>();
		Course c = null;
		try {
			String sql = "select * from course c join enrollment e on c.id = e.course_id where e.student_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getFloat(8));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
