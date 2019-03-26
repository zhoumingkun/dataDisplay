package com.toughguy.educationSystem.persist.content.impl;
import org.springframework.stereotype.Repository;
import com.toughguy.educationSystem.model.content.Notice;
import com.toughguy.educationSystem.persist.content.prototype.INoticeDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 校园互动Dao实现类
 * @author zmk
 *
 */
@Repository
public class NoticeImpl extends GenericDaoImpl<Notice, Integer> implements INoticeDao{
	
	

}
