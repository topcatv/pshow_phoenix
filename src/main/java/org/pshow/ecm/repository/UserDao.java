package org.pshow.ecm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.pshow.ecm.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}
