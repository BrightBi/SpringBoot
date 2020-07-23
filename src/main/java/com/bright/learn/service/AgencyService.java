package com.bright.learn.service;

import com.bright.learn.domain.Agency;

public interface AgencyService {

	int insert(Agency agency);

	int delete(Long id);

	int update(Agency agency);

	Agency query(Long id);

}
