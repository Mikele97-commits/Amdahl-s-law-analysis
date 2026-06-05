import java.io.*;
import java.util.*;

public class Create {
    public static void small() throws IOException {
        File file = new File("small.txt");
        if(!file.exists()) {
        file.createNewFile();
        }

        List<Integer> list = new ArrayList<>();

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for(int i = 1; i <= 10000; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for(int i = 0; i <= 9999; i++) {
            bw.write(String.valueOf(list.get(i)));
            bw.newLine();
        }
        bw.close();
    }

    public static void medium() throws IOException {
        File file = new File("medium.txt");
        if(!file.exists()) {
            file.createNewFile();
        }

        List<Integer> list = new ArrayList<>();

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for(int i = 1; i <= 100000; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for(int i = 0; i <= 99999; i++) {
            bw.write(String.valueOf(list.get(i)));
            bw.newLine();
        }
        bw.close();
    }

    public static void large() throws IOException {
        File file = new File("large.txt");
        if(!file.exists()) {
            file.createNewFile();
        }

        List<Integer> list = new ArrayList<>();

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for(int i = 1; i <= 1000000; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for(int i = 0; i <= 999999; i++) {
            bw.write(String.valueOf(list.get(i)));
            bw.newLine();
        }
        bw.close();
    }

    public static void all() throws IOException {
        large();
        medium();
        small();
    }
}
