package my.sample.project1.post.service;

import java.util.Date;
import java.util.List;

import my.sample.project1.post.Post;

public interface PostService {
	
	List<Post> getFilteredPosts(String userName, Date dateFrom, Date dateTo, int pageNumber);

	String getMessageNumber(String userName, Date dateFrom, Date dateTo);

	void cleanDb();

	void initializeDb();

	void createPost(String message, String userName, Date date);
}
