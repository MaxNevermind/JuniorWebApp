package my.sample.project1.post.service;

import java.util.Date;
import java.util.List;

import my.sample.project1.post.Post;
import my.sample.project1.post.dao.PostDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implemantation of {@link PostService}. 
 *
 * @author Maxim Konstantinov
 */
@Repository
@Transactional
public class PostServiceImpl implements PostService{

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private PostDao postDao;
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	
    /**
     * Returns messages according to filter params
     * @param userName - full user name in a post
     * @param dateFrom - start date of the period of filtering, could be null which means no lower border when filtering
     * @param dateTo - end date of the period of filtering, could be null which means no upper border when filtering
     * @param pageNumber - number of the page in a result list, results are pagged 10 posts per page
     * @return list of messages
     */	
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getFilteredPosts(String userName, Date dateFrom, Date dateTo, int pageNumber) {
    	Criteria criteria = getFilterCriteria(userName, dateFrom, dateTo);
		criteria.addOrder(Order.desc("creationDate"));
		criteria.setFirstResult((pageNumber-1)*10);
		criteria.setMaxResults(10);
		return (List<Post>)criteria.list();
	}
	
    /**
     * Returns number of messages according to filter params
     * @param userName - full user name in a post
     * @param dateFrom - start date of the period of filtering, could be null which means no lower border when filtering
     * @param dateTo - end date of the period of filtering, could be null which means no upper border when filtering
     * @return Number of messages converted to String
     */	
	@Override
	public String getMessageNumber(String userName, Date dateFrom, Date dateTo) {
		Criteria criteria = getFilterCriteria(userName, dateFrom, dateTo);
		criteria.setProjection(Projections.rowCount());
		return ((Long)criteria.uniqueResult()).toString();
	}

	private Criteria getFilterCriteria(String userName, Date dateFrom, Date dateTo) {
		if (dateFrom == null) 
    		dateFrom = new DateTime(1900,1,1,0,0,0,0).toDate();
    	if (dateTo == null) 
    		dateTo = new DateTime(2100,1,1,0,0,0,0).toDate();
    	
		Criteria criteria = currentSession().createCriteria(Post.class);
		if (userName != null && !userName.trim().isEmpty()) 
			criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.between("creationDate", dateFrom, dateTo));
		return criteria;
	}

	@Override
	public void cleanDb() {
		currentSession().createSQLQuery("truncate table post").executeUpdate();
	}

	@Override
	public void initializeDb() {
		String[] usersNames = new String[]{"Max", "John", "Kate", "Bill", "George", "Elizabeth"};
		for (int i = 0; i < 25; i++) {
			DateTime dateTime = new DateTime().minusMinutes((int)Math.floor(Math.random() *72*60));
			createPost("message " + i,usersNames[(int)Math.floor(Math.random() * usersNames.length)], dateTime.toDate());
		}
		
	}

	@Override
	public void createPost(String message, String userName, Date date) {
    	Post post = new Post();
    	post.setMessage(message);
    	post.setUserName(userName);
    	post.setCreationDate(date);
    	postDao.save(post);
	}
	
}
