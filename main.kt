import java.net.ServerSocket
import server.ConnectionHandler
import server.Router

fun main(args: Array<String>) {
    var socket = ServerSocket(5000)
    println("listening for connections on http://0.0.0.0:5000")
    while(true) {
        var routes = Router()
        routes.register("^\\/$", "root route")
        routes.register("^\\/api\\/users\\/$", "route listing all users on api")
        routes.register("^\\/api\\/users\\/\\d+\\/$", "route listing specific user ids on api")
        ConnectionHandler(socket.accept(), routes)
    }
}
