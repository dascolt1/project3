import java.util.*;
import java.io.*;

class Project3 {

    private void readLoginFile(String filePath) {

    }

    public static void main(String args[]) throws FileNotFoundException {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        String fileToRead = "LoginsAndPasswords.txt";
        String fileToWrite = "signIn.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            String line;
            String[] arrOfStr = new String[100];
            while ((line = br.readLine()) != null) {
                arrOfStr = line.split(", ", 5);
                System.out.println(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
