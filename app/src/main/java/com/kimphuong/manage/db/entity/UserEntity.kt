package com.kimphuong.manage.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserEntity @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var idLocal: Int,
    @ColumnInfo(name = "path")
    var path : String ,
    @ColumnInfo(name = "button")
    var button : Int ,
    @ColumnInfo(name = "name")
    var name: String
) : Parcelable {

    companion object {
        fun toUser(jsonData: String): UserEntity? {
            return Gson().fromJson(jsonData, UserEntity::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}