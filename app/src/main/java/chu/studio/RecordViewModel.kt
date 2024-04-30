package chu.studio

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.max // Импортируем функцию max из kotlin.math

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val myRecordRepository: MyRecordRepository
) : ViewModel() {

    private val _records = MutableStateFlow<List<MyRecord>>(emptyList())
    val records = _records.asStateFlow()

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery.value

    init {
        loadRecords()
    }

    fun insertRecord(formState: RecordFormState) {
        viewModelScope.launch {
            // Проверяем, что formState является валидной перед вставкой
            if (formState.isValid) {
                myRecordRepository.insert(formState.toMyRecord())
                loadRecords()
            }
        }
    }

    private fun loadRecords() {
        viewModelScope.launch {
            _records.value = myRecordRepository.getAllRecords()
        }
    }
}

data class RecordFormState(
    val date: String = LocalDate.now().format(DateTimeFormatter.ISO_DATE),
    val fullName: String = "",
    val address: String = "",
    val phoneNumber: String = "",
    val equipment: String = "",
    val requestFrom: String = "",
    val repairPerformed: String = "",
    val part: String = "",
    val repairCost: Double = 0.0,
    val partCost: Double = 0.0,
    val percent: Double = 0.0,
    val total: Double = 0.0, // Рассчитывается на основе других полей
    val notes: String = ""
) {
    val isValid: Boolean
        get() = fullName.isNotBlank() && address.isNotBlank() && phoneNumber.isNotBlank() && repairCost >= 0 && partCost >= 0 && percent >= 0 /* другие проверки валидности */

    fun toMyRecord(): MyRecord {
        // Используем функцию max для получения максимального значения между 0 и percent
        val validPercent = max(0.0, percent)
        val totalCost = (repairCost + partCost).let { cost ->
            cost + cost * validPercent / 100
        }
        return MyRecord(
            date = date,
            fullName = fullName,
            address = address,
            phoneNumber = phoneNumber,
            equipment = equipment,
            requestFrom = requestFrom,
            repairPerformed = repairPerformed,
            part = part,
            repairCost = repairCost,
            partCost = partCost,
            percent = validPercent, // Используем validPercent вместо percent
            total = totalCost,
            notes = notes
        )
    }
}