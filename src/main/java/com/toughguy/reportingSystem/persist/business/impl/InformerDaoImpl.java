package com.toughguy.reportingSystem.persist.business.impl;

import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.persist.business.prototype.IInformerDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;

/**
 * 举报信息Dao实现类
 * @author BOBO
 *
 */
@Repository
public class InformerDaoImpl extends GenericDaoImpl<Informer, Integer> implements IInformerDao{

}
