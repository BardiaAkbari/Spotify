package Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Test_1 {
    public static void main(String[] args) throws JsonProcessingException {
        String kir = "bardia.txt";
        int i = kir.lastIndexOf('.');
        int j = kir.length();
        String what = kir.substring(i+1);
        System.out.println(what);

    }
}
