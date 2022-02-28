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
	
	//1-0. ���� �ʱ�ȭ
	ServerSocket ss=null;
	//1-0 end
	//4-2 start inwon ���ͺ��� ���� �� �ʱ�ȭ
	Vector<ChatHandle> inwon=null; // ���� �ο��� ī��Ʈ ����
	//4-2 end
	
	
	
	public GUIChatServer()
	{
		super("Chatting Server");
		
		txt_list = new TextArea();
		btn_exit = new JButton("��������");
		
		add(txt_list, "Center");
		add(btn_exit,"South");
		setSize(250,250);
		setVisible(true);
		//�̺�Ʈó��-----------------------
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		btn_exit.addActionListener(this);
		//----------------------------------
		
		//4-2 start
		inwon=new Vector<ChatHandle>(); // �ݵ�� serverStart() ���� ���� ���� �Ǿ�� ��.
		//4-2 end
		
		serverStart();
	}
	
	
	public void serverStart()
	{	
		//1-1 ��Ʈ��ȣ ����
		final int PORT=7500;
		//1-1 end
		try{
			//���� ����, ���Ӵ�� /1-2 start
			ss=new ServerSocket(PORT);
			while(true)
			{
				Socket sock=ss.accept();
				String str=sock.getInetAddress().getHostAddress();
				txt_list.append(str);
				
				//4-1 start
				ChatHandle ch=new ChatHandle(this,sock); // ����� ���� Ŭ����
				//4-1 end
				//4-2 start
				inwon.add(ch);
				//4-2 end
				//4-3,4-4 start
				ch.start();  // ������ ����
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
		txt_list.append(nickname); // �г����� ȭ�鿡 ���
	}//4-5 end
	
	
	
}//end of GuIChatServer

class ChatHandle extends Thread{ // ����ó���� Ŭ����
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
		
	}// end of ê�ڵ� ������
	
	//4-5 start
	@Override
	public void run() { // nickname�� �о txt_list�� ���
		
		String nickname=null;
		
		try {
			nickname=br.readLine();
			server.setMsg("["+nickname+"]���� �����ϼ̽��ϴ�\n");
			
			//4-6 start
			broadcast("["+nickname+"]���� �����ϼ̽��ϴ�\n");
			//4-6 end
			
			//6-1 start
			while(true) {
				try {
				String text=br.readLine(); //Ŭ���̾�Ʈ�� ���� ���۹��� ����
				server.setMsg(nickname+" : "+text+"\n"); //���� ������ �����ʿ� ���
				broadcast(nickname+" : "+text+"\n"); // Ŭ���̾�Ʈ �ʿ��� ���
			}catch (IOException e) {
				return;
				//6-2 start
				broadcast(nickname+" : "+text+"\n");// Ŭ���̾�Ʈ �ʿ��� ���
				//6-3 start ����ó��
			}finally {
				synchronized (server.inwon) {
					server.inwon.remove(this);
					server.setMsg("["+nickname+"]���� �����ϼ̽��ϴ�\n");
					broadcast("["+nickname+"]���� �����ϼ̽��ϴ�\n");
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
		synchronized (server.inwon) { //�����ϸ� �������� ����ȭ ó��
			int s=server.inwon.size(); // ������ ���� �ľ�(������ ������ ���� �ľ�)
			for(int i=0; i<s; i++) {
				ChatHandle ch=server.inwon.get(i);
				ch.pw.println(string);
				ch.pw.flush();
			}
			
			
		}
	}
	//4-5 end
}









