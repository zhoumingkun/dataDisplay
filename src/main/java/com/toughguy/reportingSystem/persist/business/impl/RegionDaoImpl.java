package com.toughguy.reportingSystem.persist.business.impl;
import org.springframework.stereotype.Repository;
import com.toughguy.reportingSystem.model.business.Region;
import com.toughguy.reportingSystem.persist.business.prototype.IRegionDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
/**
 * 地域Dao实现类
 * @author zmk
 *
 */
@Repository
public class RegionDaoImpl extends GenericDaoImpl<Region, Integer> implements IRegionDao{

}
