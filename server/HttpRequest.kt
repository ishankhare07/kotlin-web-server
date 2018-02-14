package server

class HttpRequest {
	var requestData = String()
	var type = String()
	var url = String()
	var httpVersion = String()
	var headers = HashMap<String, String>()
	var body = String()

	constructor(requestData: String) {
		this.requestData = requestData
	}

	fun parseRequestData() {
		var lines = this.requestData.split("\r\n")
		for(i in lines.indices) {
			if(i == 0) {
				//first line
				var r = lines[i].split(" ")
				this.type = r[0]
				this.url = r[1]
				this.httpVersion = r[2]
			} else {
				if(!lines[i].isBlank()) {
					if (lines[i][0].toInt() == 0) continue
					var h = lines[i].split(":")
					this.headers.put(h[0], h[1])
				}
			}
		}
	}

	override fun toString(): String {
		return "<HttpRequest: ${this.type}, ${this.url}>"
	}
}
