package org.d3if6706202072.assessment1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if6706202072.assessment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { hitungWR() }
        binding.buttonReset.setOnClickListener { reset() }

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
            Toast.makeText(this, R.string.eMatch, Toast.LENGTH_LONG).show()
            return
        }else if(tMatch.toFloat() <= 0){
            Toast.makeText(this, R.string.eInput, Toast.LENGTH_LONG).show()
            return
        }


        val tWr = binding.nuwr.text.toString()
        if (TextUtils.isEmpty(tWr)){
            Toast.makeText(this, R.string.eWr, Toast.LENGTH_LONG).show()
            return
        }else if(tWr.toFloat() <= 0){
            Toast.makeText(this, R.string.eInput, Toast.LENGTH_LONG).show()
            return
        }

        val wrReq = binding.nutowr.text.toString()
        if (TextUtils.isEmpty(wrReq)){
            Toast.makeText(this, R.string.eWrReq, Toast.LENGTH_LONG).show()
            return
        }else if(wrReq.toFloat() >= 100 ){
            Toast.makeText(this, R.string.invalidWrReq, Toast.LENGTH_LONG).show()
            return
        }else if(wrReq.toFloat() <= 0){
            Toast.makeText(this, R.string.eInput, Toast.LENGTH_LONG).show()
            return
        }

        val tWin = tMatch.toFloat() * (tWr.toFloat() / 100);
        val tLose = tMatch.toFloat() - tWin;
        val sisaWr = 100 - wrReq.toFloat();
        val wrResult = 100 / sisaWr;
        val seratusPersen = tLose * wrResult;
        val final = (seratusPersen - tMatch.toFloat());

        val display = final.toInt().toString();

        binding.hasilId.text = getString(R.string.hasil, display)
    }




}