package com.nsergey.basejava.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ResumeTest {

    private final Resume first1 = new Resume("uuid1", "Name1");
    private final Resume first2 = new Resume("uuid1", "Name1");
    private final Resume second = new Resume("uuid2", "Name2");

    @Test
    void testGetUuid() {
        assertNotNull(new Resume("SomeName").getUuid());
    }

    @Test
    void testEquals() {
        assertNotSame(first1, first2);
        assertEquals(first1, first2);

        assertNotEquals(first1, second);
        assertNotEquals(first2, second);
    }

    @Test
    void testHashCode() {
        assertEquals(first1.hashCode(), first2.hashCode());
        assertNotEquals(first1.hashCode(), second.hashCode());
        assertNotEquals(first2.hashCode(), second.hashCode());
    }

    @Test
    void testToString() {
        String string = first1.toString();
        assertTrue(string.contains("Resume"));
        assertTrue(string.contains(first1.getUuid()));
        assertTrue(string.contains(first1.getFullName()));
    }
}