package org.jelly.orm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;
import org.jelly.model.Pager;
import org.jelly.util.ClassUtil;
import org.jelly.util.CollectionUtil;
import org.jelly.util.StringUtil;
/**
 * 封装 hibernate 对数据库常用的操作
 * @author Lychie Fan
 * @since 1.2.0
 */
@SuppressWarnings("unchecked")
public abstract class HibernateDao <E, K extends Serializable> {

	private String pk;
	private boolean pkSortable;
	private boolean pkAutoSort;
	private Class<?> entityClass;
	private SessionFactory sessionFactory;
	
	/**
	 * <des> 通过主键查找 </des>
	 * @since 1.2.0
	 */
	public E get(K id){
		return (E) getSession().get(entityClass, id);
	}
	
	/**
	 * <des> 通过主键查找 </des>
	 * @since 1.2.0
	 */
	public E load(K id){
		return (E) getSession().load(entityClass, id);
	}
	
	/**
	 * <des> 获取所有数据 </des>
	 * @since 1.2.0
	 */
	public List<E> getAll(){
		return find(createCriteria());
	}

	/**
	 * <des> 条件查找 </des>
	 * @see org.hibernate.criterion.Restrictions
	 * @since 1.2.0
	 */
	public List<E> find(Criterion... criterions){
		Criteria criteria = createCriteria(criterions);
		return find(criteria);
	}

	/**
	 * <des> 条件查找 </des>
	 * @see org.hibernate.criterion.Restrictions
	 * @since 1.2.0
	 */
	public E findUnique(Criterion... criterions){
		Criteria criteria = createCriteria(criterions);
		return findUnique(criteria);
	}

	/**
	 * <des> Criteria 查询 </des>
	 * @since 1.2.0
	 */
	protected List<E> find(Criteria criteria){
		if(pkAutoSort && pkSortable){
			criteria.addOrder(Order.asc(pk));
		}
		return criteria.list();
	}

	/**
	 * <des> Criteria 查询数据库唯一记录 </des>
	 * @since 1.2.0
	 */
	protected E findUnique(Criteria criteria){
		return (E) criteria.uniqueResult();
	}

	/**
	 * <des> Query 查询 </des>
	 * @since 1.2.0
	 */
	protected List<E> find(Query query){
		return query.list();
	}

	/**
	 * <des> Query 查询数据库中唯一记录 </des>
	 * @since 1.2.0
	 */
	protected E findUnique(Query query){
		return (E) query.uniqueResult();
	}

	/**
	 * <des> HQL 查询 </des>
	 * @since 1.2.0
	 */
	protected List<E> find(String hql, Object... values){
		return find(createQuery(hql, values));
	}

	/**
	 * <des> HQL 查询数据库中唯一记录 </des>
	 * @since 1.2.0
	 */
	protected E findUnique(String hql, Object... values){
		return findUnique(createQuery(hql, values));
	}
	
	/**
	 * <des> 分页查询数据库表中所有数据 </des>
	 * @since 1.2.0
	 */
	public Pager<E> findByPager(int currentPage, int pageSize){
		return findByPager(currentPage, pageSize, createCriteria());
	}

	/**
	 * @see org.hibernate.criterion.Restrictions
	 * @since 1.2.0
	 */
	public Pager<E> findByPager(int currentPage, int pageSize, Criterion... criterions){
		return findByPager(currentPage, pageSize, createCriteria(criterions));
	}

	/**
	 * <des> Criteria 分页查询 </des>
	 * @since 1.2.0
	 */
	protected Pager<E> findByPager(int currentPage, int pageSize, Criteria criteria){
		int totalCount = getCounts(criteria);
		criteria.setProjection(null);
		int beginIndex = getPagerBeginIndex(currentPage, pageSize, totalCount);
		criteria.setFirstResult(beginIndex);
		criteria.setMaxResults(pageSize);
		List<E> result = find(criteria);
		return new Pager<E>(currentPage, pageSize, totalCount, result);
	}

	/**
	 * <des> Query 分页查询 </des>
	 * @since 1.2.0
	 */
	protected Pager<E> findByPager(int currentPage, int pageSize, Query query){
		int totalCount = getCounts(query);
		int beginIndex = getPagerBeginIndex(currentPage, pageSize, totalCount);
		query.setFirstResult(beginIndex);
		query.setMaxResults(pageSize);
		List<E> result = find(query);
		return new Pager<E>(currentPage, pageSize, totalCount, result);
	}
	
	/**
	 * <des> HQL 分页查询 </des>
	 * @since 1.2.0
	 */
	protected Pager<E> findByPager(int currentPage, int pageSize, String hql, Object... values){
		return findByPager(currentPage, pageSize, createQuery(hql, values));
	}

	/**
	 * <des> 保存 </des>
	 * @since 1.2.0
	 */
	public K save(E entity){
		return (K) this.getSession().save(entity);
	}
	
	/**
	 * <des> 保存或更新 </des>
	 * @since 1.2.0
	 */
	public void saveOrUpdate(E entity){
		this.getSession().saveOrUpdate(entity);
	}
	
