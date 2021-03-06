package ro.ase.ism.sap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class HashFunction {
	
	public static byte[] getHash(
			String fileName, String provider, String type) 
					throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
		
		byte[]  hashValue = null;
		
		File file = new File(fileName);
		if(!file.exists()) {
			System.out.println("File does not exist");
			return null;
		}
		
		FileInputStream fis = new FileInputStream(file);
		byte[] inputBuffer = new byte[8];
		int noBytes;
		
		MessageDigest md = MessageDigest.getInstance(type, provider);
		
		while((noBytes = fis.read(inputBuffer)) != -1) {
			md.update(inputBuffer,0,noBytes);
		}
		
		fis.close();
		
		hashValue = md.digest();
		
		return hashValue;
	}
	
	
	public static byte[] getHash(byte[] values, String provider, String type) throws NoSuchAlgorithmException, NoSuchProviderException {
		
		byte[] hashValue = null;
		
		MessageDigest ms = MessageDigest.getInstance(type, provider);
		hashValue = ms.digest(values);
		
		return hashValue;
	}
	
	
}
