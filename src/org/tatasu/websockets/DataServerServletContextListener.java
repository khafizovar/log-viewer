package org.tatasu.websockets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;


public class DataServerServletContextListener implements ServletContextListener {

	/**
     * Хранилище сервера Jetty
     */
    private Server server = null;
    
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		 // Если сервер jetty когда-нибудь запустился
        if (server != null) {
            try {
                // останавливаем Jetty
                server.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
            // Создание сервера Jetty на 8081 порту
            this.server = new Server(8181);
            
            // Регистрируем ChatWebSocketHandler в сервере Jetty
            AjaxWebScoketHandler chatWebSocketHandler = new AjaxWebScoketHandler();
            // Это вариант хэндлера для WebSocketHandlerContainer
            chatWebSocketHandler.setHandler(new DefaultHandler());
            
            // Вставляем наш хэндлер слушаться jetty
            server.setHandler(chatWebSocketHandler);
            
            // Запускаем Jetty
            server.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}

}
