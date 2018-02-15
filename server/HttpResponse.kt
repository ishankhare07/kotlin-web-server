package server

import server.HttpStatusCodes

class HttpResponse {
	var responseData: String
	var statusCode: Int
	var httpVersion: String = "HTTP/1.1"
	var contentType = "text/html"

	constructor(data: String, statusCode: Int) {
		this.responseData = data
		this.statusCode = statusCode
	}

	fun toByteArray(): ByteArray {
		var res = StringBuilder()
		res.append("${this.httpVersion} ${this.statusCode} ${HttpStatusCodes.get(this.statusCode)}\r\n")
		res.append("Content-Type: ${this.contentType}\r\n")
		res.append("Connection: close\r\n")
		res.append("\n" + this.responseData + "\r\n")
		return res.toString().toByteArray()
	}

	override fun toString(): String {
		return "[HttpResponse: ${this.statusCode} ${HttpStatusCodes.get(this.statusCode)}]"
	}
}
