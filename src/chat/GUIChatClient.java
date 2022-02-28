package chat;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIChatClient extends JFrame implements ActionListener, Runnable{
	JPanel cardPane, connectPane, chatPane;
	JLabel  msg;
	JButton btn_connect, btn_send, btn_exit;
	JTextField txt_server_ip, txt_name, txt_input;
	JTextArea txt_list;
	CardLayout card;
	
	//2-1 start 포트 번호 지정 및 변수 선언
	final int PORT=7500;
	String ip_txt; //ip_주소 저장 변수
	PrintWriter pw=null; //송신 스트림
	BufferedReader br=null; // 수신 스트림
	Socket sock=null;
	//2-1 end
	//3-1 start os변수 선언 -- 닉네임 전송 동작
	OutputStream os=null;
	//3-1 end
	
	public GUIChatClient()
	{
		super("Chatting Client(ver 1.0)");
		
		ConnectPane();
		ChatPane();
		
		//card-----------------------------
		cardPane = new JPanel();
		card = new CardLayout();
		cardPane.setLayout(card);
		cardPane.add(connectPane,"접속창");
		cardPane.add(chatPane,"채팅창");
		card.show(cardPane, "접속창");
		//----------------------------------
		add(cardPane);
		setSize(400,300);
		setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//이벤트처리-----------------------
		btn_connect.addActionListener(this);
		btn_exit.addActionListener(this);
		//----------------------------------
		//5-2 start
		btn_send.addActionListener(this);
		txt_input.addActionListener(this);
		//5-2 end
	}
	public void ConnectPane()
	{
		connectPane = new JPanel();
		JPanel pn=new JPanel();
		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		
		msg = new JLabel("IP와 대화명을 입력하시오");
		msg.setFont(new Font("굴림체", Font.BOLD, 15));
		msg.setForeground(Color.magenta);
		
		JLabel lb1 = new JLabel("서버 I P : ");
		txt_server_ip = new JTextField("localhost", 15);
		pn1.add(lb1);    pn1.add(txt_server_ip);
		
		JLabel lb2 = new JLabel("대 화 명 : ");
		txt_name = new JTextField("뽀로로",15);		
		pn2.add(lb2);    pn2.add(txt_name);
		
		pn.add(pn1);     pn.add(pn2);    pn.add(msg);
		
		btn_connect = new JButton("Connection");
		
		connectPane.setBorder(BorderFactory.createTitledBorder("다중채팅화면"));
		connectPane.setLayout(new BorderLayout());
		connectPane.add(pn,"Center");
		connectPane.add(btn_connect,"South");
	}
	public void ChatPane()
	{
		chatPane = new JPanel();
		JPanel  pn = new JPanel();
		txt_list = new JTextArea();
		txt_input = new JTextField("",20);
		btn_send = new JButton("전송");
		btn_exit = new JButton("종료");

		pn.setBorder(BorderFactory.createTitledBorder("☆대화하기☆"));
		chatPane.setBorder(BorderFactory.createTitledBorder("♣채팅내용♣"));
		pn.add(txt_input);   pn.add(btn_send);  pn.add(btn_exit);
		
		chatPane.setLayout(new BorderLayout());
		chatPane.add(txt_list, "Center");
		chatPane.add(pn, "South");
	}

	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == btn_connect)
		{
			card.show(cardPane, "채팅창");
			
			//서버 주소 추출
			ip_txt=txt_server_ip.getText(); //내부에 있는 문자열만 뽑아내기
			
			//2-2 start 쓰레드 생성
			Thread th=new Thread(this); //implements Runnable 추가..
			//2-2 end
			
			//2-3 start 접속시도
			th.start();
			//2-3 end
			
//===============================
//			5-2 start
			
			if(ob==btn_send || ob==txt_input) { //전송버튼, 엔터 처리
				String text=txt_input.getText();
				if(text.trim().equals("")) {//enter 키를 반복하는 도배 방지
				return;
				}
				pw.println(text);
				pw.flush();
				txt_input.setText("");
				txt_input.requestFocus();// 커서 위치를 txt_input으로 이동
			}
				
//			5-2 end	
			
			
		}
		if(ob ==btn_exit)
			System.exit(0);
	}
	
	@Override
	public void run() {
		
	//2-3 start
		try {
			sock=new Socket(ip_txt, PORT); //접속 시도
			
//			3-1 start
			String nickname=txt_name.getText();
			os= sock.getOutputStream();
			pw=new PrintWriter(new OutputStreamWriter(os));
			pw.println(nickname); //pw.print() 사용 금지.
			pw.flush();
			//3-1 end
			
			//5-1 start
			InputStream is=sock.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			
			String str;
			while(true) {
				try {
					str=br.readLine();
					txt_list.append(str+"\n"); 
				}catch (IOException e) {
					txt_list.append("서버가 종료됨");
					return;
				}
			}
			//5-1 end
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2-3 end
	}
	
	public static void main(String[] args) {
		new GUIChatClient();
	}
	
}








