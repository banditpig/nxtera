package com.nxtera;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;



class StringManipTest {
    private  StringManip stringManip = new StringManip();

    @Test
    public void wordsOccur_null_input(){
        /*
        We expect a valid Map that is empty.
         */
        Map<String , Long> occurs = stringManip.wordsOccur(null);
        assertNotNull(occurs);
        assertEquals(0, occurs.keySet().size());
        assertEquals(0, occurs.values().size());

    }
    @Test
    public void wordsOccur_empty_input(){
        /*
        We expect a valid Map that is empty.
         */
        Map<String , Long> occurs = stringManip.wordsOccur("");
        assertNotNull(occurs);
        assertEquals(0, occurs.keySet().size());
        assertEquals(0, occurs.values().size());

    }
    @Test
    public void wordsOccur_one_word(){
        /*
        Expect one entry with count of 1
         */
        Map<String , Long> occurs = stringManip.wordsOccur("hello");
        assertNotNull(occurs);
        assertEquals(1, occurs.keySet().size());
        assertEquals(1, occurs.values().size());
        assertTrue(1 == occurs.get("hello"));

    }
    @Test
    public void wordsOccur_one_word_repeated(){
        /*
        Expect one entry with count of 3
         */
        Map<String , Long> occurs = stringManip.wordsOccur("hello hello hello");
        assertNotNull(occurs);
        assertEquals(1, occurs.keySet().size());
        assertEquals(1, occurs.values().size());
        assertTrue(3 == occurs.get("hello"));

    }
    @Test
    public void wordsOccur_multiple_words(){

        Map<String , Long> occurs = stringManip.wordsOccur("hello how are you are you well ");
        assertNotNull(occurs);
        assertEquals(5, occurs.keySet().size());
        assertEquals(5, occurs.values().size());
        assertTrue(1 == occurs.get("hello"));
        assertTrue(1 == occurs.get("how"));
        assertTrue(2 == occurs.get("are"));
        assertTrue(2 == occurs.get("you"));
        assertTrue(1 == occurs.get("well"));

    }
    //=====================================================================================

    @Test
    public void newlines_null_input_ok_length(){
        String lines = stringManip.insertNewlines(null,8 );
        assertNotNull(lines);
        assertEquals(0, lines.length());
    }

    @Test
    public void newlines_empty_input_ok_length(){
        String lines = stringManip.insertNewlines(null,8 );
        assertNotNull(lines);
        assertEquals(0, lines.length());
    }
    @Test
    public void newlines_bad_length(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringManip.insertNewlines(null,-8 );
        });
        ///and also try 0
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringManip.insertNewlines(null,0 );
        });
    }

    @Test
    public void newlines_short_line(){
        String lines = stringManip.insertNewlines("small",8 );
        assertTrue(lines.endsWith("\n"));
        assertTrue(lines.trim().equals("small"));
    }

    @Test
    public void newlines_break_in_word(){
        String lines = stringManip.insertNewlines("more than eight",6 );
        assertTrue(lines.endsWith("\n"));

        String[] sentences = lines.split("\n");
        assertTrue(sentences.length == 2);
        assertTrue(sentences[0].equals("more than"));
        assertTrue(sentences[1].equals("eight"));
    }
    @Test
    public void newlines_break_outside_word(){

        String lines = stringManip.insertNewlines("more than eight",10 );
        assertTrue(lines.endsWith("\n"));
        String[] sentences = lines.split("\n");
        assertTrue(sentences.length == 2);
        assertTrue(sentences[0].equals("more than"));
        assertTrue(sentences[1].equals("eight"));
    }



    @Test
    public void newlines_all_break_outside_word(){

        //line length of 5 so all boundaries are not within a word
        String lines = stringManip.insertNewlines("word0 word1 word2 word3 word4",5 );
        assertTrue(lines.endsWith("\n"));
        String[] sentences = lines.split("\n");
        assertTrue(sentences.length == 5);

        IntStream.range(0,4).forEach(i -> assertTrue(sentences[i].equals("word" + i)));
    }
    @Test
    public void newlines_all_break_inside_word(){

        //line length of 3 so all boundaries ARE within a word
        String lines = stringManip.insertNewlines("word0 word1 word2 word3 word4",3 );
        assertTrue(lines.endsWith("\n"));
        String[] sentences = lines.split("\n");
        assertTrue(sentences.length == 5);

        IntStream.range(0,4).forEach(i -> assertTrue(sentences[i].equals("word" + i)));
    }

}