package org.d3if6706202072.assessment1.ui
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if6706202072.assessment1.R
import org.d3if6706202072.assessment1.databinding.FragmentHitungBinding
import org.d3if6706202072.assessment1.model.HasilWr

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungWR() }
        binding.buttonReset.setOnClickListener { reset() }
        binding.saranButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_hitungFragment_to_saranFragment)
        }
        viewModel.getHasilWr().observe(requireActivity(), { showResult(it) })

    }


    private fun reset(){
        binding.numatch.setText("")
        binding.nuwr.setText("")
        binding.nutowr.setText("")
        binding.hasilId.text= ""
    }

    private fun hitungWR(){
        val tMatch = binding.numatch.text.toString()
        if (TextUtils.isEmpty(tMatch)){
            Toast.makeText(context, R.string.eMatch, Toast.LENGTH_LONG).show()
            return
        }else if(tMatch.toFloat() <= 0){
            Toast.makeText(context, R.string.eInput, Toast.LENGTH_LONG).show()
            return
        }


        val tWr = binding.nuwr.text.toString()
        if (TextUtils.isEmpty(tWr)){
            Toast.makeText(context, R.string.eWr, Toast.LENGTH_LONG).show()
            return
        }else if(tWr.toFloat() <= 0){
            Toast.makeText(context, R.string.eInput, Toast.LENGTH_LONG).show()
            return
        }

        val wrReq = binding.nutowr.text.toString()
        if (TextUtils.isEmpty(wrReq)){
            Toast.makeText(context, R.string.eWrReq, Toast.LENGTH_LONG).show()
            return
        }else if(wrReq.toFloat() >= 100 ){
            Toast.makeText(context, R.string.invalidWrReq, Toast.LENGTH_LONG).show()
            return
        }else if(wrReq.toFloat() <= 0){
            Toast.makeText(context, R.string.eInput, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungWR(tMatch.toFloat(),tWr.toFloat(),wrReq.toFloat())
    }
    private fun showResult(result: HasilWr?) {
        if (result == null) return
        binding.hasilId.text = getString(R.string.hasil, result.wr.toString())
        binding.saranButton.visibility = View.VISIBLE
    }

    private fun hitungWR(tMatch: Float, tWr: Float, wrReq: Float): HasilWr {
        val tWin = tMatch * (tWr / 100)
        val tLose = tMatch - tWin
        val sisaWr = 100 - wrReq
        val wrResult = 100 / sisaWr
        val seratusPersen = tLose * wrResult
        val final = (seratusPersen - tMatch)
        return HasilWr(final)
    }
}