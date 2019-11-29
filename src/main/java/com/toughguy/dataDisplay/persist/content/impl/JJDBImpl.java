package com.toughguy.dataDisplay.persist.content.impl;

import org.springframework.stereotype.Repository;

import com.toughguy.dataDisplay.model.content.JJDB;
import com.toughguy.dataDisplay.persist.content.prototype.IJJDBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;

@Repository
public class JJDBImpl extends GenericDaoImpl<JJDB, Integer> implements IJJDBDao{

}
