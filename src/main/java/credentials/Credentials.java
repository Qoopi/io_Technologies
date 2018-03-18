package credentials;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credentials {
    private final static String pathToFile = "src/main/resources/credentials.prop";
    private static Properties prop;
    private static String email;
    private static String pass;

    public Credentials() {
        prop = new Properties();
        readFile();
    }

    private static void readFile() {
        try {
            prop.load(new FileInputStream(pathToFile));
            setEmail(prop.getProperty("email"));
            setPass(prop.getProperty("pass"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return pass;
    }

    static void setEmail(String email){
        Credentials.email = email;
    }

    public static void setPass(String pass) {
        Credentials.pass = pass;
    }
}