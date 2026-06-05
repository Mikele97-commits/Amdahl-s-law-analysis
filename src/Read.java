import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Read {
    public static List<Integer> large() throws IOException {
        File large = new File("large.txt");
        List<Integer> list = new ArrayList<Integer>();
        BufferedReader br = new BufferedReader(new FileReader(large));
        String line= br.readLine();
        while(line !=null){
            list.add(Integer.parseInt(line));
            line = br.readLine();
        }
        return list;
    }

    public static List<Integer> medium() throws IOException {
        File large = new File("medium.txt");
        List<Integer> list = new ArrayList<Integer>();
        BufferedReader br = new BufferedReader(new FileReader(large));
        String line= br.readLine();
        while(line !=null){
            list.add(Integer.parseInt(line));
            line = br.readLine();
        }
        return list;
    }

    public static List<Integer> small() throws IOException {
        File large = new File("small.txt");
        List<Integer> list = new ArrayList<Integer>();
        BufferedReader br = new BufferedReader(new FileReader(large));
        String line= br.readLine();
        while(line !=null){
            list.add(Integer.parseInt(line));
            line = br.readLine();
        }
        return list;
    }
}
