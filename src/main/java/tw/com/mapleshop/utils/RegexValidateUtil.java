package tw.com.mapleshop.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正則表達式
public class RegexValidateUtil {

    static boolean flag = false;
    static String regex = "";

    public static  boolean check(String str, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean checkEmail(String email) {
        String regex = "^[_a-z0-9A-Z]+([.][_a-z0-9A-Z]+)*@[a-z0-9A-Z]+([.][a-z0-9A-Z]+)*$";
        return check(email, regex);
    }

    public static boolean checkPhone(String phone) {
        String regex = "09\\d{8}";
        return check(phone, regex);
    }

}
