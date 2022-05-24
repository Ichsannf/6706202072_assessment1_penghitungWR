package org.d3if6706202072.assessment1.ui
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungWR() }
        binding.buttonReset.setOnClickListener { reset() }
        binding.saranButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_hitungFragment_to_saranFragment)
        }
        viewModel.getHasilWr().observe(requireActivity(), { showResult(it) })
        binding.shareButton.setOnClickListener { shareData() }


    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
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
        binding.buttonGroup.visibility = View.VISIBLE
    }
    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.numatch.text,
            binding.nutowr.text,
            binding.nuwr.text,
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}