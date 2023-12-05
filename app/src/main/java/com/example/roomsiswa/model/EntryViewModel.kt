package com.example.roomsiswa.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.roomsiswa.repositori.RepositoriSiswa

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel() {
    /**
     * Berisi status siswa saat ini
     */
    var  uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /* Fungsi untuk menvalidasi imput */
    private fun validasiInput (uiState: DetaixlSiswa = uiStateSiswa.detailSiswa) : Boolean{
        return  with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }
}