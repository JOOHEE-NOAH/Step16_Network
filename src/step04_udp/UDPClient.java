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
		
		
		try {ps.print("접속할 server ip 주소 입력 : ");		server_ip=br.readLine();
		DatagramSocket ds=new DatagramSocket();	
		InetAddress ip=InetAddress.getByName(server_ip);
		
			ps.print("전송할 메세지 입력 : "); msg=br.readLine();
			
			byte[] buf=msg.getBytes();		   //데이터,테이터 길이, 주소 , 포트번호
			DatagramPacket dp=new DatagramPacket(buf, buf.length, ip, PORT);
			ds.send(dp); //데이터 전송
			ps.print(server_ip+"에 메세지 전송 성공");
		
		
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
