import java.net.ServerSocket
import java.net.Socket
import kotlin.collections.HashMap

class HttpRequest {
	var requestData = String()
	var type = String()
	var url = String()
	var httpVersion = String()
	var headers = HashMap<String, String>()
	var body = String()

	constructor(requestData: String) {
		this.requestData = requestData
	}

	fun parseRequestData() {
		var lines = this.requestData.split("\r\n")
		for(i in lines.indices) {
			if(i == 0) {
				//first line
				var r = lines[i].split(" ")
				this.type = r[0]
				this.url = r[1]
				this.httpVersion = r[2]
			} else {
				if(!lines[i].isBlank()) {
					if (lines[i][0].toInt() == 0) continue
					var h = lines[i].split(":")
					this.headers.put(h[0], h[1])
				}
			}
		}
	}

	override fun toString(): String {
		return "<HttpRequest: ${this.type}, ${this.url}>"
	}
}

class ConnectionHandler {
	var conn: Socket
	var requestData: String

	constructor(conn: Socket) {
		this.conn = conn
		this.requestData = String()
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
}

fun main(args: Array<String>) {
	var socket = ServerSocket(5000)

	println("listening for connections on http://0.0.0.0:5000")
	var handler = ConnectionHandler(socket.accept())
	var request = handler.getRequestObject()
	println(request)
	println(request.type)
	println(request.url)
	println(request.headers)
}
