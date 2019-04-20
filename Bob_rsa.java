//package rsa;

import java.io.*;
import java.net.*;
import java.util.*;

public class Bob {
	private static Socket socket;
	private static DataInputStream din;
	private static DataOutputStream dout;
	private static long n;
	private static long phin;
	private static long d;
	private static long e;
	private static long plain;
	private static long cipher;
	public static void main(String[] args) throws UnknownHostException, IOException {
		int port=5000;
		String address= "127.0.0.1";
		socket= new Socket(address,port);
		
		din= new DataInputStream(socket.getInputStream());
		dout= new DataOutputStream(socket.getOutputStream());
		
		n= din.readLong();
		phin= din.readLong();
		
		System.out.println("Received N: "+n);
		System.out.println("Received phi(n): "+phin);
		
		e=din.readLong();
		System.out.println("Received public key: "+e);
		System.out.println("Generate private key!");
		generatePrivateKey();
		System.out.println("Private key: "+d);
		cipher= din.readLong();
		System.out.println("Cipher text: "+cipher);
		plain=power(cipher,d,n);
		System.out.println("plain text: "+plain);
		
	}
	private static long power(long cipher2, long d2, long n2) {
		long ans=1;
		if(d2==1) {
			return cipher2;
		}
		else {
			while(d2--!=0)
				ans= (ans*cipher2)%n2;
		}
		return ans;
	}
	private static void generatePrivateKey() {
		double num=0.0;
			for(int i=0;i<=100;i++) {
				num=(double)((i*phin)+1)/e;
				System.out.println(num);
				if(num%1==0) {
					d=(long)num;
					break;
				}
			}
	}

}
