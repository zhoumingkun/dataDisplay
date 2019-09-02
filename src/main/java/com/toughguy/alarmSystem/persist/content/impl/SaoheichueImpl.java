package com.toughguy.alarmSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.alarmSystem.model.content.Saoheichue;
import com.toughguy.alarmSystem.persist.content.prototype.ISaoheichueDao;
import com.toughguy.alarmSystem.persist.impl.GenericDaoImpl;
/**
 * 扫黑除恶Dao实现类
 * @author zmk
 *
 */
@Repository
public class SaoheichueImpl extends GenericDaoImpl<Saoheichue, Integer> implements ISaoheichueDao{

}
