package test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import game.Notebook;

public class NotebookTest {

    private Notebook notebook;

    @Before
    public void setUp() throws Exception {
        notebook = new Notebook("PROFESSOR_PLUM");
    }

    @Test
    public void testNotebook() {
        // assert statements
        assertEquals(notebook.getNotes().get(0), "PROFESSOR_PLUM\'s Notebook: ");
        assertNotEquals(notebook.getNotes().get(0), "PROFESSOR_PLUM\'s Notebook:");
        notebook.add_note("Test note in notebook");
        assertEquals(notebook.getNotes().get(1), "Test note in notebook");
    }

}