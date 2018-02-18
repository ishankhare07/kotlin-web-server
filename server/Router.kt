package server

import java.util.regex.Pattern

class Router {
    var mappings = HashMap<Pattern, String>() 

    fun register(route: String, some_string: String) {
        this.mappings.put(Pattern.compile(route), some_string)
    }

    fun match(url: String) {
        for (route in this.mappings.keys) {
            var m = route.matcher(url)
            if(m.find()) {
                println("pattern matched => ${route}")
                println("mapping to => ${this.mappings.get(route)}")
                return
            }
        }

        throw Exception("No matching route found! => ${url}")
    }
}
