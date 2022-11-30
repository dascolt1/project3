/*
* Professor Reid CS 520
* Tommy D'Ascoli and Prashant Patil
*/

import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
        try {
            FileWriter fWriter = new FileWriter(filePath, true);

            fWriter.write(logOutput);

            fWriter.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    private static String getIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    private static void handleLogin(String filePath, boolean login, String username) throws UnknownHostException {
        int numberOfTries = 3;
        if (login == true) {
            System.out.println("Login Successful");

            String date = getDate();
            String ipAddress = getIpAddress();
            String log = "[SUCCESS] " + username + " <" + ipAddress + "> " + date + "\n";

            writeLogFile(filePath, log);

            System.exit(0);
        } else {
            System.out.println("Username or password incorrect.");
            numberOfTries--;

            String date = getDate();
            String ipAddress = getIpAddress();
            String log = "[FAILED] " + username + " <" + ipAddress + "> " + date + "\n";

            writeLogFile(filePath, log);

            if (numberOfTries == 0) {
                System.out.println("Account locked, please try again in an hour.");
                String failedThreeTimes = "[MULTIPLE FAILURES - POTENTIAL SECURITY WARNING] " + username + " <"
                        + ipAddress + "> " + date + "\n";
                writeLogFile(filePath, failedThreeTimes);
            }
        }
    }

    private static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void main(String args[]) throws FileNotFoundException, UnknownHostException {
        Hashtable<String, String> hashTable = new Hashtable<String, String>();
        String fileToRead = "LoginsAndPasswords.txt";
        String fileToWrite = "signIn.txt";
        // TODO make login prompt

        String username = "jjohns@stevens.edu";
        String password = "0ski22";

        readLoginFile(fileToRead, hashTable);

        if (hashTable.get(username) != null) {
            if (hashTable.get(username).equals(password)) {
                handleLogin(fileToWrite, true, username);
            } else {
                handleLogin(fileToWrite, false, username);
            }
        } else {
            handleLogin(fileToWrite, false, username);
        }
    }
}
