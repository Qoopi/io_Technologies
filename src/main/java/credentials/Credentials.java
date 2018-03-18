package credentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credentials {
    private final static String pathToFile = "src/main/resources/credentials.properties";
    private static Properties properties;

    public Credentials() {
        properties = new Properties();
        readFile();
    }

    private void readFile() {
        try {
            properties.load(new FileInputStream(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return properties.getProperty("email");
    }

    public String getPassword() {
        return properties.getProperty("pass");
    }

}