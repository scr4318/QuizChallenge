package com.coding.excercise;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

	private String category;

	private String type;

	private String difficulty;

	private String question;

	private String correct_answer;

	private List<String> incorrect_answers;

}
