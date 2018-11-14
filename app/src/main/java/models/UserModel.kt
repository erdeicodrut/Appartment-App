package models



class User {

    lateinit var username: String
    lateinit var password: String


    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

}