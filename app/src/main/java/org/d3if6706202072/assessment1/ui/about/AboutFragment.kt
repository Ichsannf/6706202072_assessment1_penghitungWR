package org.d3if6706202072.assessment1.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import org.d3if6706202072.assessment1.R
import org.d3if6706202072.assessment1.databinding.FragmentAboutBinding
import org.d3if6706202072.assessment1.model.TentangAplikasi
import org.d3if6706202072.assessment1.network.ApiStatus
import org.d3if6706202072.assessment1.network.WRApi


class AboutFragment : Fragment(){
    private val viewModel: AboutViewModel by lazy {
        ViewModelProvider(this)[AboutViewModel::class.java]
    }
    private lateinit var binding: FragmentAboutBinding
    //private lateinit var myAdapter: HistoriAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStatus().observe(viewLifecycleOwner){
            updateProgress(it)
        }
        viewModel.getTentangAplikasiWR().observe(viewLifecycleOwner){
            tentangAplikasiWRICAN(it)
        }
        Glide.with(binding.imageView)
            .load(WRApi.getImageUrl())
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.imageView)
    }
    private fun tentangAplikasiWRICAN(tentangAplikasiWRICAN: TentangAplikasi?) {
        if (tentangAplikasiWRICAN !=null) {
            binding.aboutWR.text = tentangAplikasiWRICAN.tentangAplikasi
        }
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}
