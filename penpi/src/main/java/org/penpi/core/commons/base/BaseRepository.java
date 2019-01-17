package org.penpi.core.commons.base;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 基础仓库，所有Repository都要继承该类
 * 
 * @author drugbean
 *
 * @param <E> 实体类类型
 * @param <ID> 主键类型
 */
public interface BaseRepository<E, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

}
