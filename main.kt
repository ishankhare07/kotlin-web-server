import java.net.ServerSocket
import server.ConnectionHandler

fun main(args: Array<String>) {
    var socket = ServerSocket(5000)
    println("listening for connections on http://0.0.0.0:5000")
    while(true) {
        ConnectionHandler(socket.accept())
    }
}
