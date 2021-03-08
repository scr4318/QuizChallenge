package com.coding.excercise.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.coding.excercise.Response;
import com.coding.excercise.common.Util;
import com.coding.excercise.exception.CustomGenericException;
import com.coding.excercise.response.Quiz;

import reactor.core.publisher.Mono;

@RestController
public class QuizController {

	@Autowired
	Util util;

	@Autowired
	WebClient.Builder webClientBuilder;

	private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

	@GetMapping("/coding/exercise/quiz")
	public Quiz getQuiz() throws Exception {

		Response responseCategoryEleven = webClientBuilder.build().get()
				.uri("https://opentdb.com/api.php?amount=5&category=11").retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> {
	                 return Mono.error(new CustomGenericException("404","Unable to Fetch Data"));}).bodyToMono(Response.class).block();

		Response responseCategoryTwelve =  webClientBuilder.build().get()
				.uri("https://opentdb.com/api.php?amount=5&category=12").retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> {
	                 return Mono.error(new CustomGenericException("404","Unable to Fetch Data"));}).bodyToMono(Response.class).block();

		Quiz quiz = new Quiz();

		quiz.setQuiz(util.convertToQuizResponse(responseCategoryEleven));
		
		logger.info("Converting To Quiz Result");
		quiz.getQuiz().addAll(util.convertToQuizResponse(responseCategoryTwelve));

		return quiz;
	}

}
