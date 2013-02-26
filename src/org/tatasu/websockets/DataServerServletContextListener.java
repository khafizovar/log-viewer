package org.tatasu.websockets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;


public class DataServerServletContextListener implements ServletContextListener {

	/**
     * ��������� ������� Jetty
     */
    private Server server = null;
    
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		 // ���� ������ jetty �����-������ ����������
        if (server != null) {
            try {
                // ������������� Jetty
                server.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
            // �������� ������� Jetty �� 8081 �����
            this.server = new Server(8181);
            
            // ������������ ChatWebSocketHandler � ������� Jetty
            AjaxWebScoketHandler chatWebSocketHandler = new AjaxWebScoketHandler();
            // ��� ������� �������� ��� WebSocketHandlerContainer
            chatWebSocketHandler.setHandler(new DefaultHandler());
            
            // ��������� ��� ������� ��������� jetty
            server.setHandler(chatWebSocketHandler);
            
            // ��������� Jetty
            server.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}

}
