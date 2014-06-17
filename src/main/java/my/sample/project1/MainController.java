package my.sample.project1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController 
{

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(){
    	return new ModelAndView("main");
    }
    
    @RequestMapping(value = "/postMessage", method = RequestMethod.GET)
    public ModelAndView postMessage(){
    	return new ModelAndView("postMessage");
    }
    
    @RequestMapping(value = "/showMessages", method = RequestMethod.GET)
    public ModelAndView messagesPage(){
    	return new ModelAndView("showMessages");
    }
}
