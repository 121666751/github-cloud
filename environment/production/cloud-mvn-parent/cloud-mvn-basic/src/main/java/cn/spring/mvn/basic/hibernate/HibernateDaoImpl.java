package cn.spring.mvn.basic.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.spring.mvn.basic.util.BasicUtilNullValue;
import cn.spring.mvn.basic.util.BasicUtilResultTransFormer;


@Repository("HibernateDao")
@SuppressWarnings({"unchecked", "rawtypes", "null"})
public class HibernateDaoImpl<T> implements HibernateDao<T> {
	/**@serialField 
	 * 获取数据库连接
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @author LiuTao @date 2018年5月24日 下午5:46:28 
	 * @Title: getDialect 
	 * @Description: 获取数据库连接的方言(Dialect),感觉就是数据库的类型
	 * @return
	 */
	public Dialect getDialect() {
		if ((this.sessionFactory instanceof SessionFactoryImpl)) {
			SessionFactoryImpl localSessionFactoryImpl = (SessionFactoryImpl) this.sessionFactory;
			return localSessionFactoryImpl.getDialect();
		}
		return Dialect.getDialect();
	}
	/**
	 * @author LiuTao @date 2018年5月24日 下午5:46:22 
	 * @Title: getCurrentSession 
	 * @Description: 
	 * 			获取数据库连接 (Session),应该就是spring-hibernate.xml中设置的sessionFactory
	 * @return
	 */
	public Session getSession() {
		return this.sessionFactory.openSession();
//		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public T save(T entity) throws Exception{
		if (entity != null) {
			return (T) getSession().save(entity);
		}
		return null;
	}
	@Override
	public void saveOrUpdate(T entity) {
		if (entity != null){
			getSession().saveOrUpdate(entity);
		}
	}
	@Override
	public T saveEntity(T entity) throws Exception {
		return (T) getSession().save(entity);
	}
	@Override
	public List<T> saveEntities(List<T> entities) throws Exception {
		List<T> retList = null;
		for(T entity : entities){
			retList.add(saveEntity(entity));
		}
		return retList;
	}
	@Override
	public void update(T entity) {
		if (entity != null)
			getSession().update(entity);
	}
	@Override
	public void updateEntity(T entity) {
		getSession().update(entity);
	}
	@Override
	public void updateEntities(List<T> entities) {
		for(T entity : entities){
			updateEntity(entity);
		}
	}
	@Override
	public void delete(T entity) {
		if (entity != null)
			getSession().delete(entity);
	}
	@Override
	public void deleteEntity(T entity) {
		getSession().delete(entity);
	}
	@Override
	public void deleteEntities(List<T> entities) {
		for(T entity : entities){
			getSession().delete(entity);
		}
	}
	@Override
	public T get(Class<T> theClass, Serializable id) {
		return (T) getSession().get(theClass, id);
	}
	@Override
	public T load(Class<T> theClass, Serializable id) {
		return (T) getSession().load(theClass, id);
	}
	@Override
	public T findOneByHql(String hqlStr) {
		Query query = getSession().createQuery(hqlStr);
		List localList = query.list();
		if ((localList != null) && (localList.size() > 0)) {
			return (T) localList.get(0);
		}
		return null;
	}
	@Override
	public T findOneByHqlParamMap(String hqlStr, Map<String, Object> paramMap) {
		Query query = getSession().createQuery(hqlStr);
		makeQuerySqlStr(query, paramMap);
		List localList = query.list();
		if ((localList != null) && (localList.size() > 0)) {
			return (T) localList.get(0);
		}
		return null;
	}
	@Override
	public List<T> findAllByHql(String hqlStr) {
		Query query = getSession().createQuery(hqlStr);
		return query.list();
	}
	@Override
	public List<T> findAllByHqlPageSize(String hqlStr, int page, int size) {
		Query query = getSession().createQuery(hqlStr);
		return query.setFirstResult(page).setMaxResults(size).list();
	}
	@Override
	public List<T> findAllByHqlParamMap(String hqlStr, Map<String, Object> params) {
		Query query = getSession().createQuery(hqlStr);
		makeQuerySqlStr(query, params);
		return query.list();
	}
	@Override
	public List<T> findAllByHqlParamMapPageSize(String hqlStr, Map<String, Object> params, int page, int size) {
		Query query = getSession().createQuery(hqlStr);
		makeQuerySqlStr(query, params);
		return query.setFirstResult(page).setMaxResults(size).list();
	}
	@Override
	public T findOneBySql(String sqlStr) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlStr);
		List localList = sqlQuery.list();
		if ((localList != null) && (localList.size() > 0)) {
			return (T) localList.get(0);
		}
		return null;
	}
	@Override
	public T findOneBySqlParamMap(String sqlStr, Map<String, Object> paramMap) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlStr);
		makeQuerySqlStr(sqlQuery, paramMap);
		List localList = sqlQuery.list();
		if ((localList != null) && (localList.size() > 0)) {
			return (T) localList.get(0);
		}
		return null;
	}
	@Override
	public List<Map> findAllBySql(String sqlStr) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlStr);
		return sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	@Override
	public List<Map> findAllBySqlPageSize(String sqlStr, int page, int size) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlStr);
		return sqlQuery.setFirstResult(page).setMaxResults(size).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	@Override
	public List<Map> findAllBySqlParamMap(String sqlStr, Map<String, Object> paramMap) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlStr);
		makeSqlQuerySqlStr(sqlQuery, paramMap);
		return sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	@Override
	public List<Map> findAllBySqlParamMapPageSize(String sqlStr, Map<String, Object> paramMap,int page, int size) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlStr);
		makeSqlQuerySqlStr(sqlQuery, paramMap);
		return sqlQuery.setFirstResult(page).setMaxResults(size).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	@Override
	public List<Map> findAllBySqlParamMapColumnsCharCase(String sqlStr, Map<String, Object> paramMap, List<String> columns, int charCase) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlStr);
		makeSqlQuerySqlStr(sqlQuery, paramMap);
		if ((columns != null) || (charCase != 0)) {
			return sqlQuery.setResultTransformer(new BasicUtilResultTransFormer(columns, charCase)).list();
		}
		return sqlQuery.setResultTransformer(BasicUtilResultTransFormer.INSTANCE).list();
	}
	@Override
	public List<Map> findAllBySqlParamMapColumnsCharCasePageSize(String sql, Map<String, Object> params, List<String> columns, int charCase, int page, int size) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if ((params != null) && (!params.isEmpty())) {
			for (String str : params.keySet()) {
				sqlQuery.setParameter(str, params.get(str));
			}
		}
		if ((columns != null) || (charCase != 0)) {
			return sqlQuery.setResultTransformer(new BasicUtilResultTransFormer(columns, charCase)).setFirstResult(page).setMaxResults(size).list();
		}
		return sqlQuery.setResultTransformer(BasicUtilResultTransFormer.INSTANCE).list();
	}
	@Override
	public int executeBySql(String sqlStr) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlStr);
		return sqlQuery.executeUpdate();
	}
	@Override
	public int executeBySqlParamMap(String sqlStr, Map<String, Object> paramMap) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlStr);
		if ((paramMap != null) && (!paramMap.isEmpty())) {
			for (String str : paramMap.keySet()) {
				Object localObject = paramMap.get(str);
				if ((localObject instanceof BasicUtilNullValue))
					sqlQuery.setParameter(str, null, ((BasicUtilNullValue) localObject).getType());
				else if ((localObject instanceof List))
					sqlQuery.setParameterList(str, (List) localObject);
				else 
					sqlQuery.setParameter(str, paramMap.get(str));
			}
		}
		return sqlQuery.executeUpdate();
	}
	@Override
	public int executeByHql(String hqlStr) {
		Query localQuery = getSession().createQuery(hqlStr);
		return localQuery.executeUpdate();
	}
	@Override
	public int executeByHqlParamMap(String hqlStr, Map<String, Object> paramMap) {
		Query query = getSession().createQuery(hqlStr);
		makeQuerySqlStr(query, paramMap);
		return query.executeUpdate();
	}

	/**
	 * 不会用session的get(Class class, Serializable id)方法,不然可以直接
	 * getCurrentSession.get(T.class, id)
	 */
	
	@Override
	public long countByHql(String hqlStr) {
		Query localQuery = getSession().createQuery(hqlStr);
		return (long) localQuery.uniqueResult();
	}
	@Override
	public long countByHqlParamMap(String hqlStr, Map<String, Object> paramMap) {
		Query query = getSession().createQuery(hqlStr);
		makeQuerySqlStr(query, paramMap);
		return (long) query.uniqueResult();
	}
	@Override
	public BigInteger countBySql(String sql) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		BigInteger localBigInteger = null;
		Object localObject = sqlQuery.uniqueResult();
		if ((localObject instanceof BigDecimal))
			localBigInteger = ((BigDecimal) localObject).toBigInteger();
		else 
			localBigInteger = (BigInteger) localObject;
		return localBigInteger;
	}
	@Override
	public BigInteger countBySqlParamMap(String sql, Map<String, Object> paramMap) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		makeSqlQuerySqlStr(sqlQuery, paramMap);
		BigInteger localBigInteger = null;
		Object localObject = sqlQuery.uniqueResult();
		if ((localObject instanceof BigDecimal))
			localBigInteger = ((BigDecimal) localObject).toBigInteger();
		else 
			localBigInteger = (BigInteger) localObject;
		return localBigInteger;
	}
	@Override
	public void commit() {
		getSession().getTransaction().commit();
		getSession().beginTransaction();
	}
	@Override
	public void rollback() {
		getSession().getTransaction().rollback();
		getSession().beginTransaction();
	}
	@Override
	public void doWork(Work work) {
		getSession().doWork(work);
	}
	@Override
	public <R> R doReturningWork(ReturningWork<R> returningWork) {
		return getSession().doReturningWork(returningWork);
	}
	@Override
	public void flush() {
		getSession().flush();
	}
	@Override
	public void evict(T entity) {
		getSession().evict(entity);
	}
	@Override
	public void merge(T entity) {
		if (entity != null)
			getSession().merge(entity);
	}
	@Override
	public void clearSession() {
		getSession().clear();
	}
	

	
	/**
	 * @author LiuTao @date 2018年5月30日 下午1:52:47 
	 * @Title: makeSqlQuerySqlStr 
	 * @Description: TODO(Describe) 
	 * @param sqlQuery
	 * @param paramMap
	 */
	private void makeSqlQuerySqlStr(SQLQuery sqlQuery, Map<String, Object> paramMap) {//makeSqlQuerySqlStr
		if ((paramMap != null) && (!paramMap.isEmpty())){
			for (String str : paramMap.keySet()) {
				Object object = paramMap.get(str);
				if ((object instanceof List))
					sqlQuery.setParameterList(str, (List) object);
				else
					sqlQuery.setParameter(str, paramMap.get(str));
			}
		}
	}
	/**
	 * @author LiuTao @date 2018年5月30日 下午1:52:51 
	 * @Title: makeSqlQuerySqlStr 
	 * @Description: TODO(Describe) 
	 * @param query
	 * @param paramMap
	 */
	private void makeQuerySqlStr(Query query, Map<String, Object> paramMap) {
		if ((paramMap != null) && (!paramMap.isEmpty())){
			for (String str : paramMap.keySet()) {
				Object object = paramMap.get(str);
				if ((object instanceof List))
					query.setParameterList(str, (List) object);
				else
					query.setParameter(str, paramMap.get(str));
			}
		}
	}

}
