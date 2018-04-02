package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SessionTest {

	@Test
	void SessionNotNullTest() {
		Session session = new Session();
		Assertions.assertNotNull(session);
	}

}
