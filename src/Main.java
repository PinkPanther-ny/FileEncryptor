import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;


public class Main {

    public static final String ENCRYPTED_FILE_EXTENSION = "encrypted_";
    public static final String DECRYPTED_FILE_EXTENSION = "decrypted_";

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the filename:");
        String SOURCE_FILE_NAME = scanner.nextLine();

        try{

            File sourceFile = new File(SOURCE_FILE_NAME);
            byte[] sourceByte = Files.readAllBytes(sourceFile.toPath());

            System.out.println("1: Encrypt it!\t2: Decrypt it!");
            int isEncrypt = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter the password:");
            String key = scanner.nextLine();

            SecretKey secretKey = new SecretKey(key);

            byte[] encrypted, decrypted;

            if(isEncrypt == 1){
                encrypted = secretKey.encrypt(sourceByte);
                writeByteToFile(encrypted, ENCRYPTED_FILE_EXTENSION + SOURCE_FILE_NAME);
                System.out.println("Encrypted!");
            }else if(isEncrypt == 2){
                decrypted = secretKey.decrypt(sourceByte);
                writeByteToFile(decrypted, DECRYPTED_FILE_EXTENSION + SOURCE_FILE_NAME);
                System.out.println("Decrypted!");

            }
        }catch (IOException e){
            System.out.printf("FatalError! <%s> File Not Found!", SOURCE_FILE_NAME);
        }
    }

    public static void writeByteToFile(byte[] sourceByte, String filePath) throws IOException {

        var fos = new FileOutputStream(filePath);
        fos.write(sourceByte);
    }

}


