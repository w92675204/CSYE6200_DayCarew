package Business;

import Model.School;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentPersistenceSupport;
import java.nio.file.Paths;


public class DB4OUtil {

    private static final String FILENAME = Paths.get("Databank.db4o").toAbsolutePath().toString();// path to the data store
    private static DB4OUtil dB4OUtil;
    
    public synchronized static DB4OUtil getInstance(){
        if (dB4OUtil == null){
            dB4OUtil = new DB4OUtil();
        }
        return dB4OUtil;
    }

    protected synchronized static void shutdown(ObjectContainer conn) {
        if (conn != null) {
            conn.close();
        }
    }

    private ObjectContainer createConnection() {
        try {
            EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
            config.common().add(new TransparentPersistenceSupport());
 
            config.common().activationDepth(Integer.MAX_VALUE);
 
            config.common().updateDepth(Integer.MAX_VALUE);

    
            config.common().objectClass(School.class).cascadeOnUpdate(true); 

            ObjectContainer db = Db4oEmbedded.openFile(config, FILENAME);            
            return db;
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }

    public synchronized void storeSystem(School school) {
        ObjectContainer conn = createConnection();
        conn.store(school);
        conn.commit();
        conn.close();
    }
    
    public School retrieveSystem(){
        ObjectContainer conn = createConnection();
        ObjectSet<School> schools = conn.query(School.class); 
        School school;
        if (schools.size() == 0){
            school = ConfigureSystem.configure(); 
        }
        else{
            school = schools.get(schools.size() - 1);
        }
        conn.close();
        return school;
    }
}
