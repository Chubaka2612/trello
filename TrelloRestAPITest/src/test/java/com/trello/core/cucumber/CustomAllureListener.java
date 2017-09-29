package com.trello.core.cucumber;

import org.junit.runner.notification.Failure;

import ru.yandex.qatools.allure.junit.AllureRunListener;

public class CustomAllureListener extends AllureRunListener {

	@Override
	public void testFailure(Failure failure) {
		if (!failure.getDescription().isSuite()) {
			super.testFailure(failure);
		}
	}
}