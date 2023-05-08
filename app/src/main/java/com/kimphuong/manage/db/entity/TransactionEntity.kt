package com.kimphuong.manage.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "transaction")
data class TransactionEntity @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    var transaction_id: Int,
    @ColumnInfo(name = "category_id")
    var category_id: Int,
    @ColumnInfo(name = "account_id")
    var account_id: Int,
    @ColumnInfo(name = "day")
    var day : Int,
    @ColumnInfo(name = "month")
    var month : Int,
    @ColumnInfo(name = "year")
    var year : Int,
    @ColumnInfo(name = "hour")
    var hour : Int,
    @ColumnInfo(name = "min")
    var min : Int,
    @ColumnInfo(name = "amount")
    var amount : Float,
    @ColumnInfo(name = "type")
    var type : Boolean,
    @ColumnInfo(name = "note")
    var note : String,
) : Parcelable {

    companion object {
        fun toUser(jsonData: String): TransactionEntity? {
            return Gson().fromJson(jsonData, TransactionEntity::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}