	/**
	 * <des> 更新 </des>
	 * @since 1.2.0
	 */
	public void update(E entity){
		this.getSession().update(entity);
	}
	
	/**
	 * <des> 删除 </des>
	 * @since 1.2.0
	 */
	public void delete(E entity){
		this.getSession().delete(entity);
	}
	
	/**
	 * <des> 批量删除 </des>
	 * @since 1.2.0
	 */
	public void delete(Collection<E> entities){
		if(CollectionUtil.isNotEmpty(entities)){
			Session session = this.getSession();
			for(E entity : entities){
				session.delete(entity);
			}
		}
	}
	
	/**
	 * <des> 删除 </des>
	 * @since 1.2.0
	 */
	public void deleteById(K id){
		E entity = get(id);
		if(entity != null){
			this.getSession().delete(entity);
		}
	}
	
	/**
	 * <des> 批量删除 </des>
	 * @since 1.2.0
	 */
	public void deleteByIds(Collection<K> ids){
		if(CollectionUtil.isNotEmpty(ids)){
			for(K id : ids){
				deleteById(id);
			}
		}
	}
	
	/**
	 * <des> 获取数据库中的记录的总条数 </des>
	 * @since 1.2.0
	 */
	public int getCounts(){
		return getCounts(createCriteria());
	}
	
	/**
	 * <des> 获取数据库中的记录的总条数 </des>
	 * @see org.hibernate.criterion.Restrictions
	 * @since 1.2.0
	 */
	public int getCounts(Criterion... criterions){
		return getCounts(createCriteria(criterions));
	}
	
	/**
	 * <des> Criteria 查询数据库中记录的总条数 </des>
	 * @since 1.2.0
	 */
	protected int getCounts(Criteria criteria){
		Number counts = (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return counts.intValue();
	}

	/**
	 * <des> Query 查询数据库中记录的总条数 </des>
	 * @since 1.2.0
	 */
	protected int getCounts(Query query){
		String queryString = query.getQueryString();
		queryString = StringUtil.substringBefore(queryString, " order by ");
		Query hqlQuery = getSession().createQuery("select count(*) " + queryString);
		Number counts = (Number) hqlQuery.uniqueResult();
		return counts.intValue();
	}

	/**
	 * <des> HQL 查询数据库中记录的总条数 </des>
	 * @since 1.2.0
	 */
	protected int getCounts(String hql, Object... values){
		return getCounts(createQuery(hql, values));
	}
	
	/**
	 * <des> 开始事务 </des>
	 * @since 1.2.0
	 */
	public Transaction beginTransaction(){
		return getSession().beginTransaction();
	}

	/**
	 * <des> Criteria </des>
	 * @since 1.2.0
	 */
	protected Criteria createCriteria(Criterion... criterions){
		Criteria criteria = getSession().createCriteria(entityClass);
		if (criterions != null && criterions.length > 0)
			for (Criterion criterion : criterions)
				criteria.add(criterion);
		return criteria;
	}

	/**
	 * <des> Query </des>
	 * @since 1.2.0
	 */
	protected Query createQuery(String hql, Object... values){
		if(pkAutoSort && pkSortable){
			if(hql.contains("order by")){
				hql += ", " + pk;
			}else{
				hql += " order by " + pk;
			}
		}
		Query query = getSession().createQuery(hql);
		if(values != null){
			for(int i = 0; i < values.length; i++){
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * <des> 获取 Session </des>
	 * @since 1.2.0
	 */
	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}

	protected void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.pkAutoSort = true;
		init();
	}

	/**
	 * <des> 获取 SessionFactory </des>
	 * @since 1.2.0
	 */
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * <des> 设置是否允许主键查询结果按主键排序 </des>
	 * @since 1.2.0
	 */
	protected void setPkAutoSort(boolean pkAutoSort) {
		this.pkAutoSort = pkAutoSort;
	}

	// 获取分页查询的开始索引值
	private int getPagerBeginIndex(int currentPage, int pageSize, int totalCount){
		int totalPage = totalCount / pageSize;
		if(totalCount % pageSize != 0){
			totalPage += 1;
		}
		if(currentPage > totalPage){
			return totalPage - 1 > 0 ? (totalPage - 1) * pageSize : 0;
		}
		return currentPage <= 0 ? 0 : (currentPage - 1) * pageSize;
	}
	
	// 执行初始化
	private void init(){
		this.entityClass = ClassUtil.getSuperclassGenericType(getClass());
		ClassMetadata metadata = sessionFactory.getClassMetadata(entityClass);
		this.pk = metadata.getIdentifierPropertyName();
		Class<?> pkType = metadata.getIdentifierType().getReturnedClass();
		this.pkSortable = ClassUtil.isInstanceOf(pkType, Number.class) ? true
				: ClassUtil.isInstanceOf(pkType, String.class) ? true
						: ClassUtil.isInstanceOf(pkType, Date.class);
	}
	
}