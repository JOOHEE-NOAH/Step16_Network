package step04_udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) {
		final int PORT=7500;
		String server_ip,msg;
		PrintStream ps=new PrintStream(System.out);
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		
		try {ps.print("������ server ip �ּ� �Է� : ");		server_ip=br.readLine();
		DatagramSocket ds=new DatagramSocket();	
		InetAddress ip=InetAddress.getByName(server_ip);
		
			ps.print("������ �޼��� �Է� : "); msg=br.readLine();
			
			byte[] buf=msg.getBytes();		   //������,������ ����, �ּ� , ��Ʈ��ȣ
			DatagramPacket dp=new DatagramPacket(buf, buf.length, ip, PORT);
			ds.send(dp); //������ ����
			ps.print(server_ip+"�� �޼��� ���� ����");
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				br.close();
				ps.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
