package com.app.myapplication.activity.homepage.dto

class PullHomeModel(
    private var name: String,
    private var id: String,
    private var image: Int,
    private var price: Double,
    private var discount: Double,
    private var discount_price: Double,
    private var total: Int,
    private var decription: String,
    private var created_on: Long
) {

    fun getId(): String {
        return id
    }

    fun setId(ID: String) {
        id=ID
    }

    fun getName(): String {
        return name
    }

    fun setName(itemName: String) {
        this.name=itemName
    }

    fun getImage(): Int {
        return image
    }

    fun setImage(imgResID: Int) {
        this.image=imgResID
    }
    fun getPrice(): Double {
        return price
    }

    fun setPrice(Price: Double) {
        price=Price
    }
    fun getDiscount(): Double {
        return discount
    }

    fun setDiscount(Discount: Double) {
        discount=Discount
    }

    fun getDiscountPrice(): Double {
        return discount_price
    }

    fun setDiscountPrice(DiscountPrice: Double) {
        discount_price=DiscountPrice
    }
    fun getTotal(): Int {
        return total
    }

    fun setTotal(Total: Int) {
        this.total=Total
    }


    fun getDescription(): String {
        return decription
    }

    fun setDescription(Description: String) {
        this.decription=Description
    }

    fun getCreatedOn(): Long {
        return created_on
    }

    fun setCreatedOn(Create_on: Long) {
        this.created_on=Create_on
    }


}