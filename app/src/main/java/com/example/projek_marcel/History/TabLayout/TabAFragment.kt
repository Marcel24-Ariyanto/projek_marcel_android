package com.example.projek_marcel.History.TabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projek_marcel.History.HistoryModel
import com.example.projek_marcel.R
import com.example.projek_marcel.databinding.FragmentTabABinding

class TabAFragment : Fragment() {
    val daftarGambar = listOf(
        R.drawable.emoji_bahagia,
        R.drawable.emoji_sedih,
        R.drawable.emoji_biasa,
        R.drawable.emoji_lelah,
        R.drawable.emoji_marah
    )
    private val historyList = listOf(
        HistoryModel("Senang", "Saya dapat kue tadi sama tetangga", daftarGambar[0]),
        HistoryModel("Lelah", "Harini saya banyak sekali kegiatan", daftarGambar[3]),
        HistoryModel("Lelah", "Harini tugas saya banyak sekali", daftarGambar[3]),
        HistoryModel("Marah", "Teman kelompokku gada kerja, dan aku MARAHH!!!!", daftarGambar[4]),
        HistoryModel("Senang", "Akhirnya kode aplikasiku berhasil jalan tanpa error", daftarGambar[0]),
        HistoryModel("Biasa", "Hari ini kuliah berjalan seperti biasa saja", daftarGambar[2]),
        HistoryModel("Sedih", "Kucing peliharaanku hilang sejak tadi pagi", daftarGambar[1]),
        HistoryModel("Senang", "Dapat nilai A di mata kuliah pemrograman mobile", daftarGambar[0]),
        HistoryModel("Lelah", "Begadang semalaman demi menyelesaikan revisi laporan", daftarGambar[3]),
        HistoryModel("Marah", "Macet parah di jalan padahal sudah berangkat pagi", daftarGambar[4]),
        HistoryModel("Biasa", "Cuma di kosan seharian sambil nonton video", daftarGambar[2]),
        HistoryModel("Senang", "Traktiran makan siang gratis dari teman lama", daftarGambar[0]),
        HistoryModel("Sedih", "Hujan deras bikin rencana jalan-jalan jadi batal", daftarGambar[1]),
        HistoryModel("Lelah", "Praktikum hari ini padat banget dari pagi sampai sore", daftarGambar[3]),
        HistoryModel("Marah", "Laptop tiba-tiba restart sendiri pas belum sempat save", daftarGambar[4]),
        HistoryModel("Senang", "Barang yang dipesan online akhirnya sampai lebih cepat", daftarGambar[0]),
        HistoryModel("Biasa", "Sarapan nasi goreng di warung depan kampus", daftarGambar[2]),
        HistoryModel("Sedih", "Dompet ketinggalan pas sudah sampai di kasir", daftarGambar[1]),
        HistoryModel("Lelah", "Habis olahraga lari keliling lapangan 5 putaran", daftarGambar[3]),
        HistoryModel("Senang", "Ketemu teman lama yang sudah bertahun-tahun tidak jumpa", daftarGambar[0]),
        HistoryModel("Marah", "Janji ketemuan jam 2 tapi baru datang jam 4", daftarGambar[4]),
        HistoryModel("Biasa", "Cuaca hari ini mendung tapi tidak turun hujan", daftarGambar[2]),
        HistoryModel("Sedih", "Film yang baru kutonton ending-nya sedih banget", daftarGambar[1]),
        HistoryModel("Lelah", "Seharian bersih-bersih kamar kos yang berantakan", daftarGambar[3]),
        HistoryModel("Senang", "Dapat diskon besar pas belanja kebutuhan bulanan", daftarGambar[0]),
        HistoryModel("Biasa", "Menunggu jadwal kelas berikutnya di perpustakaan", daftarGambar[2]),
        HistoryModel("Marah", "Koneksi internet putus-putus pas lagi presentasi online", daftarGambar[4]),
        HistoryModel("Sedih", "HP jatuh dan layarnya sedikit retak di bagian pojok", daftarGambar[1]),
        HistoryModel("Senang", "Berhasil bangun pagi dan olahraga sebelum jam 7", daftarGambar[0]),
        HistoryModel("Lelah", "Antre panjang di bank hampir dua jam lebih", daftarGambar[3]),
        HistoryModel("Biasa", "Makan siang dengan menu yang sama seperti kemarin", daftarGambar[2]),
        HistoryModel("Marah", "Makanan yang kupesan lewat aplikasi salah kirim menu", daftarGambar[4]),
        HistoryModel("Senang", "Libur panjang akhir pekan sudah di depan mata", daftarGambar[0]),
        HistoryModel("Sedih", "Teman dekat pindah kota karena urusan pekerjaan", daftarGambar[1]),
        HistoryModel("Lelah", "Rapat organisasi kampus baru selesai jam 10 malam", daftarGambar[3]),
        HistoryModel("Biasa", "Membaca buku novel sambil minum teh hangat", daftarGambar[2]),
        HistoryModel("Senang", "Masakan pertamaku ternyata rasanya enak banget", daftarGambar[0]),
        HistoryModel("Marah", "Ada yang menyerobot antrean tanpa merasa bersalah", daftarGambar[4]),
        HistoryModel("Sedih", "Lupa bawa payung dan kehujanan di tengah jalan", daftarGambar[1]),
        HistoryModel("Lelah", "Perjalanan pulang kampung terasa sangat jauh hari ini", daftarGambar[3]),
        HistoryModel("Biasa", "Mendengarkan playlist lagu favorit di kamar", daftarGambar[2]),
        HistoryModel("Senang", "Dapat pujian dari dosen atas hasil presentasi tadi", daftarGambar[0]),
        HistoryModel("Marah", "Suara bising dari luar bikin susah konsentrasi belajar", daftarGambar[4]),
        HistoryModel("Sedih", "Tanaman hias di teras layu karena lupa disiram", daftarGambar[1]),
        HistoryModel("Lelah", "Mata terasa perih karena terlalu lama menatap layar", daftarGambar[3]),
        HistoryModel("Biasa", "Jalan santai sore hari di sekitar komplek", daftarGambar[2]),
        HistoryModel("Senang", "Bisa tidur nyenyak setelah beberapa hari kurang tidur", daftarGambar[0]),
        HistoryModel("Marah", "Charger HP rusak padahal baru beli bulan lalu", daftarGambar[4]),
        HistoryModel("Sedih", "Gagal mendapatkan tiket konser artis favorit", daftarGambar[1]),
        HistoryModel("Senang", "Akhirnya semua target tugas minggu ini selesai tepat waktu", daftarGambar[0])
    )

    private var _binding: FragmentTabABinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val productAdapter = ItemHistoryAdapter(historyList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Anda memilih ${selectedItem.senderName}",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.rvProducts.apply {
// Mode Grid 2 kolom
            layoutManager = GridLayoutManager(requireContext(), 1)
// Jika ingin mode Linear:
// layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
    }

}