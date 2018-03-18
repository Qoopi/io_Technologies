package credentials;

public class Credentials {

    private static String email;
    private static String pass;


    public static String getEmail() {
        if (email == null) {
            new Collector().readEmail();
        }
        return email;
    }

    static void setEmail(String email) {
        Credentials.email = email;
    }

    public static String getPass() {
        if (pass == null) {
            new Collector().readPassword();
        }
        return pass;
    }

    static void setPass(String pass) {
        Credentials.pass = pass;
    }

}
