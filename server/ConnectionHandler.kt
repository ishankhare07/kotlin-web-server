package server

import java.net.Socket
import server.HttpRequest

class ConnectionHandler: Runnable {
	var conn: Socket
	var requestData: String
	var t: Thread

	constructor(conn: Socket) {
		this.conn = conn
		this.requestData = String()
		this.t = Thread(this, conn.toString())
		this.t.start()
	}

	fun readRequestData() {
		var b = ByteArray(1024)
		var inps = this.conn.getInputStream()
		inps.read(b, 0, inps.available())

		this.requestData = String(b).trim()
	}

	fun getRequestObject(): HttpRequest {
		if(this.requestData.isEmpty()) {
			this.readRequestData()
		}
		var req = HttpRequest(this.requestData)
		req.parseRequestData()
		return req
	}

	override fun run() {
		var request = this.getRequestObject()
		println(request)
		println(request.type)
		println(request.url)
		println(request.headers)
		this.conn.close()
	}
}
