package com.example.quizee.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizee.R
import com.example.quizee.models.ResultAnswer

class ResultAnswerAdapter(val context: Context, val answerList: List<ResultAnswer>) :
    RecyclerView.Adapter<ResultAnswerAdapter.ResultAnswerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAnswerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_result_answer_item, parent, false)
        return ResultAnswerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultAnswerViewHolder, position: Int) {
        holder.userAnswer.visibility = View.GONE

        holder.description.text = "Q: ${answerList[position].description}"
        holder.answer.text = "Ans: ${answerList[position].answer}"
        if (answerList[position].answer != answerList[position].userAnswer) {
            holder.userAnswer.visibility = View.VISIBLE
            holder.userAnswer.text = answerList[position].userAnswer
            holder.userAnswer.paintFlags =
                holder.userAnswer.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.imageViewTF.setImageResource(R.drawable.ic_wrong)
        } else if (answerList[position].answer == answerList[position].userAnswer) {
            holder.imageViewTF.setImageResource(R.drawable.ic_correct)
        }

    }

    override fun getItemCount(): Int {
        return answerList.size
    }


    inner class ResultAnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var description: TextView = itemView.findViewById(R.id.tvDescription)
        var answer: TextView = itemView.findViewById(R.id.tvAnswer)
        var userAnswer: TextView = itemView.findViewById(R.id.tvUserAnswer)
        var imageViewTF: ImageView = itemView.findViewById(R.id.imageViewTF)
    }
}