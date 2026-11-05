package com.example.praktikum6.navigation

object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{studentId}"
    const val PROFILE = "profile"
    const val ABOUT = "about"

    fun detail(studentId: Int): String = "detail/$studentId"
}
