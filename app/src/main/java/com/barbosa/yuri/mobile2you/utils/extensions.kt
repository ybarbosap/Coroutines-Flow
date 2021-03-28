package com.barbosa.yuri.mobile2you.utils

fun String.releaseDateFormat(): String {
    val split = this.split("-")
    return split[0]
}