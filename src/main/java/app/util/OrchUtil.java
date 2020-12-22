package app.util;

public class OrchUtil {


    public static void validateRequest(String username) throws IllegalArgumentException {
        if(username == null || username.equalsIgnoreCase(""))
            throw new IllegalArgumentException("Invalid Username");
    }
}
