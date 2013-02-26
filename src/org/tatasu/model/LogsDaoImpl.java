package org.tatasu.model;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class LogsDaoImpl implements LogsDao {
	
	Reader reader = null;
	SqlSession session = null;
	SqlSessionFactory sqlSessionFactory = null; 
	
	public LogsDaoImpl() {
	    try {
	      // Считываем конфигурационный файл
	      reader = org.apache.ibatis.io.Resources.getResourceAsReader("mybatis.config.xml");
	      // Инициализируем SqlSessionFactory
	      sqlSessionFactory =  new SqlSessionFactoryBuilder().build(reader);	     
	    }
	    catch(Exception e){
	      e.printStackTrace();
	    }
	}

	@Override
	public List<Logs> getList() {
		 session = sqlSessionFactory.openSession();
		 List<Logs> list = session.selectList("masterMapper.selectAll");
		 session.close();
		return list;
	}

	@Override
	public void saveOrUpdate(List<Logs> products) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(Logs product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(List<Logs> products) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Logs product) {
		// TODO Auto-generated method stub

	}

	@Override
	public Logs getOnceById(Integer id) {
		 // Инициализируем сессию
	      session = sqlSessionFactory.openSession();
	      Logs master = (Logs) session.selectOne("masterMapper.selectLogs", id);
	      session.close();
		return master;
	}

	@Override
	public List<Logs> getLastRowsList(int lastRowCount) {
		return session.selectList("masterMapper.selectByLimit", lastRowCount);
	}
}
