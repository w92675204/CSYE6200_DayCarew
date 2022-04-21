package Model;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> CSVList = FileUtil.readFileToString("Student.txt");
		for(String s: CSVList){
			System.out.println(s);
		}
		List<String> VaxList = FileUtil.readFileToString("Vax.txt");
		List<Vax> vaxes = new ArrayList<>();
		for(String s:VaxList){
			vaxes.add(new Vax(s));
		}
		List<String> RatioList = FileUtil.readFileToString("Ratio.txt");
		List<RatioRule> ratioRules = new ArrayList<>();
		for(String s: RatioList){
			ratioRules.add(new RatioRule((s)));
		}
		School neu = new School();
		for (RatioRule r:ratioRules){
			neu.addRatioRule(r);
		}
		for(String s: CSVList){
			Student student = StudentFactory.getInstance().getObject(s);
			for(Vax v:vaxes){
                            if(v.getStudentId() == student.getId())
				student.addVax(v);
			}
			neu.addStudent(student);
		}
		//neu.sortStudentByFirstName();
		neu.showStudent();
                neu.addAllStudentToCSV();		



                
	}

}
