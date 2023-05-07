import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaesarCipherList {
    private static final ArrayList<Character> alphabets = new ArrayList<>(
            List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',', '"', ':', '-', '!', '?', ' '));

    public static String encrypt(String message, int shiftKey) {
        message = message.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int charPosition = alphabets.indexOf(message.charAt(i));
            int keyVal = (shiftKey + charPosition) % alphabets.size();
            char replaceVal = alphabets.get(keyVal);
            cipherText.append(replaceVal);
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, int shiftKey) {
        cipherText = cipherText.toLowerCase();
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            int charPosition = alphabets.indexOf(cipherText.charAt(i));
            int keyVal = (charPosition - shiftKey) % alphabets.size();
            if (keyVal < 0) {
                keyVal = alphabets.size() + keyVal;
            }
            char replaceVal = alphabets.get(keyVal);
            message.append(replaceVal);
        }
        return message.toString();
    }

    public static int bruteForceAttack(String cipherText) {
        for (int i = 1; i <= 26; i++) {
            String checkStr = decrypt(cipherText, i).trim();
            Pattern pattern = Pattern.compile("\\s");
            Matcher matcher = pattern.matcher(checkStr);
            boolean found = matcher.find();
            if (found) {
                List<String> arrayListString = new ArrayList<>();
                arrayListString = Arrays.asList(checkStr.split(" "));
                int validWordCount = 0;
                for (String word : arrayListString) {
                    if (word.matches("\\\"?[a-zA-Z]+[,.!?\\\"\\-]*")) {
                        validWordCount++;
                    }
                }
                if (validWordCount == arrayListString.size()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /* For testing only */
//        Scanner sc = new Scanner(System.in);
//        String message;
//        int key;
//        System.out.print("Enter the String for Encryption:");
//        message = sc.nextLine();
//        System.out.println("\n\nEnter Shift Key:");
//        key = Integer.parseInt(sc.nextLine());
////        System.out.println("\nEncrypted msg:" + encrypt(message, key));
////        System.out.println("\nDecrypted Message:" + decrypt(encrypt(message, key), key));
//        System.out.println("KEY: " + bruteForceAttack(encrypt(message, key)));
////        System.out.println("\nDecrypted Message:" + decrypt(encrypt(message, key), bruteForceAttack(encrypt(message, key))));
    }
}
