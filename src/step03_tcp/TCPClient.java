package step03_tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

public class TCPClient {
	final static int PORT=7000;
	public static void main(String[] args) {
		String server_ip;
		//�ַܼ� �Է�//
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		//�ַܼ� ���
		PrintStream ps=new PrintStream(System.out);
		
		 try {ps.print("������ ip�ּҸ� �Է��ϼ��� : ");	server_ip=br.readLine();
			
		 	while(true) {
		 		Socket sock=new Socket(server_ip,PORT);
		 		
		 		//��Ʈ��ũ�� ���� ������ ����//
		 		OutputStream os=sock.getOutputStream();
		 		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
		 		
		 		ps.print("\n������ �޼����� �Է��ϼ��� : ");
		 		String str=br.readLine();
		 		
		 		//bw.write(str,0,str.length());
		 		bw.write(str);
		 		
		 		bw.close();
		 		sock.close();
		 		ps.print(server_ip+"�� �޼��� ����");
		 	}
		 
		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}