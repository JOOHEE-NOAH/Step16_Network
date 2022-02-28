package step03_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

//tcp: ���� ����
public class TCPServer {
	final static int PORT=7000;
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(PORT);
			System.out.println("���� ����� ...");
			
			while(true) {
				Socket sock=ss.accept(); //����� socket����// ���� ��û ����
				
				//��Ʈ��ũ���� �Ѿ�� �����͸� �о���̱�
				InputStream is=sock.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(is));
				
				//�ַܼ� ���
				PrintStream ps=new PrintStream(System.out);
				
				//���� ���� ���
				ps.print(sock.getInetAddress().getHostName());
				ps.println("���� ���ŵ� �޼���"+br.readLine()+"\n");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
