package Model;

public class StudentFactory extends AbstractFactory{
    private static StudentFactory instance = null;
    public static StudentFactory getInstance(){
        if(instance == null){
            instance = new StudentFactory();
        }
        return instance;
    }

    public Student getObject(String csv){
        System.out.println("getobject fail");
        System.out.println(new Student(csv));
        return new Student(csv);
        
    }

}
