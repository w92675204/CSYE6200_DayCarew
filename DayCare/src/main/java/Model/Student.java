package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends AbstractPerson {

    private int Id;
    private int Age;
    private String FirstName;
    private String LastName;
    private int TeacherId;
    private LocalDate LastRegDate;
    private LocalDate ExpectReNewDate;
    private double GPA;
    private boolean NeedRenew;
    private int ClassId;
    private List<Vax> VaxList = new ArrayList<>();
    private String parentName;
    private String parentEmail;

    public Student(String csv) {
//        System.out.println("create student fail");
        String[] item = csv.split(",");
        this.Id = Integer.parseInt(item[0]);
        this.Age = Integer.parseInt(item[1]);
        this.FirstName = item[2];
        this.LastName = item[3];
//        System.out.println("create student fail2");
        this.LastRegDate = LocalDate.parse(item[4]);//有错
//        System.out.println("create student fail time error");
        ExpectReNewDate = LastRegDate.plusYears(1L);
        this.GPA = Double.parseDouble(item[5]);
//        this.parentName = item[6];
//        this.parentEmail = item[7];
        Scanner sc = new Scanner(csv);
        sc.useDelimiter(",");
        try {
            this.Id = sc.nextInt();
            this.Age = sc.nextInt();
            this.FirstName = sc.next();
            this.LastName = sc.next();
//            this.ImuDate = LocalDate.parse(sc.next());




        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println(csv);
        }
//        System.out.println("create student fail3");
    }

    public Student() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return this.Id;
    }

    @Override
    public void setId(int id) {
        // TODO Auto-generated method stub
        this.Id = id;
    }

    public void setAge(int age) {
        // TODO Auto-generated method stub
        this.Age = age;
    }

    public int getAge() {
        // TODO Auto-generated method stub
        return this.Age;
    }

//    @Override
//    public void setName(String name) {
//
//    }
    public void setClassId(int cid) {
        this.ClassId = cid;
    }

    public void setFirstName(String firstName) {
        // TODO Auto-generated method stub
        this.FirstName = firstName;

    }

    public String getFirstName() {
        // TODO Auto-generated method stub
        return this.FirstName;
    }

    @Override
    public String getLastName() {
        return this.LastName;
    }

    public void setTid(int tid) {
        this.TeacherId = tid;
    }

    public void checkRenew() {
        NeedRenew = LastRegDate.isBefore(LocalDate.now().minusYears(1L));
    }

    public void addVax(Vax v) {
        VaxList.add(v);
    }

    public int getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(int TeacherId) {
        this.TeacherId = TeacherId;
    }

    public LocalDate getLastRegDate() {
        return LastRegDate;
    }

    public void setLastRegDate(LocalDate LastRegDate) {
        this.LastRegDate = LastRegDate;
    }

    public LocalDate getExpectReNewDate() {
        return ExpectReNewDate;
    }

    public void setExpectReNewDate(LocalDate ExpectReNewDate) {
        this.ExpectReNewDate = ExpectReNewDate;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public boolean isNeedRenew() {
        return NeedRenew;
    }

    public void setNeedRenew(boolean NeedRenew) {
        this.NeedRenew = NeedRenew;
    }

    public List<Vax> getVaxList() {
        return VaxList;
    }

    public void setVaxList(List<Vax> VaxList) {
        this.VaxList = VaxList;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", Age=" + Age +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", GPA=" + GPA +
                ", TeacherId=" + TeacherId +
                ", ClassRoomId=" + ClassId +
                ", RegDate=" + LastRegDate +
                ", NextRenewDate" + ExpectReNewDate+
                ", NeedRenew=" + NeedRenew +
                ", Vax=" + VaxList +
                '}';
    }

    public String toCSV(){
        return Id + "," +
                Age + "," +
                FirstName + "," +
                LastName + "," +
                LastRegDate.toString() + "," +
                String.valueOf(GPA);
    }


}
