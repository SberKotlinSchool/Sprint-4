package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StudentsGroupTest {

	@Test
	fun filterByPredicate() {
		val group = StudentsGroup()
		group.filterByPredicate { it.averageRate > 0.1 }

		assertEquals(2, group.students.size)
	}
}