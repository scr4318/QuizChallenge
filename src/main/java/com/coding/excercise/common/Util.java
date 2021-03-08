package com.coding.excercise.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.coding.excercise.Response;
import com.coding.excercise.Result;
import com.coding.excercise.response.QuizResponse;
import com.coding.excercise.response.QuizResult;

@Configuration
public class Util {

	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	public String replaceSpecialChar(String baseString) {
		String updatedString = new String();

		updatedString = baseString.replaceAll("\"&quot;", "\"");
		updatedString = updatedString.replaceAll("&#039;", "\'");
		updatedString = updatedString.replaceAll("&rdquo;", "\"\"");
		updatedString = updatedString.replaceAll("&ldquo;", "\"\"");
		logger.info("Replacing Characters");
		return updatedString;
	}

	public List<QuizResponse> convertToQuizResponse(Response response) {

		List<QuizResponse> quizResponseList = new ArrayList<QuizResponse>();

		for (Result result : response.getResults()) {
			QuizResponse quizResponse = new QuizResponse();
			QuizResult quizResult = new QuizResult();

			convertToQuizResult(quizResult, result);

			if (result.getCategory().equals("Film")) {
				quizResponse.setCategory(result.getCategory());
				quizResponse.setResults(addToList(quizResult));
				quizResponseList.add(quizResponse);

			} else {
				quizResponse.setCategory(result.getCategory());
				quizResponse.setResults(addToList(quizResult));
				quizResponseList.add(quizResponse);
			}

		}

		return quizResponseList;
	}

	private QuizResult convertToQuizResult(QuizResult quizResult, Result result) {
		quizResult.setType(result.getType());
		quizResult.setDifficulty(result.getDifficulty());
		quizResult.setQuestion(replaceSpecialChar(result.getQuestion()));
		quizResult.setCorrect_answer(replaceSpecialChar(result.getCorrect_answer()));
		quizResult.setAll_answers(aggregateAllAnswers(result.getIncorrect_answers(), result.getCorrect_answer()));
		logger.info("Converting To Quiz Result");
		return quizResult;

	}

	private List<String> aggregateAllAnswers(List<String> inCorrectAnswers, String correctAnswer) {
		List<String> allAnswers = new ArrayList<String>();
		inCorrectAnswers.forEach(inCorrectAnswer -> replaceSpecialChar(inCorrectAnswer));
		allAnswers.addAll(inCorrectAnswers);
		allAnswers.add(replaceSpecialChar(correctAnswer));
		return allAnswers;
	}

	private List<QuizResult> addToList(QuizResult quizResult) {
		List<QuizResult> quizResultList = new ArrayList<QuizResult>();
		quizResultList.add(quizResult);
		return quizResultList;
	}

}
