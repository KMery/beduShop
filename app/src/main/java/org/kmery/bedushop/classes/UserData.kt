package org.kmery.bedushop.classes

data class UserData(val id: Int = 1,
                    val email: String = "user@mail.com",
                    val first_name: String = "Jane",
                    val last_name: String = "Doe",
                    val avatar: String = "https://reqres.in/img/faces/2-image.jpg") {}
/*
"data": {
    "id": 2,
    "email": "janet.weaver@reqres.in",
    "first_name": "Janet",
    "last_name": "Weaver",
    "avatar": "https://reqres.in/img/faces/2-image.jpg"
},*/
