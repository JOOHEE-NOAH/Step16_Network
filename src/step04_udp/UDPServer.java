package step04_udp;
/*PDU:protocol data unit
 * tcp�� pdu :���׸�Ʈ 
 * udp�� pdu : �����ͱ׷�
 * 
 * */

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {
		final int PORT=7500;
		final int Buffer=50;
		String message="";
		
		PrintStream ps=new PrintStream(System.out);	
		
		
		try {
			DatagramSocket ds=new DatagramSocket(PORT);// udp(�����ͱ׷�) ���� ����
			ps.print("���� �����...");
			
			byte[] buf=new byte[Buffer];
			DatagramPacket dp=new DatagramPacket(buf, buf.length);
			
			ds.receive(dp);
			message=new String(dp.getData());
			ps.print("\n���� �޽��� : "+message);
			ps.flush();
			message="";
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //upd(�����ͱ׷�) ���� ����
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ps.close();
		}
		
	}

}
