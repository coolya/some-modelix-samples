package org.modelix.samples.some;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class RestApiTest {

	@Inject
	EmbeddedApplication<?> application;

	// TODO: broke with version 2.0
//	@Test
//	void testItWorks() {
//		Assertions.assertTrue(application.isRunning());
//	}

}
