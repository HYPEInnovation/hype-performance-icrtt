package de.hype.performance.icrtt

object Headers {
    val headers_get = Map(
        "Accept" -> """text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"""
    )

    val headers_post = Map(
        "Accept" -> """text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8""",
        "Cache-Control" -> """no-cache""",
        "Content-Type" -> """application/x-www-form-urlencoded; charset=UTF-8;""",
        "Pragma" -> """no-cache""",
        "xmlRequest" -> """true"""
    )
}
