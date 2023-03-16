package com.example.uslesschat

class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null

    constructor(){}

    constructor(name: String?, email: String?, uid: String?) {
        if (name != null) {
            this.name = name
        }
        if (email != null) {
            this.email = email
        }
        if (uid != null) {
            this.uid = uid
        }
    }
}