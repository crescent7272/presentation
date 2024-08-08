package com.egeninsesi.news.utils

import com.egeninsesi.news.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home",R.drawable.home,"home")
    object Authors: BottomNavItem("My Network",R.drawable.ink,"authors")
    object Categories: BottomNavItem("Post",R.drawable.newspaper,"categories")
}