package step02_protocol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//서버와 클라이언트 양쪽에서 소켓을 만들어야한다
public class ProtocolClient {
	Socket sock=null;
	BufferedWriter bw=null; //서버와 통신
	BufferedReader br=null; //서버와 통신
	
	BufferedReader keyboard=null; //클라이언트측의 키보드 입력
	
	public ProtocolClient() {
		try {
			sock=new Socket("127.0.0.1",7000);//접근하고자하는 ip 주소 지정
		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다...");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("서버와 연결 할 수 없습니다...");
			System.exit(0);
		}
// ------------------------------------------------		
		//sock 으로부터 스트림 생성
		try {
		bw=new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		br=new BufferedReader(new InputStreamReader(sock.getInputStream()));
		
		//키보드와 연결된 스트림 처리
		keyboard=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
		System.out.println("ex)100:아이디(입장) 200:아이디(퇴장) 300:아이디:메시지(대화) 400:아이디1:아이디2:메시지(귓말)");
		System.out.println("메시지를 입력하세요");
		
		String msg=keyboard.readLine(); //1.100:뽀로로  입력
		bw.write(msg+"\n");
		bw.flush();
		}
		
		}catch (IOException e) {
			System.out.println("서버와 연결이 끊어짐...");
			System.exit(0);
	}
	}

	public static void main(String[] args) {
		new ProtocolClient();
	}

}
