package screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.benasher44.uuid.Uuid
import kotlinx.datetime.*
import org.gauss.data.model.Note
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.parameter.ParametersHolder
import ui.onKeyUp

class NoteDetailScreen(private val id: Uuid) : Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val screenModel = getScreenModel<NoteDetailScreenModel>(parameters = { ParametersHolder(mutableListOf(id)) })
        val state by screenModel.state.collectAsState()

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopAppBar(
                title = { Text("Edit todo") },
                navigationIcon = {
                    IconButton(onClick = { navigator.pop() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )

            TextField(
                value = state.note?.text ?: "",
                modifier = Modifier.weight(1F).fillMaxWidth().padding(8.dp),
                label = { Text("Todo text") },
                onValueChange = screenModel::editNote
            )

            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Completed")

                Spacer(modifier = Modifier.width(8.dp))

                Checkbox(
                    checked = state.note?.isDone ?: false,
                    onCheckedChange = screenModel::completeNote
                )
            }
        }
    }


    @Composable
    private fun TodoList(
        items: List<Note>,
        onItemClicked: ((id: Uuid) -> Unit)?,
        onDoneChanged: ((id: Uuid, isDone: Boolean) -> Unit)?,
        onDeleteItemClicked: (id: Uuid) -> Unit
    ) {
        Box {
            val listState = rememberLazyListState()

            LazyColumn(state = listState) {
                items(items) {
                    Item(
                        item = it,
                        onItemClicked = onItemClicked,
                        onDoneChanged = onDoneChanged,
                        onDeleteItemClicked = onDeleteItemClicked
                    )

                    Divider()
                }
            }

//            VerticalScrollbar(
//                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
//                adapter = rememberScrollbarAdapter(
//                    scrollState = listState,
//                    itemCount = items.size,
//                    averageItemSize = 37.dp
//                )
//            )
        }
    }

    @Composable
    private fun Item(
        item: Note,
        onItemClicked: ((id: Uuid) -> Unit)?,
        onDoneChanged: ((id: Uuid, isDone: Boolean) -> Unit)?,
        onDeleteItemClicked: (id: Uuid) -> Unit
    ) {
        Row(modifier = Modifier.clickable(onClick = { onItemClicked?.invoke(item.id) })) {
            Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = item.isDone,
                modifier = Modifier.align(Alignment.CenterVertically),
                onCheckedChange = { onDoneChanged?.invoke(item.id, it) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = AnnotatedString(item.text),
                modifier = Modifier.weight(1F).align(Alignment.CenterVertically),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = { onDeleteItemClicked(item.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun TodoInput(
        text: String,
        onTextChanged: (String) -> Unit,
        onAddClicked: () -> Unit
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            OutlinedTextField(
                value = text,
                modifier = Modifier.weight(weight = 1F).onKeyEvent(onKeyUp(Key.Enter, onAddClicked)),
                onValueChange = onTextChanged,
                label = { Text(text = "Add a todo") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onAddClicked) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    }

}
