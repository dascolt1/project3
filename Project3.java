import java.util.*;
import java.io.*;

class Project3 {

    private static void readLoginFile(String filePath, Hashtable<String, String> ht) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] arrOfStr = new String[100];
            while ((line = br.readLine()) != null) {
                // gets username/password pairs
                arrOfStr = line.split(", ", 0);
                // adds them to hashtable
                ht.put(arrOfStr[0], arrOfStr[1]);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        Hashtable<String, String> hashTable = new Hashtable<String, String>();
        String fileToRead = "LoginsAndPasswords.txt";
        String fileToWrite = "signIn.txt";
        readLoginFile(fileToRead, hashTable);

    }
}
