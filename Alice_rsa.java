//package rsa;

import java.util.*;
import java.net.*;
import java.io.*;

public class Alice {
	private static ServerSocket server;
	private static Socket socket;
	private static DataInputStream din;
	private static DataOutputStream dout;
	private static long a;
	private static long b;
	private static long cipher;
	private static long plain;
	private static long n;
	private static long phin;
	private static long e;
	private static long d;
	private static ArrayList<Integer> primes;
	public static void main(String[] args) throws UnknownHostException, IOException {
		int port=5000;
		server= new ServerSocket(port);
		socket=server.accept();
		
		din= new DataInputStream(socket.getInputStream());
		dout= new DataOutputStream(socket.getOutputStream());
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter prime number A: ");
		a=sc.nextLong();
		System.out.println("Enter prime number B: ");
		b=sc.nextLong();
		
		if(ifprime(a)&&ifprime(b)) {
			n= a*b;
			phin= (a-1)*(b-1);
			
			System.out.println("Value of N: "+n);
			dout.writeLong(n);
			System.out.println("Value of phi(n): "+phin);
			dout.writeLong(phin);
			
			System.out.println("Generating public key: ");
			generatepublickey();
			e =sc.nextLong();
			System.out.println("Public key generated");
			dout.writeLong(e);
			
			System.out.println("Enter plain text: ");
			plain= sc.nextLong();
			cipher=power(plain,e,n);
			System.out.println("Cipher text generated: "+cipher);
			dout.writeLong(cipher);
		}
		else {
			System.out.println("Enter correct prime number!");
		}
	}
	private static long power(long plain2, long e2, long n2) {
		long ans=1;
		if(e2==0) {
			return plain2;
		}
		else {
			while(e2--!=0) {
				ans=(ans*plain2)%n2;
			}
		}
		return ans;
	}
	private static void generatepublickey() {
		primes= new ArrayList<Integer>();
		int count=0;
		for(int i=1;i<phin;i++) {
			if(ifprime(i)) {
				primes.add(i);
				count++;
			}
		}
		System.out.println("The total prime numbers generated: "+count);
		System.out.println("Possible values of e are: ");
		for(Integer prime:primes) {
			if(gcd(phin,prime)==1) {
				System.out.println(prime+ "");
			}
		}
		System.out.println("Select one value of e: ");
		
	}
	private static long gcd(long phin2, long prime) {
		if(prime==0) {
			return phin2;
		}
		else {
			return(gcd(prime,phin2%prime));
		}
	}
	private static boolean ifprime(long a2) {
		for(int i=2;i<a2/2;i++) {
			if(a2%i==0) {
				return false;
			}
		}
		return true;
	}

}
