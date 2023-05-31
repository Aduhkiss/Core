package lol.sunshinevalley.core.util;

public class StringUtils {

    public static String[] toArray(String string) {
        return string.split(" ");
    }

    public static String combine(String[] arr, int startPos) {
        StringBuilder str = new StringBuilder();

        for(int i = startPos; i < arr.length; ++i) {
            str = str.append(arr[i] + " ");
        }
        return str.toString();
    }

    public static String removeLeadingZeroes(String str) {
        String strPattern = "^0+(?!$)";
        str = str.replaceAll(strPattern, "");
        return str;
    }
}