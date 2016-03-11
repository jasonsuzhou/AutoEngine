package com.mh.base.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mh.base.manage.service.AdminService;
import com.mh.base.persist.dao.AdminDAO;
import com.mh.base.persist.domain.Admin;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAOImpl;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public boolean signInCheck(String username, String password) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return false;
		}
		Admin key = new Admin();
		key.setUsername(username);
		key.setPassword(password);
		Admin admin = this.adminDAOImpl.queryOneByCondition(key);
		return admin == null ? false : password.equals(admin.getPassword());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addAdmin(Admin admin) {
		Admin key = new Admin();
		key.setUsername(admin.getUsername());
		boolean isExisting = this.adminDAOImpl.queryOneByCondition(key) == null ? false : true;
		if (isExisting) {
			return 10000;
		} else {
			admin.initDate();
			admin.initSalt();
			return this.adminDAOImpl.insertSelective(admin);
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Admin> queryAllAdmin() {
		return this.adminDAOImpl.queryAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAdminById(int id) {
		this.adminDAOImpl.deleteById(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Admin queryAdminById(int id) {
		return this.adminDAOImpl.queryOneById(id);
	}
}
