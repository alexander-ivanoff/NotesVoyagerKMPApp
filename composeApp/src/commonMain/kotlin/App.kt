
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.KoinApplication
import screens.list.NoteListScreen
import screens.screenModule
import cafe.adriel.voyager.transitions.SlideTransition
import org.gauss.data.dataModule
import screens.utilModule

@Composable
fun App() {
    KoinApplication(
        application = {
            modules(dataModule(), utilModule(), screenModule())
        }
    ) {
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
}

