package com.coding.excercise;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

	private int response_code;

	private List<Result> results;

	public int getResponse_code() {
		return response_code;
	}
}
