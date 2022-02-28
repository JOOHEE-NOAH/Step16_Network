package step02_protocol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProtocolServer {
	ServerSocket ss=null;
	Socket sock=null;
	BufferedReader br=null;
	BufferedWriter bw=null;
	
	public ProtocolServer() throws IOException {
		ss=new ServerSocket(7000);
		System.out.println("접속 대기중...");
		sock=ss.accept(); //응답
		
		br=new BufferedReader(new InputStreamReader(sock.getInputStream()));//소켓에서 들어오는 입력을 처리
		bw=new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		
		while(true) {
			String buf=br.readLine(); //2. 100: 뽀로로 들어옴
			String [] words=buf.split(":"); //3. 구분해주기
			
			if(words[0].equals(Protocol.ENTER)) { //4.입장
				enter(words);
			}else if(words[0].equals(Protocol.EXIT)) {
				exit(words);
			}else if(words[0].equals(Protocol.SEND_MESSAGE)) {
				sendMessage(words);
			}else if(words[0].equals(Protocol.SECRET_MESSAGE)) {
				secretMessage(words);
			}else {
				System.out.println("잘못된 요청입니다"); //콘솔에서 출력
//				bw.write("잘못된 요청입니다."); //클라이언트에서 읽어와서 출력
//				bw.flush();
				System.exit(0);
			}
		}
		
	}
	private void secretMessage(String[] words) {
		System.out.println("["+words[1]+"]님이 "+words[2]+"님에게 귓속말 보냄");
		System.out.println("["+words[1]+"] : "+words[3]);
	}
	private void sendMessage(String[] words) {
		System.out.println("["+words[1]+"] : "+words[2]+"\n");
	}
	
	private void exit(String[] words) {
		System.out.println("["+words[1]+"]님이 퇴장하셨습니다");
	}
		
	public void enter(String [] words) {
		System.out.println("["+words[1]+"]님이 입장하셨습니다.\n");
	}
	
	
	public static void main(String[] args) throws IOException {
		new ProtocolServer();
	}

}
