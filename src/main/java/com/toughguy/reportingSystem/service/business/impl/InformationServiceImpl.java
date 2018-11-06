package com.toughguy.reportingSystem.service.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.dto.InformationDTO;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.persist.business.prototype.IInformationDao;
import com.toughguy.reportingSystem.service.business.prototype.IInformationService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;

/**
 * 举报信息Service实现类
 * @author BOBO
 *
 */
@Service
public class InformationServiceImpl extends GenericServiceImpl<Information, Integer> implements IInformationService {

	@Override
	public int findNum(int state) {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).findNum(state);
	}
	@Override
	public List<Integer> findValidNumber() {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).findValidNumber();
	}
	@Override
	public List<InformationDTO> findSum() {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).findSum();
	}
	@Override
	public List<Information> getInformation(int informerId) {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).getInformation(informerId);
	}

}
