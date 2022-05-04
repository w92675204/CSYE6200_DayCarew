package Model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {

    private int size;
    private int Id;
    private int number;
    private List<Teacher> TeacherList = new ArrayList<>();
    private int count;
    private int[] AgeRange = new int[2];

    public Classroom(int id, int low,int high,int size, int number){
        this.Id = id;
        this.AgeRange[0] = low;
        this.AgeRange[1] = high;
        this.number=number;
        this.size = size;
    }

    public int getNumber() {
        return this.number;
    }

    public int[] getAgeRange(){
        return this.AgeRange;
    }

    public int getCount(){
        return this.count;
    }

    public int getSize(){
        return this.size;
    }
    public int getId(){
        return this.Id;
    }

    public void addTeacher(Teacher t){
        TeacherList.add(t);
        count++;
    }

    public boolean isEmpty(){
        return this.count < this.size;
    }

    public boolean inRange(int age){
        return this.AgeRange[0]<=age && age<this.AgeRange[1];
    }

    public List<Teacher> getTeacherList(){
        return  this.TeacherList;
    }

    public void setAgeRange(int low, int high){
        AgeRange[0] = low;
        AgeRange[1] = high;
    }

    public void showTeachers(){
        if(!TeacherList.isEmpty()){
            for (Teacher t: TeacherList){
                t.showStudents();
                System.out.println(t.getId());
            }
        }

    }


}
