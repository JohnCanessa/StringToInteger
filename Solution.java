import java.util.Arrays;
import java.util.Scanner;


/**
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class Solution {

    
    /**
     * Flags if an integer is positive or negative.
     */
    enum SIGN {
        POS, NEG, UNKNOWN
    }


    /**
     * Implement atoi which converts a string to an integer.
     * 
     * Runtime: 4 ms, faster than 18.46% of Java online submissions.
     * Memory Usage: 39 MB, less than 38.01% of Java online submissions.
     */
    static int myAtoi1(String str) {
        
        // **** list of digits ****
        String digits = "0123456789";

        // **** value to be returned ****
        int result = 0;

        // **** remove leading and trailing spaces ****
        str = str.trim();

        // **** sanity check ****
        if (str.equals("") || str.equals("+") | str.equals("-"))
           return 0;

        // **** positive sign****
        SIGN sign = SIGN.POS;

        // **** check for leading sign ****
        if ((str.charAt(0) == '-') || (str.charAt(0) == '+')) {

            // **** negative value ****
            if (str.charAt(0) == '-')
                sign = SIGN.NEG;
    
            // **** skip leading sign ****
            str = str.substring(1);

            // **** check if we have an empty string ****
            if (str.equals(""))
                return 0;
        }

        // **** split ****
        String[] words = str.split(" ");

        // **** for ease of use ****
        str = words[0];
        
        // **** check if we have an empty string ****
        if (str.equals(""))
            return 0;

        // **** check if we have something else but digits ****
        for (int i = 0; i < str.length(); i++) {

            // ***** ****
            char ch = str.charAt(i);

            // **** check if not a digit ****
            if (digits.indexOf(ch, 0) == -1) {

                // **** ****
                str = str.substring(0, i);

                // **** check if we have an empty string ****
                if (str.equals(""))
                    return 0;

                // **** ****
                break;
            }
        }

        // **** convert the integer string to binary (may throw exception) ****
        try {
            result = Integer.parseInt(str);
        } catch (Exception e) {
            if (sign == SIGN.POS)
                return Integer.MAX_VALUE;
            else
                return Integer.MIN_VALUE;
        }

        // **** return positive or negative result ****
        return sign == SIGN.POS ? result : -result;
    }


    /**
     * Implement atoi which converts a string to an integer.
     * 
     * 3 ms, faster than 25.31% of Java online submissions.
     * Memory Usage: 38.6 MB, less than 96.38% of Java online submissions.
     * 
     */
    static int myAtoi0(String str) {

        // **** list of digits ****
        String digits = "0123456789";

        // **** for starters ****
        SIGN sign   = SIGN.POS;
        int result  = 0;

        // **** remove leading and trailing spaces ****
        str = str.trim();

        // **** check for blank strings ****
        if (str.equals(""))
            return 0;

        // **** take care of leading sign ****
        if ((str.charAt(0) == '-') || (str.charAt(0) == '+')) {

            // **** ****
            if (str.charAt(0) == '-')
                sign = SIGN.NEG;
    
            // **** skip leading sign ****
            str = str.substring(1);
        }

        // **** traverse the string ****
        for (int i = 0; i < str.length(); i++) {

            // **** get the current character ****
            char ch = str.charAt(i);

            // **** check if current character is NOT an integer ****
            if (digits.indexOf(ch, 0) == -1) {
                break;
            }

            // **** update the result (throws exception) ****
            try {
                result = Math.multiplyExact(result, 10);
                result = Math.addExact(result, ch - '0');
            } catch (Exception e) {
                if (sign == SIGN.POS)
                    return Integer.MAX_VALUE;
                else
                    return Integer.MIN_VALUE;
            }
        }

        // **** return positive or negative result ****
        return sign == SIGN.POS ? result : -result;
    }







    /**
     * Implement atoi which converts a string to an integer.
     * 
     * Runtime: 2 ms, faster than 54.72% of Java online submissions.
     * Memory Usage: 39.2 MB, less than 20.93% of Java online submissions.
     */
    static int myAtoi(String str) {

        // **** sanity checks(s) ****
        if (str.length() == 0)
            return 0;

        // **** initialization ****
        int i       = 0;
        int sign    = 1;
        int result  = 0;

        // **** discard leading white space(s) ****
        while (i < str.length() && str.charAt(i) == ' ')
            i++;

        // **** check for blank string ****
        if (str.length() == 0)
            return 0;
            
        // **** assign sign ****
        if (i < str.length() && (str.charAt(i) == '+' || str.charAt(i) == '-')){
            if (str.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (str.charAt(i) == '+'){
                sign = 1;
                i++;
            }
        }   

        // **** process digits (compute result) ****
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {

            // **** check if we overflowed ****
            if (result > Integer.MAX_VALUE / 10 || 
                (result == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // **** multiply by 10 and add current digit (update result) ****
            result = result * 10 + (str.charAt(i++) - '0');
        }

        // **** return signed result ****
        return sign * result;
    }






    /**
     * Test scaffolding.
     */
    public static void main(String[] args) {
        
        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read the string ****
        String str = sc.nextLine();

        // **** close scanner ****
        sc.close();

        // **** display the string ****
        System.out.println("main <<< str ==>" + str + "<==");

        // **** convert string to integer (if possible) and display value ****
        System.out.println("main <<< myAtoi0: " + myAtoi0(str));

        // **** convert string to integer (if possible) and display value ****
        System.out.println("main <<< myAtoi1: " + myAtoi1(str));

        // **** convert string to integer (if possible) and display value ****
        System.out.println("main <<< myAtoi: " + myAtoi(str));
    }

 }