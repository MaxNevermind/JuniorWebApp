package my.sample.project1;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import my.sample.project1.MainController;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class MainControllerTest 
{

	private MainController controller;
	
    @Before
    public void init() {
        controller = new MainController();
    }
    
	@Test
	public void mainPageViewName() 
	{
		ModelAndView mav = controller.mainPage();
        assertViewName(mav, "main");
    }
	
	@Test
	public void postMessagePageViewName() 
	{
		ModelAndView mav = controller.postMessage();
        assertViewName(mav, "postMessage");
    }
	
	@Test
	public void showMessagesPageViewName() 
	{
		ModelAndView mav = controller.messagesPage();
        assertViewName(mav, "showMessages");
    }
}
