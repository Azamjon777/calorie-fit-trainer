package goope.rimu.caloriefittrainer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import goope.rimu.caloriefittrainer.R
import goope.rimu.caloriefittrainer.databinding.ListItemTrainingBinding
import goope.rimu.caloriefittrainer.model.TrainingModel

class TrainingAdapter(
    private val trainingList: List<TrainingModel>,
    private val itemClickListener: OnTrainingItemClickListener,
    private val context: Context
) : RecyclerView.Adapter<TrainingAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListItemTrainingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(training: TrainingModel) {
            binding.trainingTitle.text = training.title
            binding.trainingLessons.text = context.getString(R.string.lessons) + training.lessons

            Glide.with(binding.root)
                .load(training.imageUrl)
                .into(binding.trainingImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemTrainingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val training = trainingList[position]
        holder.bind(training)

        holder.itemView.setOnClickListener {
            itemClickListener.onTrainingItemClick(training)
        }
    }

    override fun getItemCount(): Int {
        return trainingList.size
    }

    interface OnTrainingItemClickListener {
        fun onTrainingItemClick(training: TrainingModel)
    }
}
