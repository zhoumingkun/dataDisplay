package com.toughguy.reportingSystem.persist.business.impl;

import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.model.authority.User;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.persist.business.prototype.IInformationDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;

/**
 * 举报信息Dao实现类
 * @author BOBO
 *
 */
@Repository
public class InformationDaoImpl extends GenericDaoImpl<Information, Integer> implements IInformationDao{

}
