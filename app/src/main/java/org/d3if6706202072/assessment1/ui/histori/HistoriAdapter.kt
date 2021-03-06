package org.d3if6706202072.assessment1.ui.histori

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if6706202072.assessment1.R
import org.d3if6706202072.assessment1.databinding.ItemHistoriBinding
import org.d3if6706202072.assessment1.db.WrEntity
import org.d3if6706202072.assessment1.model.hitungWr
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter (private val viewModel: HistoriViewModel,private val context: Context):
    ListAdapter<WrEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<WrEntity>() {
                override fun areItemsTheSame(
                    oldData: WrEntity, newData: WrEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: WrEntity, newData: WrEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding,viewModel,context)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding,
        private val viewModel: HistoriViewModel,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: WrEntity) = with(binding) {
            val hasilWr = item.hitungWr()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            wrTextView.text = root.context.getString(
                R.string.hasil_x,
                item.tMatch.toString(),
                hasilWr.wr.toString()
            )
            dataTextView.text = root.context.getString(R.string.data_x, item.tWr.toString())

            deleteButton.setOnClickListener {
                MaterialAlertDialogBuilder(context)
                    .setMessage(R.string.konfirmasi_hapus)
                    .setPositiveButton(R.string.hapus) { _, _ ->
                        viewModel.delete(item)
                    }
                    .setNegativeButton(R.string.batal) { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()

            }
        }
    }
}

