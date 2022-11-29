import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//import java.util.Random;
import java.util.Scanner;

public class SlangWords {
    public static String fileName = "slang.txt";
    public static Scanner sc = new Scanner(System.in);
    public static HashMap<String, List<String>> m = new HashMap<String, List<String>>();
    public static ArrayList<String> history = new ArrayList<String>();

    public static void readFile(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            
            String a;
            while ((a = br.readLine()) != null) {
                List<String> defi = new ArrayList<String>();

                String[] s = a.split("`");
                if (s[1].contains("|")) {
                    String[] s_tmp = s[1].split("\\|");
                    for (int i = 0; i < s_tmp.length; i++) {
                        s_tmp[i] = s_tmp[i].trim();
                    }
                    defi = Arrays.asList(s_tmp);
                }
                else {
                        defi.add(s[1]);
                }
                m.put(s[0], defi);
            }

            fr.close();
            br.close();
        }
        catch (Exception ex) {
            System.out.println("Error ");
        }
    }
    
    public static void writeFile(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            
            for (String key : m.keySet()) {
                fw.write(key + "`");
                List<String> tmp = m.get(key);
                int i = 0;
                for (i = 0; i < tmp.size() - 1; i++) {
                    fw.write(tmp.get(i) + "| ");
                }
                fw.write(tmp.get(i) + "\n");
            }

            fw.close();
        }
        catch (Exception ex) {
            System.out.println("Error");
        }
    }
    
    public static void writeHistory(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (String i : history) {
                fw.write(i + "\n");
            }

            fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: ");
        }
    }
    
    public static ArrayList<String> loadHistory (String fileName) {
        ArrayList<String> his = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            
            String a;
            while ((a = br.readLine()) != null) {
                his.add(a);
            }

            fr.close();
            br.close();
        }
        catch (Exception ex) {
            System.out.println("Error: ");
        }

        return his;
    }
        
    public static void Function_1() {
        
        System.out.println("\n");
        System.out.print("Enter a Slang word: ");
        String key = sc.nextLine();
        history.add(key);
        
        if (!m.containsKey(key)) {
            System.out.println("Not Found!");
        }
        else {
            List<String> list = m.get(key);
            System.out.println("Definiton:");
            for (String s: list) {
                System.out.println("- " + s);
            }
        }
        
        Menu();
    }

    public static void Menu() {
        
        System.out.println("\n==========================================");
        System.out.println("MENU");
        System.out.println("1. Search by Slang word");
        System.out.println("2. Search by Definition");
        System.out.println("3. History");
        System.out.println("4. Add a Slang word");
        System.out.println("5. Edit a Slang word");
        System.out.println("6. Deleta a Slang word");
        System.out.println("7. Reset");
        System.out.println("8. Random 1 Slang word");
        System.out.println("9. Fun Game (Find Definition by Slang word)");
        System.out.println("10. Fun Game (Find Slang word by Definition)");
        System.out.println("11. Exit");
        
        System.out.print(">>> Your choice: ");
        int choice = sc.nextInt();
        String s = sc.nextLine();
        
        if (choice == 1) {
            Function_1();
        }
        /*else if (choice == 2) {
            Function_2();
        }
        else if (choice == 3) {
            Function_3();
        }
        else if (choice == 4) {
            Function_4();
        }
        else if (choice == 5) {
            Function_5();
        }
        else if (choice == 6) {
            Function_6();
        }
        else if (choice == 7) {
            Function_7();
        }
        else if (choice == 8) {
            Function_8();
        }
        else if (choice == 9) {
            Function_9();
        }
        else if (choice == 10) {
            Function_10();
        }*/
        else {
            writeHistory("history.txt");
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        
        readFile("slang.txt");
        
        history = loadHistory("history.txt");
        Menu();
    }
    
}