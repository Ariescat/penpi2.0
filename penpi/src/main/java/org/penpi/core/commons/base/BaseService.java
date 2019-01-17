package org.penpi.core.commons.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.penpi.core.commons.utils.ESTransactionUtil;
//import org.ccframe.client.GlobalEx;
//import org.ccframe.commons.helper.EhCacheHelper;
//import org.ccframe.commons.jpaquery.Criteria;
//import org.ccframe.commons.jpaquery.Restrictions;
//import org.ccframe.commons.util.ElasticsearchTransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基础service类，所有service都有继承该类
 * 
 * @author drugbean
 *
 * @param <E> 实体类类型
 * @param <ID> 主键类型
 * @param <R> 仓库类型
 */
public abstract class BaseService <E extends Serializable, ID extends Serializable, R extends BaseRepository<E,ID>> { //spring 4.X 支持泛型注入
	
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private Class<E> entityClass;
    private String idFieldName;
    private Class<?> idFieldType;
    
	@PersistenceContext
	@Qualifier(value = "entityManagerFactory")
	private EntityManager entityManager;
	
//	@Autowired
//	private EhCacheHelper ehCacheHelper;
	
    @SuppressWarnings("unchecked") //NOSONAR
    public BaseService() {
        Class typeCls = getClass();
        Type genType = typeCls.getGenericSuperclass(); // 获得带有泛型的父类
        while (true) {
            if (!(genType instanceof ParameterizedType)) {
                typeCls = typeCls.getSuperclass();
                genType = typeCls.getGenericSuperclass();
            } else {
                break;
            }
        }
        this.entityClass = (Class<E>) ((ParameterizedType) genType).getActualTypeArguments()[0];
        
    	Field[] fields = entityClass.getDeclaredFields();
    	for(Field field: fields){
    		if(field.isAnnotationPresent(Id.class)){
    			idFieldName = field.getName();
    			idFieldType = field.getType();
    		}
    	}
    }
  
    public Class<E> getEntityClass(){
    	return entityClass;
    }

	protected Logger getLogger() {
		return logger;
	}

	public String getIdFieldName() {
		return idFieldName;
	}

	@Transactional(readOnly = true)
	public List<ID> findIdList(){
		return entityManager.createQuery("select o." + idFieldName + " from " + entityClass.getSimpleName() + " o").getResultList();
	}

	@Transactional(readOnly = true)
	public ID getIdByKey(String fieldName, Object value){
		try {
			return (ID)entityManager.createQuery("select o." + idFieldName + " from " + entityClass.getSimpleName() + " o where " + fieldName + "=?").setParameter(0, value).getSingleResult();
		}catch(Exception e) {
			return null;
		}
	}

	private R repository;
	
	@Autowired
	public void setRepository(R repository) {
		this.repository = repository;
	}

	protected R getRepository(){
		return repository;
	}

	@Transactional(readOnly = true)
	public E getById(ID id) {//
		return getRepository().findOne(id);
	}
	
	@Transactional(readOnly = true)
	public List<E> listAll() {
		return getRepository().findAll();
	}
	
	@Transactional
	public E save(E data){
		E result = getRepository().save(data);
		ESTransactionUtil.pushSave(this.getClass(), result);
		return result;
	}
	
	@Transactional
	public E saveAndFlush(E data){
		E result = getRepository().saveAndFlush(data);
//		  ehCacheHelper.transactionCommit();
        ESTransactionUtil.pushSave(this.getClass(), data);
        ESTransactionUtil.commit();
        return result;
	}

	@Transactional
	public void delete(E data){
		getRepository().delete(data);
        ESTransactionUtil.pushDelete(this.getClass(), data);
	}

	@Transactional
	public void deleteById(ID id){
		getRepository().delete(id);
        ESTransactionUtil.pushDeleteById(this.getClass(), id);
	}

//	@Transactional(readOnly = true)
//	public List<E> findByKey(String fieldName, Object value, Order... orders){
//		if(orders.length == 0){
//			return getRepository().findAll(new Criteria<E>().add(Restrictions.eq(fieldName, value)));
//		}else{
//			return getRepository().findAll(new Criteria<E>().add(Restrictions.eq(fieldName, value)), new Sort(orders));
//		}
//	}
//
//	@Transactional(readOnly = true)
//	public E getByKey(String fieldName, Object value){
//		return getRepository().findOne(new Criteria<E>().add(Restrictions.eq(fieldName, value)));
//	}
}

