package org.tatasu.websockets;

import java.io.IOException;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.json.JSONArray;
import org.tatasu.model.LogsDao;
import org.tatasu.model.LogsDaoImpl;

public class AjaxWebScoketHandler extends WebSocketHandler {

	/**
	 * ����� �������� �������
	 */
	private final Set<DataWebSocket>	webSockets	= new CopyOnWriteArraySet<DataWebSocket>();

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest arg0, String arg1) {
		return new DataWebSocket();
	}

	private class DataWebSocket implements WebSocket.OnTextMessage {

		/** ��������� ���������� */
		private Connection		connection;
		
		private LogsDao dao = new LogsDaoImpl();

		/** ������ �������� ������� ����������� */
		private final Pattern	authCmdPattern	= Pattern.compile("^\\/auth ([\\S]+).*");

		@Override
		public void onClose(int arg0, String arg1) {
			System.out.println("socket closed");
			// ������� ���� �� ����������� ������ ChatWebSocketHandler::webSockets
            webSockets.remove(this);
		}

		@Override
		public void onOpen(Connection arg0) {
			System.out.println("socket opened");
			// ��������� ���������� � �������� ChatWebSocket::connection
			this.connection = arg0;

			// ��������� ���� � ���������� �����
			// ChatWebSocketHandler::webSockets
			webSockets.add(this);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					try {
						if(connection.isOpen())
							connection.sendMessage(new JSONArray(dao.getList()).toString());
						else 
							this.cancel();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, 5000, 5000);			
		}

		@Override
		public void onMessage(String arg0) {
			System.out.println(arg0);
		}		
	}
	
}
