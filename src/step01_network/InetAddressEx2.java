package step01_network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InetAddressEx2 {

	public static void main(String[] args) throws UnknownHostException {
		Scanner sc= new Scanner(System.in);
		String host=null;
		
		System.out.print("홈페이지 주소 : "); host=sc.nextLine();
		InetAddress[] addr=InetAddress.getAllByName(host);
		
		System.out.println(host+"는 "+addr.length+"개인 ip 주소를 가지고 있음");
		
		for(int i=0; i<addr.length; i++) { //여러개의 ip주소가 있을 경우 for문을 이용해 여러개를 출력해서 볼 수 있다.
			System.out.println((i+1)+"번 ip주소는 "+addr[i].getHostAddress()); 
			System.out.println((i+1)+"번 이름은 "+addr[i].getHostName()); 
		}
	}

}
