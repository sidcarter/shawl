import org.junit.Assert._

import com.gistlabs.mechanize.Mechanize
import com.gistlabs.mechanize.headers.Headers
import com.gistlabs.mechanize.Resource
import com.gistlabs.mechanize.document.AbstractDocument
import com.gistlabs.mechanize.document.html.form.Form
import com.gistlabs.mechanize.impl.MechanizeAgent

object Shawl extends App {
	
	println("username: " )
	val username = input
	println("password: " )
	val password = input
	
	val agent= new MechanizeAgent()
	agent.setUserAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
			
	val page:AbstractDocument  = agent.get("https://www.schwab.com/public/schwab/client_home")
	println(page)
	
	val form = page.form("SignonForm")
	println(form)
	form.get("SignonAccountNumber").set(username)
	form.get("SignonPassword").set(password)
	val response:AbstractDocument= form.submit()
	println(response)
		
	/**
	val page:AbstractDocument  = agent.get("https://client.schwab.com/Login/SignOn/CustomerCenterLogin.aspx")
	val usernameField: String = "ctl00$WebPartManager1$CenterLogin$LoginUserControlId$txtLoginID"
	val response:AbstractDocument= form.submit()
	val action = page.action("https://client.schwab.com/Login/SignOn/signon.ashx")
	


	println(form.elements())
	*/
}
