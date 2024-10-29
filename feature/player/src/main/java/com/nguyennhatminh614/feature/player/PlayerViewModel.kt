package com.nguyennhatminh614.feature.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

}