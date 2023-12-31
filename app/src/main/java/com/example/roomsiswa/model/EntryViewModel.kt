package com.example.roomsiswa.model

import android.app.AlarmManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.roomsiswa.data.Siswa
import com.example.roomsiswa.repositori.RepositoriSiswa
import java.util.NavigableMap

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel() {
    /**
     * Berisi status siswa saat ini
     */
    var  uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /* Fungsi untuk menvalidasi imput */
    private fun validasiInput (uiState: DetailSiswa = uiStateSiswa.detailSiswa) : Boolean{
        return  with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    /*Fungsi Untuk menyimpan data yang di-entry */
    suspend fun saveSiswa(){
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}

/**
 * Mewakili status Ui untuk siswa
 */

data class  UIStateSiswa(
    val  detailSiswa: DetailSiswa = DetailSiswa(),
    val  isEntryValid: Boolean = false
)

data class  DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = "",
)
/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis daanya */
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

fun  Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)