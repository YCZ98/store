package cn.tedu.store.db;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSource {
	@Autowired
	javax.sql.DataSource dataSource;
	
	@Test
	public void sy() throws SQLException {
		System.out.println(dataSource.getConnection());
	}
}
