public class Utils {
    public static boolean isNumeric(String str){
        boolean ans = false;
        try {
            Double.parseDouble(str);
            ans = true;
        } catch (NumberFormatException err) {
            ans = false;
        }
        return ans;
    }
}
