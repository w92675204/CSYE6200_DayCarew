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
        return new Student(csv);     
    }

}
