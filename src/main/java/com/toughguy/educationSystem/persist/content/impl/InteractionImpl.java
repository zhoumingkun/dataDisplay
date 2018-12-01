package com.toughguy.educationSystem.persist.content.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Interaction;
import com.toughguy.educationSystem.persist.content.prototype.IInteractionDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 校园互动Dao实现类
 * @author zmk
 *
 */
@Repository
public class InteractionImpl extends GenericDaoImpl<Interaction, Integer> implements IInteractionDao{
	

}
