class Wool {
	
	private var _cookie:String = _
	
	val userAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36"
	
	val schwabThread = "https://www.schwab.com/public/schwab/client_home"

	def getUsername(echoString:String) = {
		scala.io.StdIn.readLine(echoString)
	}
	
	def getPassword(echoString:String) = {
		val tmp_pass = System.console.readPassword(echoString)
		new String(tmp_pass)
	}
	
	def cookie = _cookie
	
	def cookie_= (cookie:String) = _cookie = cookie

}