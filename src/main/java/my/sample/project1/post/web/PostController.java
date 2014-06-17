package my.sample.project1.post.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import my.sample.project1.Utils;
import my.sample.project1.post.Post;
import my.sample.project1.post.dao.PostDao;
import my.sample.project1.post.service.PostService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController 
{
	private final Logger LOGGER = Logger.getLogger(getClass());
	@Autowired
	private PostDao postDao;
	@Autowired
	private PostService postService;
	@Autowired
	private Utils utils;
	
    @ResponseBody
    @RequestMapping(value = "/filterMessages", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public List<Post> filterMessages(@RequestParam(value = "userName", required=false) String userName,
    		@RequestParam(value = "dateFrom", required=false) String dateFromS,
    		@RequestParam(value = "dateTo", required=false) String dateToS,
    		@RequestParam(value = "pageNumber", required=false) Integer pageNumber){
    	
    	Date dateFrom = utils.parseDate(dateFromS);
    	Date dateTo = utils.parseDate(dateToS);
    	return postService.getFilteredPosts(userName, dateFrom, dateTo, pageNumber);
    }
    
    @ResponseBody
    @RequestMapping(value = "/getMessageNumber", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public String getMessageNumber(@RequestParam(value = "userName", required=false) String userName,
    		@RequestParam(value = "dateFrom", required=false) String dateFromS,
    		@RequestParam(value = "dateTo", required=false) String dateToS){
    	
    	Date dateFrom = utils.parseDate(dateFromS);
    	Date dateTo = utils.parseDate(dateToS);
    	return "{ \"messageNumber\" : \""+postService.getMessageNumber(userName, dateFrom, dateTo)+"\" }";
    }


	@RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    public String postMessage(@RequestParam("userName") String userName, @RequestParam("message") String message, Model model, HttpServletRequest request){
    	postService.createPost(message, userName, new Date());
    	LOGGER.info("User posted a message. Clien IP: " + request.getRemoteAddr());
    	return "redirect:/postMessage";
    }
    
	@RequestMapping(value = "/cleanDb", method = RequestMethod.POST)
    public void cleanDb(HttpServletRequest request){
		postService.cleanDb();
		LOGGER.info("User cleaned Db. Clien IP: " + request.getRemoteAddr());
    }
	
	@RequestMapping(value = "/initializeDb", method = RequestMethod.POST)
    public void initializeDb(HttpServletRequest request){
		postService.initializeDb();
		LOGGER.info("User initialized Db. Clien IP: " + request.getRemoteAddr());
    }
    
}
