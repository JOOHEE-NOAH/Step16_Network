package step04_udp;
/*PDU:protocol data unit
 * tcp의 pdu :세그먼트 
 * udp의 pdu : 데이터그램
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
			DatagramSocket ds=new DatagramSocket(PORT);// udp(데이터그램) 소켓 생성
			ps.print("접속 대기중...");
			
			byte[] buf=new byte[Buffer];
			DatagramPacket dp=new DatagramPacket(buf, buf.length);
			
			ds.receive(dp);
			message=new String(dp.getData());
			ps.print("\n수신 메시지 : "+message);
			ps.flush();
			message="";
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //upd(데이터그램) 소켓 생성
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ps.close();
		}
		
	}

}
