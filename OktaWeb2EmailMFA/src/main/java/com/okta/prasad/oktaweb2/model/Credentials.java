package com.okta.prasad.oktaweb2.model;

public class Credentials {

	public Password password = new Password();
	public Recovery_question recovery_question=new Recovery_question();

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public Recovery_question getRecovery_question() {
		return recovery_question;
	}

	public void setRecovery_question(Recovery_question recovery_question) {
		this.recovery_question = recovery_question;
	}

	public class Password {
		public String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{ value:\"").append(value).append("}");
			return builder.toString();
		}
	}

	public class Recovery_question {
		public String question, answer;

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{ question:\"").append(question).append("\", answer:\"").append(answer).append("}");
			return builder.toString();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ password:\"").append(password).append("\", recovery_question :\"")
				.append( recovery_question).append("}");
		return builder.toString();
	}

}
