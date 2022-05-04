
package Business;

import Model.FileUtil;
import Model.RatioRule;
import Model.School;
import Model.Student;
import Model.StudentFactory;
import Model.Vax;
import java.util.ArrayList;
import java.util.List;


public class ConfigureSystem {

    public static School configure() {
        List<String> CSVList = FileUtil.readFileToString("Student.txt");
        List<String> VaxList = FileUtil.readFileToString("Vaccination.txt");
        List<Vax> vaxes = new ArrayList<>();
        for (String s : VaxList) {
            vaxes.add(new Vax(s));
        }
        List<String> RatioList = FileUtil.readFileToString("Ratio.txt");
        List<RatioRule> ratioRules = new ArrayList<>();
        for (String s : RatioList) {
            ratioRules.add(new RatioRule((s)));
        }
        School neu = new School();
        for (RatioRule r : ratioRules) {
            neu.addRatioRule(r);
        }
        for (String s : CSVList) {
            Student student = StudentFactory.getInstance().getObject(s);
            for (Vax v : vaxes) {
                if (v.getStudentId() == student.getId()) {
                    student.addVax(v);
                }
            }
            neu.addStudent(student);
            neu.addAllStudentToCSV();
        }
        return neu;
    }
}
