package com.example.dictionary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform