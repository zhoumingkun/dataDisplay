package com.toughguy.educationSystem.persist.content.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.persist.content.prototype.IAccountDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 账户Dao实现类
 * @author zmk
 *
 */
@Repository
public class AccountImpl extends GenericDaoImpl<Account, Integer> implements IAccountDao{
	

}
