
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.KoinApplication
import screens.list.NoteListScreen
import cafe.adriel.voyager.transitions.SlideTransition
import di.utilModule

@Composable
fun App() {
    MaterialTheme {
        Navigator(
            screen = NoteListScreen(),
            onBackPressed = { currentScreen ->
                println("Navigator: Pop screen #${currentScreen}")
                true
            }
        ) { navigator ->
            SlideTransition(navigator)
        }
    }
}

