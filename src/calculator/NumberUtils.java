package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    150518
    Stringの値の形式が数値にのっとっているかを判定する
    https://teratail.com/questions/3300
*/
public class NumberUtils {
    /*
    public static void main(String[] args){
        String num = "-1.414";
        System.out.println(isNumber(num));
    }
    */
    
    public static boolean isNumber(String num){
        String regex = "^\\-?[0-9]*\\.?[0-9]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(num);
        return m.find();
    }
    
}
