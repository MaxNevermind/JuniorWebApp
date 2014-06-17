package my.sample.project1.post.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import my.sample.project1.post.Post;
import my.sample.project1.post.dao.PostDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:spring-contexts/*Context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class PostDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests 
{
	@Autowired
	private PostDao postDao;
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
    @Before
    public void setUp() throws Exception {
        session = sessionFactory.getCurrentSession();
    }
	
	@Test
	public void testPosting() {
		String testMessage = "Message sfvdjgbvdfthegbhw34";
		
    	Post post = new Post();
    	post.setMessage(testMessage);
    	post.setUserName("Max");
    	post.setCreationDate(new Date());
    	postDao.save(post);
		
    	Post actualPost = (Post) session.get(Post.class, post.getId());
		assertEquals("Method returned wrong post", actualPost.getMessage(), testMessage);
	}
	
}
