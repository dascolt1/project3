/*
* Professor Reid CS 520
* Tommy D'Ascoli and Prashant Patil
*/

import java.util.*;
import java.io.*;

class Project3 {

    private static void readLoginFile(String filePath, Hashtable<String, String> ht) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] arrOfStr = new String[2];
            while ((line = br.readLine()) != null) {
                // gets username/password pairs
                arrOfStr = line.split(", ", 0);
                // adds them to hashtable
                ht.put(arrOfStr[0], arrOfStr[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeLogFile(String filePath, String logOutput) {
        // TODO implement file writing
    }

    private static void handleLogin(String filePath, boolean login) {
        int numberOfTries = 3;
        if (login == true) {
            System.out.println("Login Successful");
            writeLogFile(filePath, "Test");
            System.exit(0);
        } else {
            System.out.println("Username or password incorrect.");
            numberOfTries--;
            if (numberOfTries == 0) {
                System.out.println("Account locked, please try again in an hour.");
            }
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        Hashtable<String, String> hashTable = new Hashtable<String, String>();
        String fileToRead = "LoginsAndPasswords.txt";
        String fileToWrite = "signIn.txt";

        String username = "jjohns@stevens.edu";
        String password = "0ski22";

        readLoginFile(fileToRead, hashTable);

        if (hashTable.get(username).equals(password)) {
            handleLogin(fileToWrite, true);
        } else {
            handleLogin(fileToWrite, false);
        }
    }
}
