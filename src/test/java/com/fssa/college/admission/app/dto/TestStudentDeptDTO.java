package com.fssa.college.admission.app.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.collage.admission.app.dto.StudentDeptDTO;

/**
 * JUnit test class for the {@link StudentDeptDTO} class.
 */
public class TestStudentDeptDTO {

    private StudentDeptDTO studentDeptDTO;

    /**
     * Set up a new {@link StudentDeptDTO} object before each test.
     */
    @BeforeEach
    public void setUp() {
        studentDeptDTO = new StudentDeptDTO("Male", 1, 10);
    }

    /**
     * Test the {@link StudentDeptDTO#getDepartmentId()} method.
     */
    @Test
    public void testGetDepartmentId() {
        assertEquals(1, studentDeptDTO.getDepartmentId());
    }

    /**
     * Test the {@link StudentDeptDTO#getGender()} method.
     */
    @Test
    public void testGetGender() {
        assertEquals("Male", studentDeptDTO.getGender());
    }

    /**
     * Test the {@link StudentDeptDTO#getCount()} method.
     */
    @Test
    public void testGetCount() {
        assertEquals(10, studentDeptDTO.getCount());
    }

    /**
     * Test the {@link StudentDeptDTO#setDepartmentId(int)} method.
     */
    @Test
    public void testSetDepartmentId() {
        studentDeptDTO.setDepartmentId(2);
        assertEquals(2, studentDeptDTO.getDepartmentId());
    }

    /**
     * Test the {@link StudentDeptDTO#setGender(String)} method.
     */
    @Test
    public void testSetGender() {
        studentDeptDTO.setGender("Female");
        assertEquals("Female", studentDeptDTO.getGender());
    }

    /**
     * Test the {@link StudentDeptDTO#setCount(int)} method.
     */
    @Test
    public void testSetCount() {
        studentDeptDTO.setCount(20);
        assertEquals(20, studentDeptDTO.getCount());
    }
    

    /**
     * Test the {@link StudentDeptDTO#toString()} method.
     */
    @Test
    public void testToString() {
        String expected = "StudentDeptDto [gender=Male, count=10]";
        assertEquals(expected, studentDeptDTO.toString());
    }
}

