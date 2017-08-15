package Listen;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnLineCount
 *
 */
public class OnLineCount implements HttpSessionListener, HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public OnLineCount() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession session=arg0.getSession();
    	ServletContext application=session.getServletContext();
    	
    	if(application.getAttribute("onlinecount") == null) {
			int i = 0;
			application.setAttribute("onlinecount",i);
		} else {
			int i = ((Integer)application.getAttribute("onlinecount")).intValue();
			if(i>0)
				i --;
			else
				i=0;
			application.setAttribute("onlinecount",i);
		}
    	String username=(String) session.getAttribute("username");
    	if(application.getAttribute("onlineuser")==null)
    	{
    		ArrayList<String> user=new ArrayList<String>();
    		application.setAttribute("onlineuser", user);
    	}
    	else
    	{
    		@SuppressWarnings("unchecked")
			ArrayList<String> user=(ArrayList<String>) application.getAttribute("onlineuser");
    		user.remove(username);
    		application.setAttribute("onlineuser", user);
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession session=arg0.getSession();
    	if((String)session.getAttribute("username")!=null) {
    	String name = (String)session.getAttribute("username");
    	ServletContext application=session.getServletContext();
    	if(application.getAttribute("onlineuser")==null)
    	{
    		ArrayList<String> user=new ArrayList<String>();
    		user.add(name);
    		application.setAttribute("onlineuser", user);
    	}
    	else
    	{
    		@SuppressWarnings("unchecked")
			ArrayList<String> user=(ArrayList<String>) application.getAttribute("onlineuser");
    		user.add(name);
    		application.setAttribute("onlineuser", user);
    	}
    	if(application.getAttribute("onlinecount") == null) {
			int i = 0;
			i++;
			application.setAttribute("onlinecount",i);
		} else {
			int i = ((Integer)application.getAttribute("onlinecount")).intValue();
			i ++;
			application.setAttribute("onlinecount",i);
		}
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession session=arg0.getSession();
    	ServletContext application=session.getServletContext();
    	
    	if(application.getAttribute("onlinecount") == null) {
			int i = 0;
			application.setAttribute("onlinecount",i);
		} else {
			int i = ((Integer)application.getAttribute("onlinecount")).intValue();
			if(i>0)
				i --;
			else
				i=0;
			application.setAttribute("onlinecount",i);
		}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
