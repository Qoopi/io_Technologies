package credentials;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credentials {
    private final static String pathToFile = "src/main/resources/credentials.properties";
    private static Properties prop;

    public Credentials() {
        prop = new Properties();
        readFile();
    }

    private void readFile() {
        try {
            prop.load(new FileInputStream(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return prop.getProperty("email");
    }

    public String getPassword() {
        return prop.getProperty("pass");
    }


}