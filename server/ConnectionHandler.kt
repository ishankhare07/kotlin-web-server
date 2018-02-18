package server

import java.net.Socket
import server.HttpRequest
import server.Router

// just for testing purpose
import server.HttpResponse

class ConnectionHandler: Runnable {
    var conn: Socket
    var requestData: String
    var t: Thread
    var routes: Router

    constructor(conn: Socket, routes: Router) {
        this.conn = conn
        this.requestData = String()
        this.routes = routes
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
        print(request)
        print("\t")

        try {
            this.routes.match(request.url)
        } catch (ex: Exception) {
            var err_response = HttpResponse("Not Found!", 404)
            println(err_response)
            this.conn.getOutputStream().write(err_response.toByteArray())
            this.conn.close()
            return
        }

        var response = HttpResponse("Hello world", 200)
        println(response)
        this.conn.getOutputStream().write(response.toByteArray())
        this.conn.close()
    }
}
