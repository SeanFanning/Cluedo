package test;

import static org.junit.jupiter.api.Assertions.*;

import game.Notebook;
import org.junit.jupiter.api.Test;

class NotebookTest {
    @Test
    void testNotebook() {

        Notebook tester = new Notebook("PROFESSOR_PLUM");

        // assert statements
        assertEquals(tester.getNotes().get(0), "PROFESSOR_PLUM\'s Notebook: ");
        assertNotEquals(tester.getNotes().get(0), "PROFESSOR_PLUM\'s Notebook:");
        tester.add_note("Test note in notebook");
        assertEquals(tester.getNotes().get(1), "Test note in notebook");
    }

}