package com.kimphuong.manage.ui.main

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kimphuong.manage.base.BaseViewModel
import com.kimphuong.manage.base.parseToString
import com.kimphuong.manage.db.LocalDataSource
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.CategoryEntity
import com.kimphuong.manage.db.entity.TransactionEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import com.kimphuong.manage.utils.Constant.FORMAT_TIME_IN_FILE_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val context: Context,
    val localDataSource: LocalDataSource
) : BaseViewModel() {

    val stateShowProgressBar = MutableLiveData<Boolean>()

    private var listAccountEntity = listOf<AccountEntity>()
    private var listCategoryEntity = listOf<CategoryEntity>()
    private var listTransactionEntity = listOf<TransactionEntity>()
    private var listTypeAccountEntity = listOf<TypeAccountEntity>()
    val stateRestore = MutableLiveData<Boolean>()


    fun getAllTransactionDetail() = localDataSource.getAllTransactionDetail()

    fun getAllTransactionEntity() = localDataSource.getAllTransaction()

    fun getAllAccountEntity() = localDataSource.getAllAccount()

    fun getAllCategoryEntity() = localDataSource.getAllCategory()

    fun getAllTypeAccountEntity() = localDataSource.getAllTypeAccount()


    fun doSaveTxt(callback: (File?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            stateShowProgressBar.postValue(true)
            val path = context.getExternalFilesDir(null)?.path
            val fileName = getFileName()
            val file = File(path, fileName)
            if (file.exists()) file.delete()
            try {
                val fileOutputStream = FileOutputStream(file)
                val dataLines = mutableListOf<String>()

                Log.d("abcc", "doSaveTxt: ${getDataLine(listTransactionEntity)}")
                dataLines.add(getDataLine(listAccountEntity))
                dataLines.add(getDataLine(listTransactionEntity))

                val printWriter = PrintWriter(
                    OutputStreamWriter(fileOutputStream as OutputStream)
                )
                dataLines.forEach {
                    printWriter.println(it)
                }
                printWriter.flush()
                printWriter.close()
                callback.invoke(file)
            } catch (e: FileNotFoundException) {
                callback.invoke(null)
            }
            stateShowProgressBar.postValue(false)
        }
    }

    private fun getDataLine(listData: List<Any>): String {
        val gson = Gson()
        return gson.toJson(listData)
    }

    fun setListAccount(list: List<AccountEntity>) {
        listAccountEntity = list
    }

    fun setListTransaction(list: List<TransactionEntity>) {
        listTransactionEntity = list
    }

    fun setListCategory(list: List<CategoryEntity>) {
        listCategoryEntity = list
    }

    fun setListTypeAccount(list: List<TypeAccountEntity>) {
        listTypeAccountEntity = list
    }

    private fun getFileName(): String {
        return "backup_money_note_${
            Calendar.getInstance().parseToString(FORMAT_TIME_IN_FILE_NAME)
        }.txt"
    }

    fun readBackupFile(uri: Uri?, fileInputStream: InputStream?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                stateShowProgressBar.postValue(true)
                var listAccount = arrayListOf<AccountEntity>()
                var listTransaction = arrayListOf<TransactionEntity>()
                var listCategory = arrayListOf<CategoryEntity>()
                var listTypeAccountEntity = arrayListOf<TypeAccountEntity>()
                val input = uri?.let { context.contentResolver.openInputStream(it) }
                val bufferedReader = if (fileInputStream == null) {
                    BufferedReader(InputStreamReader(input))
                } else {
                    BufferedReader(InputStreamReader(fileInputStream))
                }
                var line: String? = bufferedReader.readLine()
                var count = 1
                while (line != null) {
                    Log.d("abcc", "readBackupFile:$count, $line")
                    when (count) {
//                        1 -> {
//                            val gson = Gson()
//                            val type = object : TypeToken<ArrayList<TypeAccountEntity?>?>() {}.type
//                            listTypeAccountEntity = gson.fromJson(line, type)
//                        }
                        1 -> {
                            val gson = Gson()
                            val type = object : TypeToken<ArrayList<AccountEntity?>?>() {}.type
                            listAccount = gson.fromJson(line, type)
                            Log.d("abcc", "listAcc: $listAccount")
                        }
                        2 -> {
                            val gson = Gson()
                            val type = object : TypeToken<ArrayList<TransactionEntity?>?>() {}.type
                            listTransaction = gson.fromJson(line, type)
                            Log.d("abcc", "listTransaction: $listTransaction")

                        }
                    }
                    count++
                    line = bufferedReader.readLine()
                }
                val isGetEnoughData = listTransaction.isNotEmpty()
                if (isGetEnoughData) {
                    insertAllData(listAccount, listTransaction)
                }
                stateRestore.postValue(isGetEnoughData)
            } catch (e: Exception) {
                stateRestore.postValue(false)
            }
            stateShowProgressBar.postValue(false)
        }
    }

    private fun insertAllData(
        listAccount: ArrayList<AccountEntity>,
        listTransaction: ArrayList<TransactionEntity>
    ) {
        viewModelScope.launch {
            localDataSource.deleteAllData()
            localDataSource.insertAllData(
                listAccount,
                listTransaction
            )
        }
    }

    fun deleteAllData() = viewModelScope.launch {
        localDataSource.deleteAllData()
    }
}