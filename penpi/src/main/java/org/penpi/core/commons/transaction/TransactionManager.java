package org.penpi.core.commons.transaction;

import org.penpi.core.commons.utils.ESTransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.TransactionController;

/**
 * 完成缓存、索引的事务同步。
 * 注：如果要即时生效，请单独调用ehcache来刷新缓存或用ESTransactionUtil来刷新索引
 */
public class TransactionManager extends JpaTransactionManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3878501009638970644L;
	
	private CacheManager ehcacheManager;
	
	private int transactionTimeout;

	public void setTransactionTimeout(int transactionTimeout) {
		this.transactionTimeout = transactionTimeout;
	}

	@Autowired
	public void setEhcacheManager(CacheManager ehcacheManager) {
		this.ehcacheManager = ehcacheManager;
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		super.doBegin(transaction, definition);
		ehcacheManager.getTransactionController().begin(transactionTimeout);
		if(!definition.isReadOnly()){ //只读事务无需操作索引
			ESTransactionUtil.init();
		}
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		super.doCommit(status);
		TransactionController controller = ehcacheManager.getTransactionController();
		if (controller.getCurrentTransactionContext() != null) {
			if(status.isRollbackOnly()){
				controller.rollback(); //在rollback only异常下强制rollback
			}
			controller.commit();
		}
		if(!status.isReadOnly()){ //只读事务无需操作索引
			ESTransactionUtil.commit();
		}
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		super.doRollback(status);
		TransactionController controller = ehcacheManager.getTransactionController();
		if (controller.getCurrentTransactionContext() != null) {
			controller.rollback();
		}
		if(!status.isReadOnly()){ //只读事务无需操作索引
			ESTransactionUtil.rollback();
		}
	}

}