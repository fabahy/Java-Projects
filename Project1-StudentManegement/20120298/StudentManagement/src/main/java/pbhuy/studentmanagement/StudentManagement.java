/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pbhuy.studentmanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author HuyBao
 */
public class StudentManagement {

    private final ArrayList<Student> listStd = new ArrayList<>();

    public void addStudent(Student std) {
        listStd.add(std);
    }

    public void removeStudent(int index) {
        listStd.remove(index);
    }

    public void changeStudent(int index, Student std) {
        listStd.remove(index);
        listStd.add(index, std);
    }
    
    public void removeAllStudents() {
        listStd.removeAll(listStd);
    }

    public void sortAscByID() {
        Collections.sort(this.listStd, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getID().compareTo(o2.getID());
            }
        });
    }

    public void sortDescByID() {
        Collections.sort(this.listStd, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getID().compareTo(o1.getID());
            }
        });
    }

    public void sortAscByGPA() {
        Collections.sort(this.listStd, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o1.getGPA() - o2.getGPA());
            }
        });
    }
    
        public void sortDescByGPA() {
        Collections.sort(this.listStd, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o2.getGPA() - o1.getGPA());
            }
        });
    }

    public ArrayList<Student> getListStd() {
        return this.listStd;
    }
}
