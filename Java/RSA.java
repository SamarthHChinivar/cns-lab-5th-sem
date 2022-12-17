import java.util.Random;
import java.math.BigInteger;
import java.io.DataInputStream;
import java.io.IOException;

public class RSA
{
  //Class Variables:
  private BigInteger p;
  private BigInteger q;
  private BigInteger N;
  private BigInteger phi;
  private BigInteger e;
  private BigInteger d;
  
  private int bitlength = 1024;
  private Random r;
  
  
  public RSA() //Constructor
  {
    r = new Random();
    
    p = BigInteger.probablePrime(bitlength,r);
    q = BigInteger.probablePrime(bitlength,r);
    N = p.multiply(q);
    phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    e = BigInteger.probablePrime(bitlength/2,r);
    
    while(phi.gcd(e).compareTo(BigInteger.ONE) > 0  &&  e.compareTo(phi) < 0)
    	e.add(BigInteger.ONE);
    
    d = e.modInverse(phi);
  }
  
  
  @SuppressWarnings("deprecation")
  public static void main(String[] args) throws IOException
  {
    RSA rsa = new RSA();
    
    DataInputStream in = new DataInputStream(System.in);
    
    System.out.println("Enter plain text: ");
    
    String teststr;
    teststr = in.readLine();
    
    System.out.println("Encrypting String: " +teststr);
    System.out.println("String in Bytes: " +bytesToString(teststr.getBytes()) );
    
    //encrypt
    byte[] encrypted = rsa.encrypt(teststr.getBytes());
    
    //decrypt
    byte[] decrypted = rsa.decrypt(encrypted);
    
    System.out.println("Decrypting Bytes: " +bytesToString(decrypted) );
    System.out.println("Decrypted String: " +new String(decrypted) );
  }
  
  private static String bytesToString(byte[] encrypted)
  {
    String test = "";
    
    for(byte b: encrypted)
    	test = test + Byte.toString(b);
    
    return test;
  }
  
  public byte[] encrypt(byte[] message)
  {
    return (new BigInteger(message)).modPow(e,N).toByteArray();
  }
  
  public byte[] decrypt(byte[] message)
  {
    return (new BigInteger(message)).modPow(d,N).toByteArray();
  }
  
}
