package com.example.trivia_jan10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trivia_jan10.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding: FragmentQuestionBinding get() = _binding!!

    private val viewModel: TriviaViewModel by activityViewModels()
    private lateinit var adapter: AnswerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AnswerAdapter(this::onAnswerSelected)
        with(binding) {
            questionRv.apply {
                adapter = this@QuestionFragment.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            viewModel.currentQuestion.observe(viewLifecycleOwner) { question ->
                if (question == null) return@observe
                questionTv.text = question.question
                adapter.submitAnswers(question.answers)
            }
            viewModel.isGameOver.observe(viewLifecycleOwner) { shouldNavigate ->
                if (shouldNavigate) {
                    val direction = QuestionFragmentDirections.actionQuestionFragmentToEndGameFragment()
                    findNavController().navigate(direction)
                }
            }
        }
    }

    private fun onAnswerSelected(answer: Answer) {
        viewModel.onQuestionAnswered(answer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}