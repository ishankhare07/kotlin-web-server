package server

import java.util.regex.Pattern
import server.ApiView
import server.HttpResponse
import server.HttpRequest


data class NameTypeTuple(var name:String, var type: String)

class Converter {
    var url: String
    var convertedUrl: String = String()
    var paramNames: ArrayList<NameTypeTuple>

    constructor(url: String) {
        this.url = url
        this.paramNames = arrayListOf<NameTypeTuple>()
        this.convertedUrl = url
    }

    fun replaceParams(name: String, type: String) {
        if(type == "int") {
            this.convertedUrl = this.convertedUrl.replace(Regex("<${name}:${type}>"), Regex.escapeReplacement("(\\d+)"))
        } else if(type == "string") {
            this.convertedUrl = this.convertedUrl.replace(Regex("<${name}:${type}>"), Regex.escapeReplacement("(\\w+)"))
        }
    }

    fun printMatches() {
        var m = Pattern.compile("/<(\\w+):(\\w+)>/").matcher(this.url)
        while(m.find()) {
            this.paramNames.add(NameTypeTuple(m.group(1), m.group(2)))
            replaceParams(m.group(1), m.group(2))
        }
        println(this.convertedUrl)
        println(this.paramNames)
        println(Pattern.compile(this.convertedUrl))
    }
}

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
