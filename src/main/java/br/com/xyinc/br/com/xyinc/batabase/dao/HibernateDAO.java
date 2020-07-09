package br.com.xyinc.br.com.xyinc.batabase.dao;

public interface HibernateDAO<T, ID> {
	T update(T instance);

	T save(T entity);
}
