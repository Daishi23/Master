package chu.studio.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import chu.studio.MyDao
import chu.studio.MyRecord

class MyRecordRepository(private val myDao: MyDao) {

    fun getAllRecords(): Flow<List<MyRecord>> {
        return myDao.getAllRecords()
    }

    fun getRecordById(recordId: Long): Flow<MyRecord?> {
        return myDao.getRecordById(recordId)
    }

    suspend fun insertRecord(record: MyRecord) {
        myDao.insertRecord(record)
    }

    suspend fun updateRecord(record: MyRecord) {
        myDao.updateRecord(record)
    }

    suspend fun deleteRecord(record: MyRecord) {
        myDao.deleteRecord(record)
    }

    suspend fun exportToExcel(context: Context): String {
        return myDao.exportToExcel(context)
    }
}