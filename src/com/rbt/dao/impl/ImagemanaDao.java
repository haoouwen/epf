/*
 
 * Package:com.rbt.dao.impl
 * FileName: ImagemanaDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IImagemanaDao;
import com.rbt.model.Imagemana;

/**
 * @function 功能  记录图片管理表信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu Dec 27 11:11:10 CST 2014
 */
@Repository
public class ImagemanaDao extends GenericDao<Imagemana,String> implements IImagemanaDao {
	
	public ImagemanaDao() {
		super(Imagemana.class);
	}
	
}

