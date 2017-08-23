package filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dcone.dtss.model.dc_user;

/**
 * Servlet Filter implementation class Filter
 */
public class Filter implements javax.servlet.Filter {

    /**
     * Default constructor. 
     */
    public Filter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		String[] notFilter = new String[] { "img", "js","css","login","signin","register","sign","checkimage" };
		String temp = req.getRequestURI();
		if(temp.indexOf("dtss") != -1) {
			if(session.getAttribute("user")==null)
			{
				boolean doFilter = true;
				for (String s : notFilter) {
					if (temp.indexOf(s) != -1) {
						doFilter = false;
						break;
					}
				}
				System.out.println(temp);
				if(doFilter) {
					if(temp.equals("/dtss/frame_b"))
						chain.doFilter(request, response);
					else
						resp.sendRedirect("login");
				}else {
					chain.doFilter(request, response);
				}
			}
			else {
				String[] adminFilter = new String[] { "adminindex","program","account","accounts","wallets","newprogram","recoeds","luckymoneyrain","grabluckymoney","luckyrecords","grabluckyrecords","rewardrecords"};
				dc_user user = (dc_user)session.getAttribute("user");
					if(user.getLocked()==0) {
						
						boolean doFilter = false;
							for (String s : adminFilter) {
								if (temp.indexOf(s) != -1) {
									if(temp.equals("/dtss/accountforuser")||temp.equals("/dtss/programmenu")) {
									}else {
										doFilter = true;
										break;
									}
								}
							}
							if(doFilter) {
								resp.sendRedirect("userindex");
							} else {
								chain.doFilter(request, response);
							}
					}else {
						chain.doFilter(request, response);
					}
			}
		}else {
			chain.doFilter(request, response);
		}
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
