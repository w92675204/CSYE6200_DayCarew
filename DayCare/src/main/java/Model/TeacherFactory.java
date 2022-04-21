package Model;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Random;

public class TeacherFactory extends AbstractFactory{
    private TeacherFactory(){

    }
    private static TeacherFactory instance = null;
    private static String[] dates = {"2021-04-01","2020-10-11","2021-11-11","2018-12-12","2020-01-05","2020-09-09"};
    public static TeacherFactory getInstance(){
        if(instance == null){
            instance = new TeacherFactory();
        }
        return instance;
    }

    public Teacher getObject(int tid,int size,int cid){
        Faker faker = new Faker();
        Random rand = new Random();
        return new Teacher(tid,size,faker.name().firstName(),faker.name().lastName(),rand.nextInt(20)+20,rand.nextInt(500)+400,cid,dates[rand.nextInt(5)]);
    }

}
