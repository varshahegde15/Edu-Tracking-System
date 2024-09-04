package com.jsp.ets.user;

import lombok.Getter;

import java.util.List;

@Getter
public enum Stack {

	JAVA_FULL_STACK(List.of(Subject.CORE_JAVA, Subject.HIBERNATE, Subject.SQL, Subject.SPRING, Subject.SPRING_BOOT,
			Subject.HTML, Subject.CSS, Subject.JAVASCRIPT, Subject.SERVLET)),
	MERN_FULL_STACK(List.of(Subject.HTML, Subject.CSS, Subject.JAVASCRIPT, Subject.REACT_JS)), PYTHON_FULL_STACK(
			List.of(Subject.PYTHON, Subject.SQL, Subject.HTML, Subject.CSS, Subject.JAVASCRIPT, Subject.DJANGO));

	private List<Subject> subjects;

	private Stack(List<Subject> subjects) {
		this.subjects = subjects;
	}

}
