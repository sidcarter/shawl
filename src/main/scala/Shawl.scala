import scala.collection.convert.wrapAsScala._

import com.gistlabs.mechanize.Mechanize
import com.gistlabs.mechanize.headers.Headers
import com.gistlabs.mechanize.Resource
import com.gistlabs.mechanize.document.AbstractDocument
import com.gistlabs.mechanize.document.html.form.Form
import com.gistlabs.mechanize.impl.MechanizeAgent

object Shawl extends App {
	
	val cloth = new Wool()
	
	val username = cloth.getUsername("username: ")
	val password = cloth.getPassword("password: ")
	
	val weaver= new MechanizeAgent()
	weaver.setUserAgent(cloth.userAgent)
			
	var page:AbstractDocument = weaver.get(cloth.schwabThread)
//	println(page)
	
	val form = page.form("SignonForm")

	form.get("SignonAccountNumber").set(username)
	form.get("SignonPassword").set(password)
	val response:AbstractDocument= form.submit()

//	println(response) // for debug
	
	if (response.getUri().endsWith("YES")) {
		println("Authentication succeeded.") 
		val cookieStore = response.getAgent().cookies().getAll()
		for (cookie <- cookieStore) {
			cloth.cookie = (cookie.getValue())
			println (s"Name: ${cookie.getName()}")
			println (s"Domain: ${cookie.getDomain()}")
			println (s"Value: ${cloth.cookie}\n")
		}

//		val reWeaver = new MechanizeAgent()
//		reWeaver.setUserAgent(cloth.userAgent)
//		page = reWeaver.get(response.getUri())
		
//		val htmlElement:HtmlElement = page.htmlElements().find("span#ctl00_wpm_ac_ac_rba_ctl00_dsv")
		
	}
	// /Accounts/Summary/Summary.aspx?ShowUN=YES
	else if (response.getUri().endsWith("ErrorInvalidCredentials")) println("Authentication failed.")
	// /Login/SignOn/CustomerCenterLogin.aspx?ErrorCode=ErrorInvalidCredentials
	else println("Unknown authentication error.") 
	// /Login/SignOn/CustomerCenterLogin.aspx?ErrorCode=Login-Error-DWTThirdAttemptFailed
		
	/**
	println(form)
	println(response.getTitle())
	val page:AbstractDocument  = weaver.get("https://client.schwab.com/Login/SignOn/CustomerCenterLogin.aspx")
	val usernameField: String = "ctl00$WebPartManager1$CenterLogin$LoginUserControlId$txtLoginID"
	val response:AbstractDocument= form.submit()
	val action = page.action("https://client.schwab.com/Login/SignOn/signon.ashx")

	println(form.elements())
	*/
}
