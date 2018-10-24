package com.toughguy.reportingSystem.service.business.impl;

import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.service.business.prototype.IInformationService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;

/**
 * 举报信息Service实现类
 * @author BOBO
 *
 */
@Service
public class InformationServiceImpl extends GenericServiceImpl<Information, Integer> implements IInformationService {

}
