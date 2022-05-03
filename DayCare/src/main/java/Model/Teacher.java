package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static java.util.stream.Collectors.toList;

public class Teacher extends AbstractPerson {

    private int Id;
    private int Age;
    //private String Name;
    private String LastName;
    private String FirstName;
    private int Wage;
    private int ClassroomId;
    private LocalDate ReviewDate;
    private List<Student> StudentList = new ArrayList<>();
    private int count = 0;
    private int size;


    public Teacher(int id, int size,String firstName,String lastName,int age,int wage,int cid,String reviewDate ){
        this.Id = id;
        this.size = size;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Age = age;
        //Random r = new Random();
        this.Wage = wage;
        this.ClassroomId = cid;
        this.ReviewDate = LocalDate.parse(reviewDate);

    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return Id;
    }

    @Override
    public void setId(int id) {
        // TODO Auto-generated method stub
        this.Id = id;
    }

    public void setClassroomId(int id){
        this.ClassroomId = id;
    }
    @Override
    public void setAge(int age) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getAge() {
        // TODO Auto-generated method stub
        return this.Age;
    }

    @Override
    public String getFirstName() {
        return this.FirstName;
    }

    @Override
    public String getLastName() {
        return this.LastName;
    }

    public LocalDate getReviewDate(){
        return this.ReviewDate;
    }
    
    public void deleteStudentById(int id){                
        List<Student> students = StudentList
            .stream()
            .filter(i -> i.getId()!=id)            
            .collect(toList());
        
        this.StudentList = students;        
    }

    public int getWage(){return this.Wage;}

//    @Override
//    public void setName(String name) {
//        // TODO Auto-generated method stub
//
//    }
//    @Override
//    public String getName() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
    public void addStudent(Student s){
        StudentList.add(s);
        s.setTid(this.Id);
        count++;
    }

    public boolean isEmpty(){
        return count < size;
    }
    public void showStudents(){
        if(!StudentList.isEmpty()){
            for (Student s: StudentList){
                System.out.println(s.getFirstName());
            }

        }

    }

    public List<Student> getStudentList() {
        return StudentList;
    }

    public void setStudentList(List<Student> StudentList) {
        this.StudentList = StudentList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getClassroomId() {
        return ClassroomId;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setWage(int Wage) {
        this.Wage = Wage;
    }

    public void setReviewDate(LocalDate ReviewDate) {
        this.ReviewDate = ReviewDate;
    }
    
    

    @Override
    public String toString() {
        return "Teacher{" + "Id=" + Id + ", Age=" + Age + ", LastName=" + LastName + ", FirstName=" + FirstName + ", Wage=" + Wage + ", count=" + count + ", size=" + size + '}';
    }
    
    
}

