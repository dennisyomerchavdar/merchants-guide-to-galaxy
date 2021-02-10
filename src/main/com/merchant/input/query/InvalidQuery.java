package com.merchant.input.query;

public class InvalidQuery implements IQuery{
	@Override
	public String getAnswerForQuery() {
		return "I have no idea what you are talking about";
	}
}
