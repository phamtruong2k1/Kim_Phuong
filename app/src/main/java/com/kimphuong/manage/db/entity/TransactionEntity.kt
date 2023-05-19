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
    var transaction_id: Int = 0,
    @ColumnInfo(name = "category_id")
    var category_id: Int = 0,
    @ColumnInfo(name = "account_id")
    var account_id: Int = 0,
    @ColumnInfo(name = "day")
    var day : Int = 0,
    @ColumnInfo(name = "month")
    var month : Int = 0,
    @ColumnInfo(name = "year")
    var year : Int = 0,
    @ColumnInfo(name = "amount")
    var amount : Long = 0,
    @ColumnInfo(name = "type")
    var type : Boolean = true,
    @ColumnInfo(name = "note")
    var note : String = "",
) : Parcelable {

    companion object {
        fun toTransaction(jsonData: String): TransactionEntity? {
            return Gson().fromJson(jsonData, TransactionEntity::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}