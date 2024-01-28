package tech.ericwathome.counterapphutsyexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import tech.ericwathome.counterapphutsyexample.presentation.counter_screen.CounterScreen
import tech.ericwathome.counterapphutsyexample.presentation.counter_screen.CounterViewModel
import tech.ericwathome.counterapphutsyexample.ui.theme.CounterAppHutsyExampleTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<CounterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterAppHutsyExampleTheme {
                CounterScreen(viewModel = viewModel)
            }
        }
    }
}