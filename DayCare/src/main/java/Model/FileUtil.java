package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;



public class FileUtil {
    //convert a file to input stream
    public static List<String> readFileToString(String fileName) {
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader in= new BufferedReader(fr);
            // System.out.println("BufferedReader: '" + fileName + "'");
            String line = in.readLine();
            while(line != null) {
                list.add(line);
                line = in.readLine();
            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return list;

    }

    //convert input stream to a file
    public static void writeStringToFile(List<String> input, String fileName) {
        try {

            File ofile = new File(fileName);
            ofile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(ofile));
            // System.out.println("BufferedWriter: '" + fileName + "'");
            for(String s:input) {
                out.write(s);
                out.write("\r\n");
            }
            out.flush();
            out.close();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}

