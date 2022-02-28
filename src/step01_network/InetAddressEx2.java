package step01_network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InetAddressEx2 {

	public static void main(String[] args) throws UnknownHostException {
		Scanner sc= new Scanner(System.in);
		String host=null;
		
		System.out.print("Ȩ������ �ּ� : "); host=sc.nextLine();
		InetAddress[] addr=InetAddress.getAllByName(host);
		
		System.out.println(host+"�� "+addr.length+"���� ip �ּҸ� ������ ����");
		
		for(int i=0; i<addr.length; i++) { //�������� ip�ּҰ� ���� ��� for���� �̿��� �������� ����ؼ� �� �� �ִ�.
			System.out.println((i+1)+"�� ip�ּҴ� "+addr[i].getHostAddress()); 
			System.out.println((i+1)+"�� �̸��� "+addr[i].getHostName()); 
		}
	}

}
