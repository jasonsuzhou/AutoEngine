package com.mh.base.manage.service;

import java.util.List;

import com.mh.base.persist.domain.Admin;

public interface AdminService {

	boolean signInCheck(String username, String password);

	int addAdmin(Admin admin);

	List<Admin> queryAllAdmin();

	void deleteAdminById(int id);

	Admin queryAdminById(int id);

}
