package step02_protocol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//������ Ŭ���̾�Ʈ ���ʿ��� ������ �������Ѵ�
public class ProtocolClient {
	Socket sock=null;
	BufferedWriter bw=null; //������ ���
	BufferedReader br=null; //������ ���
	
	BufferedReader keyboard=null; //Ŭ���̾�Ʈ���� Ű���� �Է�
	
	public ProtocolClient() {
		try {
			sock=new Socket("127.0.0.1",7000);//�����ϰ����ϴ� ip �ּ� ����
		} catch (UnknownHostException e) {
			System.out.println("������ ã�� �� �����ϴ�...");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("������ ���� �� �� �����ϴ�...");
			System.exit(0);
		}
// ------------------------------------------------		
		//sock ���κ��� ��Ʈ�� ����
		try {
		bw=new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		br=new BufferedReader(new InputStreamReader(sock.getInputStream()));
		
		//Ű����� ����� ��Ʈ�� ó��
		keyboard=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
		System.out.println("ex)100:���̵�(����) 200:���̵�(����) 300:���̵�:�޽���(��ȭ) 400:���̵�1:���̵�2:�޽���(�Ӹ�)");
		System.out.println("�޽����� �Է��ϼ���");
		
		String msg=keyboard.readLine(); //1.100:�Ƿη�  �Է�
		bw.write(msg+"\n");
		bw.flush();
		}
		
		}catch (IOException e) {
			System.out.println("������ ������ ������...");
			System.exit(0);
	}
	}

	public static void main(String[] args) {
		new ProtocolClient();
	}

}
