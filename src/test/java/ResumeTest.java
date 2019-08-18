import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResumeTest {

    private final Resume first1 = new Resume("uuid1");
    private final Resume first2 = new Resume("uuid1");
    private final Resume second = new Resume("uuid2");

    @Test
    void testGetUuid() {
        String uuid = "some-uuid";
        assertEquals(uuid, new Resume(uuid).getUuid());
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
        String uuid = "some-uuid";
        assertEquals(uuid, new Resume(uuid).toString());
    }
}