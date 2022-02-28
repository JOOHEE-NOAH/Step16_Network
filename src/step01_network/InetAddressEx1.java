package step01_network;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class InetAddressEx1 {

	public static void main(String[] args) throws UnknownHostException, MalformedURLException {
		InetAddress addr1=InetAddress.getByName("naver.com");
		InetAddress addr2=InetAddress.getLocalHost(); // 나의 pc에 대한 정보 확인
		
		URL url=new URL("http://127.0.0.1");
		
		System.out.println("addr1 : "+addr1.getHostAddress());
		System.out.println("addr1 : "+addr1.getHostName());
		
		System.out.println("addr2 : "+addr2.getHostAddress()); //나의 ip 주소
		System.out.println("addr2 : "+addr2.getHostName()); //나의 pc이름
//		System.out.println("addr2 : "+addr2.getLoopbackAddress()); 
		
		//포트 번호 확인 방식
		System.out.println("port no : "+url.getPort()); //--> 출력된 -1의 의미 포트 번호가 없다.
		//지정해 주려면 위의 URL url=new URL("http://127.0.0.1");에서 : 숫자를 추가해줘야 한다.
		URL url1=new URL("http://127.0.0.1:80");
		System.out.println("port no : "+url1.getPort());
		
		URL url2=new URL("http://naver.com:80");
		System.out.println("port no : "+url2.getPort());
		System.out.println("protocol : "+url2.getProtocol());
		
	}

}
