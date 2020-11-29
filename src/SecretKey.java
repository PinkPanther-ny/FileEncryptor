import java.util.Random;

public class SecretKey {

    private final byte[] key;

    SecretKey(String password){
        key = new byte[64];
        new Random(password.hashCode()).nextBytes(key);
    }

    public byte[] encrypt(byte[] message){
        int m = message.length;
        int k = key.length;
        int j = 0;

        byte[] result = new byte[m];

        for(int i = 0;i<m;i++){
            result[i] = (byte) (message[i]^key[j]);
            if((j++)==k){
                j = 0;
            }
        }
        return result;
    }

    public byte[] decrypt(byte[] message){

        return encrypt(message);

    }



}
