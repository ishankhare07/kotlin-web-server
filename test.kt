import server.Converter

fun main(args: Array<String>) {
    var c = Converter("/api/<user_id:int>/posts/<post_id:int>/")
    c.printMatches()
}
