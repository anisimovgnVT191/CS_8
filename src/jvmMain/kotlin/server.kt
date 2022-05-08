import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.*
import kotlinx.html.*

fun HTML.index() {
    head {
        title("Hello from Server!")
    }
    body {
        div {
            +"Hello from Server"
        }
        div {
            id = "root"
        }
        script(src = "/static/js.js") {}
    }
}

fun main() {
    embeddedServer(Netty, port = 9090, host = "127.0.0.1") {
        install(AutoHeadResponse)
        routing {
            get("/index.html") {
                call.respondHtml(HttpStatusCode.OK) { index() }
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}