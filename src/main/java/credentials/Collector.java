package credentials;

import javax.xml.bind.PropertyException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class Collector {

    private static final String credentialFile = "src/main/java/credentials/credentials.properties";
    private final Properties prop = new Properties();
    private InputStream input = null;

    void readEmail() {
        try {
            input = new FileInputStream(credentialFile);
            // load a properties file
            prop.load(input);
            if (prop.get("email") != null && !prop.getProperty("email").isEmpty()) {
                Credentials.setEmail(prop.getProperty("email"));
            } else {
                throw new PropertyException("ERR: property 'email' not found");
            }

        } catch (IOException | PropertyException io) {
            io.printStackTrace();
            System.exit(1);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void readPassword() {
        try {
            input = new FileInputStream(credentialFile);
            // load a properties file
            prop.load(input);

            if (prop.get("pass") != null && !prop.getProperty("pass").isEmpty()) {
                Credentials.setPass(prop.getProperty("pass"));
            } else {
                throw new PropertyException("ERR: property 'pass' not found");
            }

        } catch (IOException | PropertyException io) {
            io.printStackTrace();
            System.exit(1);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
