package step01_network;
//��Ʈ��ũ �󿡼� �ٸ� PC�� �����ϱ� ���ؼ���, PC�� ����Ű�� �ּҸ� �˾ƾ� �Ѵ�.
//��Ʈ��ũ�� ����� Ư�� PC�� �ּҸ� ��Ÿ���� ü�踦 IP Address(Internet Protocol Address, IP �ּ�)��� �Ѵ�.
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class InetAddressEx1 { //InetAddress Ŭ����: IP��ȣ�� ó�� �� �� ����ϴ� Ŭ����

	public static void main(String[] args) throws UnknownHostException, MalformedURLException {
		InetAddress addr1=InetAddress.getByName("naver.com");
		InetAddress addr2=InetAddress.getLocalHost(); // ���� pc(���� ȣ��Ʈ)�� �̿��� ���� Ȯ��(�������ּ� �޾ƿ�)
		
		URL url=new URL("http://127.0.0.1");
		
		System.out.println("addr1 : "+addr1.getHostAddress());
		System.out.println("addr1 : "+addr1.getHostName());
		
		System.out.println("addr2 : "+addr2.getHostAddress()); //���� ip �ּ�
		System.out.println("addr2 : "+addr2.getHostName()); //���� pc�̸�
//		System.out.println("addr2 : "+addr2.getLoopbackAddress()); 
		
		//��Ʈ ��ȣ Ȯ�� ��� ->port�� �ܺ��� �ٸ� ���� �����ϱ� ���� �÷��׿� ���� ��.
		//��Ʈ ��ȣ�� � ���α׷��� ���� �� ������ ��ǻ�Ϳ��� �˷��ش�. (������ ������Ҹ� ��Ÿ���� ����ǥ.)
		System.out.println("port no : "+url.getPort()); //--> ��µ� -1�� �ǹ� ��Ʈ ��ȣ�� ����.
		//������ �ַ��� ���� URL url=new URL("http://127.0.0.1");���� : ���ڸ� �߰������ �Ѵ�.
		URL url1=new URL("http://127.0.0.1:80");
		System.out.println("port no : "+url1.getPort());
		
		URL url2=new URL("http://naver.com:80");
		System.out.println("port no : "+url2.getPort());
		System.out.println("protocol : "+url2.getProtocol());
		
	}

}
