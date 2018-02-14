import java.net.ServerSocket
import server.*

fun main(args: Array<String>) {
	while(true) {
		var socket = ServerSocket(5000)

		println("listening for connections on http://0.0.0.0:5000")
		ConnectionHandler(socket.accept())
	}
}
