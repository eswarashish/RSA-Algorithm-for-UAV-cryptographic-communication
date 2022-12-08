import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.swing.JOptionPane;
public class RSA {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSA() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (Exception ignored) {
        }
    }

    public String encrypt(String message) throws Exception{
        byte[] messageToBytes = message.getBytes(); 
        // the string is converted to bytes
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // inbuilt cipher function begins to encrypt the message
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        // encryption happens with the usage of publickey of uav
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        // now the according to the number of bytes of message the cipher text also gets generated
        return encode(encryptedBytes);
    }
    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
        // now the encrypted message is encoded
    }

    public String decrypt(String encryptedMessage) throws Exception{
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE,privateKey); 
        // now the cipher text is decrypted in UAV using its private key
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage,"UTF8");
    }
    private byte[] decode(String data){
        return Base64.getDecoder().decode(data);
        // the decrypted message is decoded
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        try{
            String A=JOptionPane.showInputDialog("Enter a desired number for public key generation of the base station");
            
            int a = Integer.parseInt(A);
            
            String encryptedMessage = rsa.encrypt(Integer.toString(rsakeygeneration.main(a)));
            String decryptedMessage = rsa.decrypt(encryptedMessage);
            JOptionPane.showMessageDialog(null, "Public Key of base station is encrypted using public key of UAV as follows :" +encryptedMessage +"\n"+"The message is decrypted and the public key of base station is received by UAV i.e :" +decryptedMessage, "Encrypted and decrypted form of public key of base station", JOptionPane.PLAIN_MESSAGE );
            JOptionPane.showMessageDialog(null, "Hence the public key of base station is successfully recieved by our desired drone","Thank you", JOptionPane.PLAIN_MESSAGE );
        }catch (Exception ingored){}
    }

}