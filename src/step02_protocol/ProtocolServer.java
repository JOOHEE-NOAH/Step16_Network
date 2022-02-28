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
		System.out.println("���� �����...");
		sock=ss.accept(); //����
		
		br=new BufferedReader(new InputStreamReader(sock.getInputStream()));//���Ͽ��� ������ �Է��� ó��
		bw=new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		
		while(true) {
			String buf=br.readLine(); //2. 100: �Ƿη� ����
			String [] words=buf.split(":"); //3. �������ֱ�
			
			if(words[0].equals(Protocol.ENTER)) { //4.����
				enter(words);
			}else if(words[0].equals(Protocol.EXIT)) {
				exit(words);
			}else if(words[0].equals(Protocol.SEND_MESSAGE)) {
				sendMessage(words);
			}else if(words[0].equals(Protocol.SECRET_MESSAGE)) {
				secretMessage(words);
			}else {
				System.out.println("�߸��� ��û�Դϴ�"); //�ֿܼ��� ���
//				bw.write("�߸��� ��û�Դϴ�."); //Ŭ���̾�Ʈ���� �о�ͼ� ���
//				bw.flush();
				System.exit(0);
			}
		}
		
	}
	private void secretMessage(String[] words) {
		System.out.println("["+words[1]+"]���� "+words[2]+"�Կ��� �ӼӸ� ����");
		System.out.println("["+words[1]+"] : "+words[3]);
	}
	private void sendMessage(String[] words) {
		System.out.println("["+words[1]+"] : "+words[2]+"\n");
	}
	
	private void exit(String[] words) {
		System.out.println("["+words[1]+"]���� �����ϼ̽��ϴ�");
	}
		
	public void enter(String [] words) {
		System.out.println("["+words[1]+"]���� �����ϼ̽��ϴ�.\n");
	}
	
	
	public static void main(String[] args) throws IOException {
		new ProtocolServer();
	}

}
