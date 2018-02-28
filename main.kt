import java.net.ServerSocket
import server.ConnectionHandler
import server.Router
import server.ApiView
import server.HttpRequest
import server.HttpResponse

class MyView: ApiView() {
    override fun get(request: HttpRequest): HttpResponse {
        println(request)
        return HttpResponse("That's fast!", 200)
    }
}

fun main(args: Array<String>) {
    var socket = ServerSocket(5000)
    println("listening for connections on http://0.0.0.0:5000")

    var routes = Router()
    routes.register("^/$", MyView())
    routes.register("^/api/users/$", MyView())
    routes.register("^/api/users/\\d+/$", MyView())

    while(true) {
        ConnectionHandler(socket.accept(), routes)
    }
}
