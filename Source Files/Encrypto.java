import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Encrypto {
    final static String txtFilePath = "Example: C:\\Users\\lenovo\\Desktop\\Input\\start.txt";
    final static String FolderFilePath = "Example: C:\\Users\\lenovo\\Desktop\\Input";

    public static void printProgramOptions() {
        System.out.println("Encrypto (Cryptanalyzer) \n version 1 \n What would you like to do? \n Press 1 - Encrypt File \n Press 2 - Decrypt File \n Press 3 - Brute Force Attack \n Press 4 - exit");
    }

    public static ArrayList<String> inputFileSourceAndDestination(Scanner scan, int purpose) {
        ArrayList<String> inputs = new ArrayList<>();
        switch (purpose) {
            case 1:
                System.out.println("Selected 1 - Encrypt File");
                System.out.println("Enter path to .txt file you want to encrypt");
                System.out.println(txtFilePath);
                String src = scan.nextLine();
                inputs.add(src);
                System.out.println("Enter output folder path");
                System.out.println(FolderFilePath);
                String dest = scan.nextLine();
                inputs.add(dest);
                break;
            case 2:
                System.out.println("Selected 2 - Decrypt File");
                System.out.println("Enter path to .txt file you want to decrypt");
                System.out.println(txtFilePath);
                src = scan.nextLine();
                inputs.add(src);
                System.out.println("Enter output folder path");
                System.out.println(FolderFilePath);
                dest = scan.nextLine();
                inputs.add(dest);
                break;
            case 3:
                System.out.println("Selected 3 - Brute Force Attack");
                System.out.println("Enter path to cipher .txt file you want to brute-force");
                System.out.println("Brute Force will not be 100% accurate");
                System.out.println(txtFilePath);
                src = scan.nextLine();
                inputs.add(src);
                System.out.println("Enter output folder path");
                System.out.println(FolderFilePath);
                dest = scan.nextLine();
                inputs.add(dest);
                break;
            default:
                System.out.println("Error");
        }
        return inputs;
    }

    public static void start() {
        printProgramOptions();
        Scanner scan = new Scanner(System.in);
        int input = Integer.parseInt(scan.nextLine());
        while (!(input == 4)) {
            if (input == 1) {
                ArrayList<String> inputs = inputFileSourceAndDestination(scan, 1);
                try (BufferedReader br = new BufferedReader(new FileReader(inputs.get(0)));
                     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputs.get(1) + "\\encrypted.txt"))) {
                    System.out.println("Enter encryption key ~ '1 - 26'");
                    int key = Integer.parseInt(scan.nextLine());
                    while (br.ready()) {
                        String line = br.readLine();
                        System.out.println(CaesarCipherList.encrypt(line, key));
                        bufferedWriter.write(CaesarCipherList.encrypt(line, key));
                        bufferedWriter.newLine();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Encryption performed successfully");
                System.out.println("File path: " + inputs.get(1) + "\\encrypted.txt");
                printProgramOptions();
                input = Integer.parseInt(scan.nextLine());
            } else if (input == 2) {
                ArrayList<String> inputs = inputFileSourceAndDestination(scan, 2);
                try (BufferedReader br = new BufferedReader(new FileReader(inputs.get(0)));
                     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputs.get(1) + "\\decrypted.txt"))) {
                    System.out.println("Enter decryption key");
                    int key = Integer.parseInt(scan.nextLine());
                    while (br.ready()) {
                        String line = br.readLine();
                        System.out.println(CaesarCipherList.decrypt(line, key));
                        bufferedWriter.write(CaesarCipherList.decrypt(line, key));
                        bufferedWriter.newLine();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Decrypted successfully!");
                System.out.println("File path: " + inputs.get(1) + "\\decrypted.txt");
                printProgramOptions();
                input = Integer.parseInt(scan.nextLine());
            } else if (input == 3) {
                ArrayList<String> inputs = inputFileSourceAndDestination(scan, 2);
//                int key = CaesarCipherList.bruteForceAttack();
                try (BufferedReader br = new BufferedReader(new FileReader(inputs.get(0)));
                     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputs.get(1) + "\\bruteForce.txt"))) {
                    while (br.ready()) {
                        String line = br.readLine();
                        int key = CaesarCipherList.bruteForceAttack(line);
                        bufferedWriter.write(CaesarCipherList.decrypt(line, key));
                        bufferedWriter.newLine();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Brute Force Performed Successfully! File saved as bruteForce.txt");
                System.out.println("File path: " + inputs.get(1) + "\\bruteForce.txt");
                printProgramOptions();
                input = Integer.parseInt(scan.nextLine());
            } else {
                System.out.println("Invalid Input Entered!!!");
                input = Integer.parseInt(scan.nextLine());
            }
        }
        System.out.println("Exiting program...");
    }

    public static void main(String[] args) {
    }
}
