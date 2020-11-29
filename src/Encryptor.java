import java.util.ArrayList;
import java.util.List;

public abstract class Encryptor {
    private SecretKey secretKey;

    Encryptor(){
        secretKey = createKey();
    }

    public byte[] encrypt(List<String> lines){

        ArrayList<byte[]> totalResult = new ArrayList<>();
        int totalLen = 0;
        for(String line: lines){
            totalResult.add(secretKey.encrypt(line.getBytes()));
            totalLen += line.length();

        }

        int index = 0;
        byte[] result = new byte[totalLen];

        for(byte[] aResult: totalResult){

            for(byte b: aResult){
                result[index] = b;
                index++;
            }

        }

        return result;

    }

    abstract SecretKey createKey();

}
