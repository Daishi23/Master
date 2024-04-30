package chu.studio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class RecordViewModel : ViewModel() {
    // Здесь должна быть реализация ViewModel

    var searchQuery by mutableStateOf("")
        private set

    var isFilterShown by mutableStateOf(false)
        private set

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun toggleFilterShown() {
        isFilterShown = !isFilterShown
    }
}

class KLActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KLScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KLScreen(
    viewModel: RecordViewModel = viewModel()
) {
    val records = remember { /* Получение списка записей */ }
    val context = LocalContext.current
    val searchQuery by viewModel.searchQuery
    val isFilterShown by viewModel.isFilterShown

    if (isFilterShown) {
        FilterDialog(
            onDismissRequest = { viewModel.toggleFilterShown() },
            onApplyFilter = { /* Обработка применения фильтра */ }
        )
    }

    if (searchQuery.isNotBlank()) {
        SearchDialog(
            searchQuery = searchQuery,
            onDismissRequest = { viewModel.updateSearchQuery("") },
            onSearchQueryChange = viewModel::updateSearchQuery,
            onSearch = { /* Обработка поиска */ }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Записи") },
                actions = {
                    IconButton(onClick = {
                        // Показать диалоговое окно поиска
                        viewModel.updateSearchQuery("")
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Поиск")
                    }
                    IconButton(onClick = { viewModel.toggleFilterShown() }) {
                        Icon(Icons.Filled.Filter, contentDescription = "Фильтр")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // context.startActivity(Intent(context, AddKLActivity::class.java))
                },
               
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Добавить")
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            val filteredRecords = records.filter { record ->
                // Применение фильтра и поиска
                record.fullName.contains(searchQuery, ignoreCase = true) && true
                /* Условия фильтрации */
            }

            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredRecords) { record ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(2.dp, Color.Green),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "${record.date} - ${record.fullName}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = record.address)
                            Text(text = record.repairPerformed)
                        }
                    }
                }
            }
        }
    }
}

// Здесь должны быть реализованы композабельные функции FilterDialog и SearchDialog