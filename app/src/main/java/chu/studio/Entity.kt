package chu.studio;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_rec")
data class MyRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val fullName: String,
    val address: String,
    val phoneNumber: String,
    val equipment: String,
    val requestFrom: String,
    val repairPerformed: String,
    val part: String,
    val repairCost: Double,
    val partCost: Double,
    val percent: Double,
    val total: Double,
    val notes: String
)