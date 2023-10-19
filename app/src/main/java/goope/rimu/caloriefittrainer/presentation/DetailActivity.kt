package goope.rimu.caloriefittrainer.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import goope.rimu.caloriefittrainer.R
import goope.rimu.caloriefittrainer.databinding.ActivityDetailBinding
import goope.rimu.caloriefittrainer.model.TrainingModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trainingModel = intent.getSerializableExtra("trainingModel") as? TrainingModel

        if (trainingModel != null) {
            clicks(trainingModel)
        }
    }

    private fun clicks(trainingModel: TrainingModel) {
        binding.btnViewProgram.setOnClickListener {
            val url = trainingModel.urlReference

            if (url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.video_is_not_available_now),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        Glide.with(this)
            .load(trainingModel.imageUrl)
            .into(binding.lessonImg)

        binding.tvCalorie.text = trainingModel.kcal
        binding.tvDuration.text = trainingModel.timeDuration
        binding.backImg.setOnClickListener {
            finish()
        }
    }
}