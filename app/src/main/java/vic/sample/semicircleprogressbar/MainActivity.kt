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

        GlobalScope.launch(Dispatchers.Main) {
            for (i in 0..100) {
                delay(20)
                binding.SemiCircleProgressBarTopClockTrue.setProgress(i)
                binding.SemiCircleProgressBarTopClockFalse.setProgress(i)
                binding.SemiCircleProgressBarBottomClockTrue.setProgress(i)
                binding.SemiCircleProgressBarBottomClockFalse.setProgress(i)
                binding.SemiCircleProgressBarLeftClockTrue.setProgress(i)
                binding.SemiCircleProgressBarLeftClockFalse.setProgress(i)
                binding.SemiCircleProgressBarRightClockTrue.setProgress(i)
                binding.SemiCircleProgressBarRightClockFalse.setProgress(i)
            }
            binding.seekbar.visibility = View.VISIBLE
            binding.seekbar.progress = 100
        }


        binding.seekbar.onProgressChanged { seekBar, progress, fromUser ->
            val i = seekBar?.progress
            i?.let {
                binding.SemiCircleProgressBarTopClockTrue.setProgress(it)
                binding.SemiCircleProgressBarTopClockFalse.setProgress(it)
                binding.SemiCircleProgressBarBottomClockTrue.setProgress(it)
                binding.SemiCircleProgressBarBottomClockFalse.setProgress(it)
                binding.SemiCircleProgressBarLeftClockTrue.setProgress(it)
                binding.SemiCircleProgressBarLeftClockFalse.setProgress(it)
                binding.SemiCircleProgressBarRightClockTrue.setProgress(it)
                binding.SemiCircleProgressBarRightClockFalse.setProgress(it)
            }
        }
    }
}