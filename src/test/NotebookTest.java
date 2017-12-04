package test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import game.Notebook;
import game.Note;

public class NotebookTest {

    private Notebook notebook;

    @Before
    public void setUp() throws Exception {
        notebook = new Notebook("PROFESSOR_PLUM");
    }

    @Test
    public void testNotebook() {
        // assert statements
        assertEquals(notebook.getNotes().get(0).getNote(), "PROFESSOR_PLUM\'s Notebook: ");
        assertNotEquals(notebook.getNotes().get(0).getNote(), "PROFESSOR_PLUM\'s Notebook:");
        notebook.add_note("Test note in notebook", "Test");
        assertEquals(notebook.getNotes().get(1).getNote(), "Test note in notebook");
    }

}