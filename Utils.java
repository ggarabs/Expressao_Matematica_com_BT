public class Utils {
    public static boolean isNumeric(String str){
        boolean ans = false;
        try {   // se a String for numérica, o programa consegue convertê-la para Double
            Double.parseDouble(str);
            ans = true;
        } catch (NumberFormatException err) {
            ans = false;
        }
        return ans;
    }
}
