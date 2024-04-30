package chu.studio

import android.content.Context
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

@Dao
interface MyDao {

    @Query("SELECT * FROM my_rec")
    fun getAllRecords(): Flow<List<MyRecord>>

    @Query("SELECT * FROM my_rec WHERE id = :recordId")
    fun getRecordById(recordId: Long): Flow<MyRecord?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: MyRecord)

    @Update
    suspend fun updateRecord(record: MyRecord)

    @Delete
    suspend fun deleteRecord(record: MyRecord)

    @Query("SELECT * FROM my_rec")
    suspend fun getAllRecordsAsList(): List<MyRecord>

    @Transaction
    suspend fun exportToExcel(context: Context): String {
        val records = getAllRecordsAsList()
        return createExcelFile(context, records)
    }

    private suspend fun createExcelFile(context: Context, records: List<MyRecord>): String {
        val excelFile = File(context.filesDir, "records.xlsx")
        val workbook = XSSFWorkbook() // Создаем новую книгу Excel
        val sheet = workbook.createSheet("Records")

        val headerRow = sheet.createRow(0)
        headerRow.createCell(0).setCellValue("ID")
        headerRow.createCell(1).setCellValue("Дата")
        headerRow.createCell(2).setCellValue("ФИО")
        headerRow.createCell(3).setCellValue("Адрес")
        headerRow.createCell(4).setCellValue("Номер телефона")
        headerRow.createCell(5).setCellValue("Оборудование")
        headerRow.createCell(6).setCellValue("Заявка от")
        headerRow.createCell(7).setCellValue("Ремонт")
        headerRow.createCell(8).setCellValue("Запчасть")
        headerRow.createCell(9).setCellValue("Стоимость ремонта")
        headerRow.createCell(10).setCellValue("Стоимость запчасти")
        headerRow.createCell(11).setCellValue("Процент")
        headerRow.createCell(12).setCellValue("Итого")
        headerRow.createCell(13).setCellValue("Примечания")

        for ((rowIndex, record) in records.withIndex()) {
            val row = sheet.createRow(rowIndex + 1)
            row.createCell(0).setCellValue(record.id.toDouble())
            row.createCell(1).setCellValue(record.date)
            row.createCell(2).setCellValue(record.fullName)
            row.createCell(3).setCellValue(record.address)
            row.createCell(4).setCellValue(record.phoneNumber)
            row.createCell(5).setCellValue(record.equipment)
            row.createCell(6).setCellValue(record.requestFrom)
            row.createCell(7).setCellValue(record.repairPerformed)
            row.createCell(8).setCellValue(record.part)
            row.createCell(9).setCellValue(record.repairCost)
            row.createCell(10).setCellValue(record.partCost)
            row.createCell(11).setCellValue(record.percent)
            row.createCell(12).setCellValue(record.total)
            row.createCell(13).setCellValue(record.notes)
        }

        val outputStream = FileOutputStream(excelFile)
        workbook.write(outputStream)
        outputStream.close()

        return excelFile.absolutePath
    }
}