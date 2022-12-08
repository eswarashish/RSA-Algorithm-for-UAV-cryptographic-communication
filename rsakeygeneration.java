import java.util.*;

import javax.swing.JOptionPane;

import java.math.BigInteger;

public class rsakeygeneration{


public static int main(int i) {
   //Lets us create two random integers to get few prime numbers
   Random r1 = new Random(System.currentTimeMillis());
   Random r2 = new Random(System.currentTimeMillis()*10);

    int pubKey = i;

   BigInteger p = BigInteger.probablePrime(32, r1); 
   BigInteger q = BigInteger.probablePrime(32, r2); 
   /*
   Random numbers r1 and r2 are created so that 
   random bits are taken and prime numbers 
   are created usign the primality test */
   
   BigInteger p_1= p.subtract(new BigInteger("1"));//p-1
   BigInteger q_1= q.subtract(new BigInteger("1"));//q-1
   /*Euler's totient function 
   (also called the Phi function) counts the number 
   of positive integers less than n that are coprime to n.  */
   BigInteger phi = p_1.multiply(q_1);
   //now lets generate the public key
   while(true){
    BigInteger GCD = phi.gcd(new BigInteger(""+pubKey));
 // so if GCD is equal to one they are co 
 //primes hence we would get our desirred numbers
    if (GCD.equals(BigInteger.ONE)) {
        break;
    }
    pubKey++;
}
   BigInteger Pubkey= new BigInteger(""+pubKey);
   BigInteger Prvkey= Pubkey.modInverse(phi);
   JOptionPane.showMessageDialog(null, "The desired set of public and private keys generated using RSA is : " +"\n public key: "+Pubkey +"\n private key: "+Prvkey, "Pair of keys of base station", JOptionPane.PLAIN_MESSAGE );

   JOptionPane.showMessageDialog(null, " Now the public key of base station is encrypted using the public key of the drone \n");
   return pubKey;

}

}