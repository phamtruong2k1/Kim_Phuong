package com.kimphuong.manage.db.entity

data class TransactionDetail(var transaction_id:Int, var day:Int, var month:Int, var year:Int, var amount:Float, var type:Boolean, var note: String, var account_name:String, var category_name:String, var category_icon:Int)