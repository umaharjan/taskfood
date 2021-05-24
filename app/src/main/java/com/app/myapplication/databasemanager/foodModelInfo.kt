package com.app.myapplication.databasemanager

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

 open class foodModelInfo(
    @PrimaryKey public open var id: String = "",
    public open var name: String = "",
    public open var price: Double? = null ,
            public open var discount: Double? = null,
    public open var discount_price: Double? = null,
    public open var image: Int? = null,
    public open var total: Int? = null,
    public open var decription: String? = "",
    public open var created_on: Long? = null

) : RealmObject(){

}


