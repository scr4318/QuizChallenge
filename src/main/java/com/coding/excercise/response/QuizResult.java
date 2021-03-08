package com.coding.excercise.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResult {

	private String type;
	private String difficulty;
	private String question;
	private List<String> all_answers;
	private String correct_answer;

}
