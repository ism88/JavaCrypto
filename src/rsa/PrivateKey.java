package rsa;

import java.math.BigInteger;

public class PrivateKey {
	private BigInteger n;
	private BigInteger d;
	
	public PrivateKey(BigInteger pN, BigInteger pD){
		n = pN;
		d = pD;
	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getD() {
		return d;
	}
}
