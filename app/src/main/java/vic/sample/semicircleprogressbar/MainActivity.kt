package vic.sample.semicircleprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.coroutines.*
import vic.sample.semicircleprogressbar.custom.onProgressChanged
import vic.sample.semicircleprogressbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SemiCircleProgressBar.setProgressColor(ContextCompat.getColor(this, R.color.black))
        binding.SemiCircleProgressBar.setProgressHolderColor(
            ContextCompat.getColor(this, R.color.teal_200)
        )
        binding.SemiCircleProgressBar.setProgressWidth(24)
        binding.SemiCircleProgressBar.setProgressHolderWidth(36)

        GlobalScope.launch(Dispatchers.Main) {
            for (i in 0..100) {
                delay(20)
                binding.SemiCircleProgressBar.setProgress(i)
            }
            binding.seekbar.visibility = View.VISIBLE
            binding.seekbar.progress = 100
        }


        binding.seekbar.onProgressChanged { seekBar, progress, fromUser ->
            val i = seekBar?.progress
            i?.let {
                binding.SemiCircleProgressBar.setProgress(it)
            }
        }
    }
}