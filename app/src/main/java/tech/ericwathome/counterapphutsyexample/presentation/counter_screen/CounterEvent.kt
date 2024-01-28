package tech.ericwathome.counterapphutsyexample.presentation.counter_screen

sealed class CounterEvent {
    data object OnClickAdd : CounterEvent()
    data object OnClickSubtract : CounterEvent()
    data class OnEnterValue(val value: String) : CounterEvent()
    data object OnClickCountDown : CounterEvent()
    data object OnClickReset : CounterEvent()
}