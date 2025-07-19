package paul.vickers.built4life

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform