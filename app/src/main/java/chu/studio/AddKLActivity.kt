
package chu.studio


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddKLActivity(
    viewModel: RecordViewModel = viewModel()
) {
    val formState = remember { mutableStateOf(RecordFormState()) }
    val context = LocalContext.current

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Добавить запись") }
                )
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    TextField(
                        value = formState.value.fullName,
                        onValueChange = { formState.value = formState.value.copy(fullName = it) },
                        label = { Text("ФИО") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = formState.value.address,
                        onValueChange = { formState.value = formState.value.copy(address = it) },
                        label = { Text("Адрес") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = formState.value.phoneNumber,
                        onValueChange = { formState.value = formState.value.copy(phoneNumber = it) },
                        label = { Text("Номер телефона") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = formState.value.equipment,
                        onValueChange = { formState.value = formState.value.copy(equipment = it) },
                        label = { Text("Оборудование") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = formState.value.requestFrom,
                        onValueChange = { formState.value = formState.value.copy(requestFrom = it) },
                        label = { Text("Запрос от") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = formState.value.repairPerformed,
                        onValueChange = { formState.value = formState.value.copy(repairPerformed = it) },
                        label = { Text("Выполненный ремонт") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = formState.value.part,
                        onValueChange = { formState.value = formState.value.copy(part = it) },
                        label = { Text("Запчасть") },
                        modifier = Modifier.fillMaxWidth()
                    )
                   
                    TextField(
                        value = formState.value.repairCost.toString(),
                        onValueChange = {
                            formState.value = formState.value.copy(repairCost = it.toDoubleOrNull() ?: 0.0)
                        },
                        label = { Text("Стоимость ремонта") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = formState.value.partCost.toString(),
                        onValueChange = {
                            formState.value = formState.value.copy(partCost = it.toDoubleOrNull() ?: 0.0)
                        },
                        label = { Text("Стоимость запчасти") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = formState.value.percent.toString(),
                        onValueChange = {
                            formState.value = formState.value.copy(percent = it.toDoubleOrNull() ?: 0.0)
                        },
                        label = { Text("Процент") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = formState.value.notes,
                        onValueChange = { formState.value = formState.value.copy(notes = it) },
                        label = { Text("Примечания") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            if (formState.value.isValid) {
                                viewModel.insertRecord(formState.value)
                                Toast.makeText(context, "Запись сохранена", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Пожалуйста, заполните все обязательные поля", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = formState.value.isValid
                    ) {
                        Text(text = "Сохранить")
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}