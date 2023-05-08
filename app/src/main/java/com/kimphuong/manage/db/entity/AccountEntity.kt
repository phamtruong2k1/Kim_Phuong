package com.kimphuong.manage.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "account")
data class AccountEntity @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "account_id")
    var account_id: Int = 0,
    @ColumnInfo(name = "type_account_id")
    var type_account_id: Int = 1,
    @ColumnInfo(name = "name")
    var name : String = "",
    @ColumnInfo(name = "amount")
    var amount : Float = 0f
) : Parcelable {

    companion object {
        fun toAccountEntity(jsonData: String): AccountEntity? {
            return Gson().fromJson(jsonData, AccountEntity::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}