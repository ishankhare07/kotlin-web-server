package server

val HttpStatusCodes: HashMap<Int, String> = hashMapOf(
	// Informational responses
	100 to "Continue",
	101 to "Switching Protocols",
	102 to "Processing",
	103 to "Early Hints",

	// Success
	200 to "Ok",
	201 to "Created",
	202 to "Accepted",
	203 to "Non-Authoritative Information",
	204 to "No Content",
	205 to "Reset Content",
	206 to "Partial Content",
	207 to "Multi-Status",
	208 to "Already Reported",
	226 to "IM Used",

	// Redirection
	300 to "Multiple Choices",
	301 to "Moved Permanently",
	302 to "Found",
	303 to "See Other",
	304 to "Not Modified",
	305 to "Use Proxy",
	306 to "Switch Proxy",
	307 to "Temporary Redirect",
	308 to "Permanent Redirect",

	// Client Errors
	400 to "Bad Request",
	401 to "Unauthorized",
	402 to "Payment Required",
	403 to "Forbidden",
	404 to "Not Found",
	405 to "Method Not Allowed",
	406 to "Not Acceptable",
	407 to "Proxy Authentication Required",
	408 to "Request Timeout",
	409 to "Conflict",
	410 to "Gone",
	411 to "Length Required"
)
