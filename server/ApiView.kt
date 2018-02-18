package server

import server.HttpRequest
import server.HttpResponse

open class ApiView {
    open fun get(request: HttpRequest): HttpResponse {
        throw NotImplementedError()
    }

    open fun post(request: HttpRequest): HttpResponse {
        throw NotImplementedError()
    }

    open fun put(request: HttpRequest): HttpResponse {
        throw NotImplementedError()
    }

    open fun patch(request: HttpRequest): HttpResponse {
        throw NotImplementedError()
    }

    open fun delete(request: HttpRequest): HttpResponse {
        throw NotImplementedError()
    }

    final fun dispatch(request: HttpRequest): HttpResponse {
        var response: HttpResponse
        if (request.type == "GET") {
            response = this.get(request)
        } else if (request.type == "POST") {
            response = this.post(request)
        } else if (request.type == "PUT") {
            response = this.put(request)
        } else if (request.type == "PATCH") {
            response = this.patch(request)
        } else if (request.type == "DELETE") {
            response = this.delete(request)
        } else {
            response = HttpResponse("Request type not recognised", 400)
        }

        return response
    }
}
