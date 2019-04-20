 //package diffie;
 
 import java.util.*;
 import java.io.*;
 import java.net.*;
 
 public class Alice_server {
	 private static ServerSocket server;
	 private static Socket socket;
	 private static DataInputStream din;
	 private static DataOutputStream dout;
	 private static long p;
	 private static long g;
	 private static long a;
	 private static long xa;
	 private static long xb;
	 private static long key1;
	 private static long key2;
	 
	 public static void main(String[] args) throws IOException {
		 int port=5000;
		 server= new ServerSocket(port);
		 socket= server.accept();
		 
		 din= new DataInputStream(socket.getInputStream());
		 dout= new DataOutputStream(socket.getOutputStream());
		 Scanner sc= new Scanner(System.in);
		 
		 System.out.println("Enter value of p: ");
		 p=sc.nextLong();
		 System.out.println("Enter value of g: ");
		 g=sc.nextLong();
		 dout.writeLong(p);
		 dout.writeLong(g);
		 
		 System.out.println("Enter the private key: ");
		 a=sc.nextLong();
		 
		 System.out.println("\nFinding out the public key");
		 xa= power(g,a,p);
		 System.out.println("Public key is: "+xa);
		 dout.writeLong(xa);
		 
		 xb=din.readLong();
		 System.out.println("The public key of Bob is: "+xb);
		 
		 key1= power(xb,a,p);
		 System.out.println("Shared secret key is: "+key1);
		 
	 }

	private static long power(long g2, long a2, long p2) {
		long ans=1;
		if(a2==1) 
			return g2;
		else {
			while(a2--!=0) {
				ans= (ans*g2)%p2;
			}
			return ans;
		}
	}
 }