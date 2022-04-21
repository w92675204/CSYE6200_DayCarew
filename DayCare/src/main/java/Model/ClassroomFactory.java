package Model;

public class ClassroomFactory extends AbstractFactory{
    private ClassroomFactory(){

    }
    private static ClassroomFactory instance = null;
    public static ClassroomFactory getInstance(){
        if(instance == null){
            instance = new ClassroomFactory();
        }
        return instance;
    }

    public Classroom getObject(int cid,int low,int high, int size){
        return new Classroom(cid,low,high,size);
    }

}
