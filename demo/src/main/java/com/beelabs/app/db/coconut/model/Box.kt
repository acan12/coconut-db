package com.beelabs.app.db.coconut.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Box : RealmObject(){
    @PrimaryKey
    var id : Long = 0
    var name : String? = null
    var desc : String? = null
}