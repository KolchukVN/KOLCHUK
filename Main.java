import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class Main {
    public static int counter=1;
    public static void main(String[] args1) {
        String[] valid = new String[]{"be-BY", "ru-RU", "en-US"};
        Faker faker = new Faker(new Locale(args1[0]));
        if (Arrays.asList(valid).contains(args1[0])){
            try {
                    if (args1.length == 2) {
                        for (int i = 0; i < Integer.parseInt(args1[1]); i++) {
                            GenerateMistake(DataToOutput(args1[0],faker), 0, args1[0]);

                        }
                    } else if (args1.length == 3) {
                        for (int i = 0; i < Integer.parseInt(args1[1]); i++) {
                            GenerateMistake(DataToOutput(args1[0],faker), Double.parseDouble(args1[2]), args1[0]);
                        }
                    } else System.out.println("Pls enter valid data");

            } catch (NumberFormatException e) {
                System.out.println("Pls enter valid data");
            }
        } else System.out.println("Pls enter valid data");
        }


    public static String DataToOutput(String region, Faker faker) {

        String fullname = faker.name().fullName();
        String streetAddress = faker.address().fullAddress();
        String phone = faker.phoneNumber().phoneNumber();
        faker = null;
        return fullname + "; " + streetAddress + "; " + phone;

    }

    public static void GenerateMistake(String str, double errors, String region){
        while (true){
            if (errors == 0)
            {
                MakeMistake(str, 0, region);
                break;
            } else if (errors > 0 && errors < 1)
            {
                if (counter < (int) 1/errors)
                {
                    counter++;
                    MakeMistake(str, 0, region);
                }
                else {
                    counter = 1;
                    MakeMistake(str, 1, region);
                }
                break;
            }
            else if (errors >= 1) {
                MakeMistake(str, errors, region);
                break;
            }
        }
    }

    public static void MakeMistake(String str, double NumbersOfErrors,String region){
        StringBuffer newStr = new StringBuffer(str);
        for (int k = 0; k < NumbersOfErrors; k++){
            int way = (int)(Math.random() * 3);
            if (way == 0) {
                newStr.insert((int) (Math.random() * newStr.length()), GetAlphabet(region));

            } else if (way == 1) {
                int i = (int) (Math.random() * newStr.length());
                newStr.deleteCharAt(i);

            } else if(way == 2){
                int i = (int) (Math.random() * newStr.length());
                StringBuffer t = new StringBuffer(newStr);
                if (i == newStr.length()){
                    i -= 1;
                    t.setCharAt(i, newStr.charAt(i + 1));
                    t.setCharAt(i + 1, newStr.charAt(i));
                    newStr = t;
                }

            }
        }
        if (NumbersOfErrors != 0){
            System.err.println(newStr.toString());
        } else System.out.println(newStr.toString());



    }

    public static Character GetAlphabet(String region){
        List<Character> alphabet = new ArrayList<>();
        switch (region){
            case "be-BY":
                alphabet.add((char)1110);
                alphabet.add((char)1118);
                alphabet.add((char)1038);
                for (int i = 1040; i < 1048; i++) alphabet.add((char)i);
                for (int i = 1050; i < 1065; i++) alphabet.add((char)i);
                for (int i = 1067; i < 1080; i++) alphabet.add((char)i);
                for (int i = 1082; i < 1097; i++) alphabet.add((char)i);
                for (int i = 1099; i < 1104; i++) alphabet.add((char)i);
                for (int i = 49; i < 58; i++) alphabet.add( (char)i);
                break;
            case "ru-RU":
                for (int i = 1040; i < 1104; i++) alphabet.add((char)i);
                for (int i = 49; i < 58; i++) alphabet.add((char)i);
                break;
            case "en-US":
                for (int i = 65; i < 91; i++) alphabet.add((char)i);
                for (int i = 97; i < 122; i++) alphabet.add((char)i);
                for (int i = 49; i < 58; i++) alphabet.add((char)i);
                break;
        }
        return alphabet.get((int) (Math.random() * alphabet.size()));
    }
}
