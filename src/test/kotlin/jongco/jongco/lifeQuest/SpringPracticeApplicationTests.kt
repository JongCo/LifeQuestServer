package jongco.jongco.lifeQuest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class SpringPracticeApplicationTests (
) {

	@Transactional
	@Test
	fun testJpa() { }

}
