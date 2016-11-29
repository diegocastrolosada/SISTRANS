package startup;

import javax.jms.JMSException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dtm.VuelAndesDistributed;


public class ContextListener implements ServletContextListener 
{

	private VuelAndesDistributed dtm;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		try 
		{
			dtm.stop();
		} 
		catch (JMSException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{	
		final ServletContext context = arg0.getServletContext();
		VuelAndesDistributed.setPath(context.getRealPath("WEB-INF/ConnectionData"));
		dtm = VuelAndesDistributed.getInstance();
	}

}