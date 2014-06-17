package my.sample.project1.post.dao;

import my.sample.project1.post.Post;

public interface PostDao {
	
	Post get(long id);
	
	void save(Post post);
}
