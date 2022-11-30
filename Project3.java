/*
* Professor Reid CS 520
* Tommy D'Ascoli and Prashant Patil
* Github repo: https://github.com/dascolt1/project3
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
            String[] usernamePasswordPairs = new String[2];

            while ((line = br.readLine()) != null) {
                // gets username/password pairs
                usernamePasswordPairs = line.split(", ", 0);
                // adds them to hashtable
                ht.put(usernamePasswordPairs[0], usernamePasswordPairs[1]);
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

    private static void handleFailedLogin(String filePath, String username, int numberOfTries)
            throws UnknownHostException {

        System.out.println("Username or password incorrect.");

        String date = getDate();
        String ipAddress = getIpAddress();
        String log = "[FAILED] " + username + " <" + ipAddress + "> " + date + "\n";

        writeLogFile(filePath, log);

        if (numberOfTries == 0) {
            System.out.println("Account locked, please try again in an hour.");
            String failedThreeTimes = "[MULTIPLE FAILURES - POTENTIAL SECURITY WARNING] " + username + " <" + ipAddress
                    + "> " + date + "\n";
            writeLogFile(filePath, failedThreeTimes);
            System.exit(0);
        }
    }

    private static void handleInvalidUsernameFailedLogin(String filePath, String username) throws UnknownHostException {

        System.out.println("Username does not exist.");

        String date = getDate();
        String ipAddress = getIpAddress();
        String log = "[FAILED] " + username + " <" + ipAddress + "> " + date + "\n";

        writeLogFile(filePath, log);
        System.exit(0);
    }

    private static void handleSuccessfulLogin(String filePath, String username) throws UnknownHostException {
        System.out.println("Login Successful");

        String date = getDate();
        String ipAddress = getIpAddress();
        String log = "[SUCCESS] " + username + " <" + ipAddress + "> " + date + "\n";

        writeLogFile(filePath, log);

        System.exit(0);
    }

    private static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void main(String args[]) throws FileNotFoundException, UnknownHostException {
        Hashtable<String, String> hashTable = new Hashtable<String, String>();
        final String fileToRead = "LoginsAndPasswords.txt";
        final String fileToWrite = "signIn.txt";

        boolean continuePrompt = true;
        Scanner sc = new Scanner(System.in);

        int correctTriesLimit = 3;

        readLoginFile(fileToRead, hashTable);

        while (continuePrompt) {
            System.out.println("Please enter username:");
            String username = sc.nextLine();
            System.out.println("Please enter password:");
            String password = sc.nextLine();

            if (hashTable.get(username) != null) {
                if (hashTable.get(username).equals(password)) {
                    handleSuccessfulLogin(fileToWrite, username);
                } else {
                    correctTriesLimit--;
                    handleFailedLogin(fileToWrite, username, correctTriesLimit);
                }
            } else {
                handleInvalidUsernameFailedLogin(fileToWrite, username);
            }
        }
        sc.close();
    }
}
