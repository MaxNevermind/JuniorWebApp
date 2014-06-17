package my.sample.project1.post.dao;

import my.sample.project1.post.Post;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostDaoImpl implements PostDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Post get(long id) {
		return (Post)currentSession().get(Post.class, id);
	}

	@Override
	public void save(Post post) {
		currentSession().save(post);
	}
}
