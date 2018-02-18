package server

import java.util.regex.Pattern
import server.ApiView
import server.HttpResponse
import server.HttpRequest

class Router {
    var mappings = HashMap<Pattern, ApiView>() 

    fun register(route: String, view: ApiView) {
        this.mappings.put(Pattern.compile(route), view)
    }

    fun match(request: HttpRequest): HttpResponse {
        for (route in this.mappings.keys) {
            var m = route.matcher(request.url)
            if(m.find()) {
                var view = this.mappings.get(route)
                return view?.dispatch(request) ?: HttpResponse("Server Error", 500)
            }
        }

        return HttpResponse("Not found!", 404)
    }
}
