import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Read {

    public static void main(String[] args) throws Exception {
        File file = new File("bearer.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        st = br.readLine() ;
            System.out.println(st);
    }
    }
