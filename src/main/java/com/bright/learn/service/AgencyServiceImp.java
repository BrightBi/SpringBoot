package com.bright.learn.service;

import com.bright.learn.domain.Agency;
import com.bright.learn.domain.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AgencyServiceImp implements AgencyService {

	/*
	 * application.properties 文件中配置了两个数据源，
	 * 并且在 com.bright.learn.config.MultipleDataSourceConfig 中用不同数据源构建了不同的两个 JdbcTemplate
	 */
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate templateP;
	
	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	private JdbcTemplate templateS;
	
	private static final String INSERT = "insert into dbo.test (id, name, date, is_enable, type) values (?, ?, ?, ?, ?)";
	private static final String DELETE = "delete from dbo.test where id = ?";
	private static final String UPDATE = "update dbo.test set name = ? where id = ?";
	private static final String QUERY = "select id, name, date, is_enable, type from dbo.test where id = ?";

	/*
	 * @Transactional 用于指定事物，通常在 service 层使用。可设置参数很多，需要查询下。
	 */
	@Override
	@Transactional
	public int insert(Agency agency) {
		int numP = templateP.update(INSERT, agency.getId(), agency.getName(), agency.getDate(), agency.getIsEnable(), agency.getType().toString());
		int numS = templateS.update(INSERT, agency.getId(), agency.getName(), agency.getDate(), agency.getIsEnable(), agency.getType().toString());
		return numP + numS;
	}

	@Override
	public int delete(Long id) {
		int num = templateP.update(DELETE, id);
		return num;
	}

	@Override
	public int update(Agency agency) {
		int num = templateP.update(UPDATE, agency.getName(), agency.getId());
		return num;
	}

	@Override
	public Agency query(Long id) {
		Agency agency = templateP.queryForObject(QUERY, new RowMapper<Agency> () {
			public Agency mapRow(ResultSet rs, int rowNum) throws SQLException {
				Agency agency = new Agency();
				agency.setId(rs.getLong(1));
				agency.setName(rs.getString(2));
				agency.setDate(rs.getDate(3));
				agency.setIsEnable(rs.getBoolean(4));
				agency.setType(Type.valueOf(rs.getString(5)));
				return agency;
			}
		}, id);
		return agency;
	}
}
