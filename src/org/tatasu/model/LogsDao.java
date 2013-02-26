package org.tatasu.model;

import java.util.List;

public interface LogsDao {

	public List<Logs> getList();
	
	public List<Logs> getLastRowsList(int lastRowCount);
	
	public Logs getOnceById(Integer id);

	public void saveOrUpdate(List<Logs> products);

	public void saveOrUpdate(Logs product);

	public void delete(List<Logs> products);

	public void delete(Logs product);
}
