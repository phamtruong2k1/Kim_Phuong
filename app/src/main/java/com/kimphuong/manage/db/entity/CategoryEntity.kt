package com.kimphuong.manage.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "category")
data class CategoryEntity @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    var category_id: Int,
    @ColumnInfo(name = "type")
    var type: Boolean,
    @ColumnInfo(name = "category_name")
    var name: String,
    @ColumnInfo(name = "category_icon")
    var icon: Int
) : Parcelable {

    companion object {
        fun toCategory(jsonData: String): CategoryEntity? {
            return Gson().fromJson(jsonData, CategoryEntity::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}