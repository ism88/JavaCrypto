package rsa;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
	
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	public RSA(int keySize){
		BigInteger p = BigInteger.probablePrime(keySize/2, new Random());
		BigInteger q = BigInteger.probablePrime(keySize/2, new Random());
		BigInteger n = p.multiply(q);
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
	    BigInteger lcm = lcm(p.subtract(BigInteger.ONE),q.subtract(BigInteger.ONE));
	    BigInteger e = calculate_e(lcm);
	    BigInteger d = e.modInverse(phi);
	    publicKey = new PublicKey(n, e);
	    privateKey = new PrivateKey(n,d);
	}
	
	public BigInteger calculate_e(BigInteger lcm){
		for(Integer i = 16; i<Integer.MAX_VALUE; i++){
			if(areCoprime(new BigInteger(i.toString()), lcm)) return new BigInteger(i.toString());
		}
		return null;
	}
	public boolean areCoprime(BigInteger a, BigInteger b){
		return a.gcd(b).intValue() == 1;
	}
	
	public byte[] encrypt(PublicKey public_key, String msg){
		return new BigInteger(msg.getBytes()).modPow(public_key.getE(),public_key.getN()).toByteArray();
	}
	
	public byte[] decrypt(PrivateKey private_key, byte[] msg){
		return new BigInteger(msg).modPow(private_key.getD(),private_key.getN()).toByteArray();
	}
	
	// lcm(a,b) * gcd(a,b) = ab
	public BigInteger lcm(BigInteger a, BigInteger b){
        BigInteger gcd = a.gcd(b);
        return a.multiply(b).divide(gcd);
    }
	
	public static void main(String[] args) {
		RSA r = new RSA(4096);
		byte[] c = r.encrypt(r.publicKey, "hi there!");
		byte[] p = r.decrypt(r.privateKey, c);
		System.out.println("CypherText: "+new String(c));
		System.out.println("PlainText: "+new String(p));

	}
}
