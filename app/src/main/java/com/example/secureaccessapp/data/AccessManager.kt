package com.example.secureaccessapp.data

class AccessManager (private val currentUser: User) {
    fun hasAccess(requiredLevel: Int): Boolean{
        return currentUser.accessLevel >= requiredLevel
    }
}




fun checkAccess(currentUser: User) {
    val access = AccessManager(currentUser).hasAccess(2)
    if (access) {
        println("Ok, ${currentUser.name}. You have access")
    }
    else{
        println("Sorry,${currentUser.name}. You haven`t access")
    }
}


