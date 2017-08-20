/*
  
 
 * Package:com.rbt.service.impl
 * FileName: NavService.java 
 */
package com.rbt.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.model.Nav;
import com.rbt.service.INavService;
import com.rbt.dao.INavDao;

@Service
public class NavService extends GenericService<Nav,String> implements INavService {
	
	INavDao navDao;

	@Autowired
	public NavService(INavDao navDao) {
		super(navDao);
		this.navDao = navDao;
	}

}
