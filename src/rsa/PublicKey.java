package rsa;

import java.math.BigInteger;

public class PublicKey {

	private BigInteger n;
	private BigInteger e;
	
	public PublicKey(BigInteger pN, BigInteger pE){
		n = pN;
		e = pE;
	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getE() {
		return e;
	}
}
