package com.kimphuong.manage.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "type_account")
data class TypeAccountEntity @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "type_account_id")
    var type_account_id: Int,
    @ColumnInfo(name = "type_account_name")
    var name : String,
    @ColumnInfo(name = "account_icon")
    var icon : Int
) : Parcelable {

    companion object {
        fun toUser(jsonData: String): TypeAccountEntity? {
            return Gson().fromJson(jsonData, TypeAccountEntity::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}