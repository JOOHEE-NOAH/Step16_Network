package chat;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.*;

public class GUIChatServer extends JFrame implements ActionListener{
	TextArea txt_list;
	JButton btn_exit;
	
	//1-0. 소켓 초기화
	ServerSocket ss=null;
	//1-0 end
	//4-2 start inwon 벡터변수 선언 및 초기화
	Vector<ChatHandle> inwon=null; // 접속 인원의 카운트 변수
	//4-2 end
	
	
	
	public GUIChatServer()
	{
		super("Chatting Server");
		
		txt_list = new TextArea();
		btn_exit = new JButton("서버종료");
		
		add(txt_list, "Center");
		add(btn_exit,"South");
		setSize(250,250);
		setVisible(true);
		//이벤트처리-----------------------
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		btn_exit.addActionListener(this);
		//----------------------------------
		
		//4-2 start
		inwon=new Vector<ChatHandle>(); // 반드시 serverStart() 보다 먼저 동작 되어야 함.
		//4-2 end
		
		serverStart();
	}
	
	
	public void serverStart()
	{	
		//1-1 포트번호 설정
		final int PORT=7500;
		//1-1 end
		try{
			//소켓 생성, 접속대기 /1-2 start
			ss=new ServerSocket(PORT);
			while(true)
			{
				Socket sock=ss.accept();
				String str=sock.getInetAddress().getHostAddress();
				txt_list.append(str);
				
				//4-1 start
				ChatHandle ch=new ChatHandle(this,sock); // 사용자 정의 클래스
				//4-1 end
				//4-2 start
				inwon.add(ch);
				//4-2 end
				//4-3,4-4 start
				ch.start();  // 쓰레드 시작
				//4-3,4-4 end
				
				
			}
			//1-2 end
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_exit)
			System.exit(0);
	}
	
	public static void main(String[] args) {
		new GUIChatServer();
	}
	//4-5 start
	public void setMsg(String nickname) {
		txt_list.append(nickname); // 닉네임을 화면에 출력
	}//4-5 end
	
	
	
}//end of GuIChatServer

class ChatHandle extends Thread{ // 병행처리용 클래스
	BufferedReader br=null;
	PrintWriter pw=null;
	GUIChatServer server=null;
	Socket sock=null;
	
	public ChatHandle(GUIChatServer server, Socket sock) {
		this.server=server;
		this.sock=sock;
		
		try {
			InputStream is=sock.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			
			OutputStream os=sock.getOutputStream();
			pw=new PrintWriter(new OutputStreamWriter(os));
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}// end of 챗핸들 생성자
	
	//4-5 start
	@Override
	public void run() { // nickname을 읽어서 txt_list에 출력
		
		String nickname=null;
		
		try {
			nickname=br.readLine();
			server.setMsg("["+nickname+"]님이 입장하셨습니다\n");
			
			//4-6 start
			broadcast("["+nickname+"]님이 입장하셨습니다\n");
			//4-6 end
			
			//6-1 start
			while(true) {
				try {
				String text=br.readLine(); //클라이언트로 부터 전송받은 내용
				server.setMsg(nickname+" : "+text+"\n"); //받은 내용을 서버쪽에 출력
				broadcast(nickname+" : "+text+"\n"); // 클라이언트 쪽에도 출력
			}catch (IOException e) {
				return;
				//6-2 start
				broadcast(nickname+" : "+text+"\n");// 클라이언트 쪽에도 출력
				//6-3 start 퇴장처리
			}finally {
				synchronized (server.inwon) {
					server.inwon.remove(this);
					server.setMsg("["+nickname+"]님이 퇴장하셨습니다\n");
					broadcast("["+nickname+"]님이 퇴장하셨습니다\n");
				}
			}
			
			}//6-1 end
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//4-6 start
	private void broadcast(String string) {
		synchronized (server.inwon) { //과부하를 막기위한 동기화 처리
			int s=server.inwon.size(); // 접속자 개수 파악(벡터의 데이터 개수 파악)
			for(int i=0; i<s; i++) {
				ChatHandle ch=server.inwon.get(i);
				ch.pw.println(string);
				ch.pw.flush();
			}
			
			
		}
	}
	//4-5 end
}









