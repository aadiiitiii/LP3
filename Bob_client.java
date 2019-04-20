//package diffie;

import java.io.*;
import java.util.*;
import java.net.*;

public class Bob_client {
	private static Socket socket;
	private static DataInputStream din;
	private static DataOutputStream dout;
	private static long p;
	private static long g;
	private static long b;
	private static long xa;
	private static long xb;
	private static long key;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		int port=5000;
		String address="127.0.0.1";
		socket= new Socket(address,port);
		
		din= new DataInputStream(socket.getInputStream());
		dout= new DataOutputStream(socket.getOutputStream());
		Scanner sc= new Scanner(System.in);
		
		p= din.readLong();
		g= din.readLong();
		
		System.out.println("Value of p: "+p);
		System.out.println("Value of g: "+g);
		
		System.out.println("Enter private key b: ");
		b=sc.nextLong();
		
		System.out.println("Calculating public key");
		xb= power(g,b,p);
		System.out.println("Public Key is: "+xb);
		
		xa= din.readLong();
		dout.writeLong(xb);
		
		System.out.println("Alice's key is: "+xa);
		key=power(xa,b,p);
		System.out.println("Shared Secret key : "+key);
	}

	private static long power(long g2, long b2, long p2) {
		long ans=1;
		if(b2==1) 
			return g2;
		else {
			while(b2--!=0) {
				ans= (ans*g2)%p2;
			}
		}
		return ans;
	}
}