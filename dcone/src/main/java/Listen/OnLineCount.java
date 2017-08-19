package Listen;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.dcone.dtss.model.dc_user;

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
    	dc_user user=(dc_user) session.getAttribute("username");
    	if(application.getAttribute("onlineuser")==null)
    	{
    		ArrayList<dc_user> users=new ArrayList<dc_user>();
    		application.setAttribute("onlineuser", users);
    	}
    	else
    	{
    		@SuppressWarnings("unchecked")
			ArrayList<dc_user> users=(ArrayList<dc_user>) application.getAttribute("onlineuser");
    		users.remove(user);
    		application.setAttribute("onlineuser", users);
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession session=arg0.getSession();
    	if((dc_user)session.getAttribute("user")!=null) {
    	dc_user user = (dc_user)session.getAttribute("user");
    	ServletContext application=session.getServletContext();
    	if(application.getAttribute("onlineuser")==null)
    	{
    		ArrayList<dc_user> users=new ArrayList<dc_user>();
    		users.add(user);
    		application.setAttribute("onlineuser", users);
    	}
    	else
    	{
    		@SuppressWarnings("unchecked")
			ArrayList<dc_user> users=(ArrayList<dc_user>) application.getAttribute("onlineuser");
    		users.add(user);
    		application.setAttribute("onlineuser", users);
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
