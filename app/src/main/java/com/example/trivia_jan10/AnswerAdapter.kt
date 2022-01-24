package com.example.trivia_jan10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trivia_jan10.databinding.ItemAnswerBinding

class AnswerAdapter(
    private val listener: (answer: Answer) -> Unit
) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    var listOfAnswers = listOf<Answer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            ItemAnswerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).also { viewHolder ->
            viewHolder.itemView.setOnClickListener {
                listener.invoke(
                    listOfAnswers[viewHolder.adapterPosition]
                )
            }
        }
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(listOfAnswers[position])
    }

    override fun getItemCount(): Int {
        return listOfAnswers.size
    }

    fun submitAnswers(answers: List<Answer>) {
        listOfAnswers = answers
        notifyDataSetChanged()
    }

    class AnswerViewHolder(
        private val binding: ItemAnswerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(answer: Answer) = with(binding) {
            answerTv.text = answer.answer
        }
    }
}