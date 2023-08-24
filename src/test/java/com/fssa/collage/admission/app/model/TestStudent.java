package com.fssa.collage.admission.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


class TestStudent {

	Student student = new Student(1,567865, "John", "Doe", 1234567890, "john@example.com", "password", "Male",
			LocalDate.of(1990, 1, 1), LocalDateTime.now(), true);

	@Test
	void testId() {
		assertEquals(1, student.getId());
	}
	
	@Test
	void testRollNo() {
		assertEquals(567865, student.getRollNo());
	}

	@Test
	void testFirstName() {
		assertEquals("John", student.getFirstName());
	}

	@Test
	void testLastName() {
		assertEquals("Doe", student.getLastName());
	}

	@Test
	void testMobileNumber() {
		assertEquals(1234567890, student.getMobileNumber());
	}

	@Test
	void testEmailId() {
		assertEquals("john@example.com", student.getEmailId());
	}

	@Test
	void testPassword() {
		assertEquals("password", student.getPassword());
	}

	@Test
	void testGender() {
		assertEquals("Male", student.getGender());
	}

	@Test
	void testDob() {
		assertEquals(LocalDate.of(1990, 1, 1), student.getDob());
	}

	@Test
	void testCreatedDate() {
		assertNotNull(student.getCreated_date());
	}

	@Test
	void testIsActive() {
		assertEquals(true, student.getIsActive());
	}

	@Test
	void testFullName() {
		assertEquals("John Doe", student.fullName());
	}
}
