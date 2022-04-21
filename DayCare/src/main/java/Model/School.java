package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class School {
    private List<Classroom> ClassroomList = new ArrayList<>();
    private List<AbstractPerson> TeacherList = new ArrayList<>();
    private List<Student> StudentList = new ArrayList<>();
    private int tid;
    private int cid;
    private List<RatioRule> ratioRules = new ArrayList<>();





    public School(){

        tid = 0;
        cid = 0;
        //Classroom c = new Classroom();
        //Classroom c = ClassroomFactory.getInstance().getObject(cid++,0,35);
//        for(int i = 0; i < 3; i++){
//            c.addTeacher(generateTeacher(3));
//
//        }
//        ClassroomList.add(c);

    }
    public void addAllStudentToCSV(){
        List<String> CSVList = new ArrayList<>();        
        for (Student s : StudentList) {
            CSVList.add(s.toCSV());
        }        
        FileUtil.writeStringToFile(CSVList, "Student.txt");
    }
    
    public void addRatioRule(RatioRule r){
        ratioRules.add(r);
    }

    public Teacher generateTeacher(int size,int cid){
        //Teacher t = new Teacher(tid++);
        Teacher t = TeacherFactory.getInstance().getObject(tid++,size,cid);
        return t;
    }

//    private void addTeacher(Teacher t){
//        TeacherList.add(t);
//        addToClassroom(t);
//
//
//
//    }

    public void addStudent(Student s){
        StudentList.add(s);
        s.checkRenew();
        addToClassroom(s);

    }
    
    public AbstractPerson findStudentById(int id) {
        for(AbstractPerson p : StudentList) {
            if(p.getId() == id)
                return p;
        }
        return null;
    }
    
    public void deleteStudentById(int studentId) {
        List<Student> students = StudentList
            .stream()
            .filter(i -> i.getId()!=studentId)            
            .collect(toList());
        
        this.StudentList = students;        
    }

//    private void addToClassroom(Teacher t){
//        for(Classroom c : ClassroomList){
//            if (c.isEmpty()){
//                c.addTeacher(t);
//            }
//            else{
//                Classroom c1 = new Classroom();
//                c1.addTeacher(t);
//                ClassroomList.add(c1);
//
//            }
//        }
//    }

    private void addToClassroom(Student s){
        if (ClassroomList.isEmpty()){
            for (RatioRule r:ratioRules){
                if (r.inRange(s.getAge())){
                    Classroom c = ClassroomFactory.getInstance().getObject(cid++,r.getLow(),r.getHigh(), r.getClassroomSize());
                    Teacher t = generateTeacher(r.getGroupSize(),c.getId());
                    s.setClassId(c.getId());
                    t.addStudent(s);
                    c.addTeacher(t);
                    for(int i = 0; i < r.getClassroomSize()-1; i++){
                        c.addTeacher(generateTeacher(r.getGroupSize(),c.getId()));

                    }
                    ClassroomList.add(c);
                    return;

                }
            }
        }
        for(Classroom c : ClassroomList){
            if (c.inRange(s.getAge())){
                //System.out.println(s.getId()+"in range");
                for(Teacher t:c.getTeacherList()){
                    if(t.isEmpty()){
                        s.setClassId(c.getId());
                        t.addStudent(s);
                        return;
                    }

                }
            }

        }
        for (RatioRule r:ratioRules){
            if (r.inRange(s.getAge())){
                Classroom c = ClassroomFactory.getInstance().getObject(cid++,r.getLow(),r.getHigh(),r.getClassroomSize());
                Teacher t = generateTeacher(r.getGroupSize(),c.getId());
                s.setClassId(c.getId());
                t.addStudent(s);
                c.addTeacher(t);
                for(int i = 0; i < r.getClassroomSize()-1; i++){
                    c.addTeacher(generateTeacher(r.getGroupSize(),c.getId()));

                }
                ClassroomList.add(c);

            }
        }

//        if (0<s.getAge() && s.getAge()<=35){
//            //Classroom c = new Classroom(cid++,0,35);
//            Classroom c = ClassroomFactory.getInstance().getObject(cid++,r.getLow(),r.getHigh());
//            Teacher t = generateTeacher(r.getSize());
//            s.setClassId(c.getId());
//            t.addStudent(s);
//            for(int i = 0; i < 2; i++){
//                c.addTeacher(generateTeacher(r.getSize()));
//
//            }
//            ClassroomList.add(c);
//        }
//        else if(35<=s.getAge() && s.getAge()<=60){
//            //Classroom c = new Classroom(cid++,35,60);
//            Classroom c = ClassroomFactory.getInstance().getObject(cid++,35,60);
//            Teacher t = generateTeacher(3);
//            s.setClassId(c.getId());
//            t.addStudent(s);
//            for(int i = 0; i < 2; i++){
//                c.addTeacher(generateTeacher(3));
//
//            }
//            ClassroomList.add(c);
//        }
//        else{
//            //Classroom c = new Classroom(cid++,60,9999);
//            Classroom c = ClassroomFactory.getInstance().getObject(cid++,60,9999);
//            Teacher t = generateTeacher(3);
//            s.setClassId(c.getId());
//            t.addStudent(s);
//            for(int i = 0; i < 2; i++){
//                c.addTeacher(generateTeacher(3));
//
//            }
//            ClassroomList.add(c);
        //}




    }

    public void showStudent(){
        for (AbstractPerson s : StudentList){
            System.out.println(s);
        }
    }
    public List<Student> getStudentList(){
        return StudentList;
    }


    public void sortStudentByID() {
        StudentList.sort(new Comparator<AbstractPerson>() {
            @Override
            public int compare(AbstractPerson p1, AbstractPerson p2) {
                return (Integer.compare(p1.getId(), p2.getId()));
            }
        });



    }
    public void sortStudentByAge() {
        StudentList.sort(new Comparator<AbstractPerson>() {
            @Override
            public int compare(AbstractPerson p1, AbstractPerson p2) {
                return (Integer.compare(p1.getAge(), p2.getAge()));
            }
        });

    }

    public void sortStudentByLastName() {
        StudentList.sort(new Comparator<AbstractPerson>() {
            @Override
            public int compare(AbstractPerson p1, AbstractPerson p2) {
                return (p1.getLastName().compareTo(p2.getLastName()));
            }
        });
    }

    public void sortStudentByFirstName() {
        StudentList.sort(new Comparator<AbstractPerson>() {
            @Override
            public int compare(AbstractPerson p1, AbstractPerson p2) {
                return (p1.getFirstName().compareTo(p2.getFirstName()));
            }
        });
    }

    public void showAll(){
        for (Classroom c: ClassroomList){
            c.showTeachers();
        }
    }

    public List<Classroom> getClassroomList() {
        return ClassroomList;
    }

    public void setClassroomList(List<Classroom> ClassroomList) {
        this.ClassroomList = ClassroomList;
    }

    public List<AbstractPerson> getTeacherList() {
        return TeacherList;
    }

    public void setTeacherList(List<AbstractPerson> TeacherList) {
        this.TeacherList = TeacherList;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public List<RatioRule> getRatioRules() {
        return ratioRules;
    }

    public void setRatioRules(List<RatioRule> ratioRules) {
        this.ratioRules = ratioRules;
    }






}
