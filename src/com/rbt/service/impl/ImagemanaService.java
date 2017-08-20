/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ImagemanaService.java 
 */
package com.rbt.service.impl;
import com.rbt.dao.IImagemanaDao;
import com.rbt.model.Imagemana;
import com.rbt.service.IImagemanaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录图片管理表信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu Dec 27 11:11:10 CST 2014
 */
@Service
public class ImagemanaService extends GenericService<Imagemana,String> implements IImagemanaService {
	
	IImagemanaDao imagemanaDao;

	@Autowired
	public ImagemanaService(IImagemanaDao imagemanaDao) {
		super(imagemanaDao);
		this.imagemanaDao = imagemanaDao;
	}
	
}

