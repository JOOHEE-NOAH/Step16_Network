package step01_network;
//네트워크 상에서 다른 PC에 접속하기 위해서는, PC를 가리키는 주소를 알아야 한다.
//네트워크에 연결된 특정 PC의 주소를 나타내는 체계를 IP Address(Internet Protocol Address, IP 주소)라고 한다.
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class InetAddressEx1 { //InetAddress 클래스: IP번호를 처리 할 때 사용하는 클래스

	public static void main(String[] args) throws UnknownHostException, MalformedURLException {
		InetAddress addr1=InetAddress.getByName("naver.com");
		InetAddress addr2=InetAddress.getLocalHost(); // 나의 pc(로컬 호스트)를 이용해 정보 확인(아이피주소 받아옴)
		
		URL url=new URL("http://127.0.0.1");
		
		System.out.println("addr1 : "+addr1.getHostAddress());
		System.out.println("addr1 : "+addr1.getHostName());
		
		System.out.println("addr2 : "+addr2.getHostAddress()); //나의 ip 주소
		System.out.println("addr2 : "+addr2.getHostName()); //나의 pc이름
//		System.out.println("addr2 : "+addr2.getLoopbackAddress()); 
		
		//포트 번호 확인 방식 ->port란 외부의 다른 장비와 접속하기 위한 플러그와 같은 것.
		//포트 번호는 어떤 프로그램에 접속 할 것인지 컴퓨터에게 알려준다. (논리적인 접속장소를 나타내는 이정표.)
		System.out.println("port no : "+url.getPort()); //--> 출력된 -1의 의미 포트 번호가 없다.
		//지정해 주려면 위의 URL url=new URL("http://127.0.0.1");에서 : 숫자를 추가해줘야 한다.
		URL url1=new URL("http://127.0.0.1:80");
		System.out.println("port no : "+url1.getPort());
		
		URL url2=new URL("http://naver.com:80");
		System.out.println("port no : "+url2.getPort());
		System.out.println("protocol : "+url2.getProtocol());
		
	}

}
