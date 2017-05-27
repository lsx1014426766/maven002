package zttc.itat.user.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import zttc.itat.user.model.User;
import zttc.itat.user.util.AbstractDbUnitTestCase;
import zttc.itat.user.util.EntitiesHelper;

public class TestUserDao extends AbstractDbUnitTestCase{
	private IUserDao userDao;
	
	@Before
	public void setUp() throws DataSetException, IOException, SQLException {
		userDao = new UserDao();
		//将这个表备份，保留原始数据
		bakcupOneTable("t_user");
	}
	
	@Test
	public void testLoad() throws IOException, DatabaseUnitException, SQLException {
		//xmL数据，准备插入的数据
		IDataSet ds = createDateSet("t_user");
		//下面的操作会清除到db里原有的数据，并执行insert插入准备的数据
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		User tu = userDao.loadByUsername("admin");
		EntitiesHelper.assertUser(tu);
	}
	
	@Test
	public void testAdd() throws IOException, DatabaseUnitException, SQLException {
		IDataSet ds = createDateSet("t_user");
		//清空整个表的原始数据
		DatabaseOperation.TRUNCATE_TABLE.execute(dbunitCon, ds);
		User user = new User("admin","123","管理员");
		userDao.add(user);
		Assert.assertTrue(user.getId()>0);
		
		User tu = userDao.loadByUsername("admin");
		EntitiesHelper.assertUser(tu,user);
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException {
		resumeTable();
	}
}
