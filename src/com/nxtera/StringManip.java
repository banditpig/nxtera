package com.nxtera;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class StringManip {

    public final  Map<String, Long> wordsOccur(String text) {

        if(text == null || text.length() == 0 ) return new HashMap<>();// or maybe illegal arg. exception
        return  Arrays.asList(text.split("\\s+")) // strip spaces and stream to collect
                .stream()
                .collect(groupingBy(Function.identity(), counting()));


    }
   
    public final String insertNewlines(String text, int lineLen){

        if(lineLen <=0 ) throw new IllegalArgumentException("Line length should be at least 1");

        if (text == null || text.length() == 0) return "";

        boolean done = false;
        StringBuilder result = new StringBuilder();
        String txt = text;
        //pull text apart and don't include any trailing spaces
        while (!done){
            String brk = "";
            if(txt.length() <= lineLen){
                brk = txt;
                done = true;
            }else{
                brk = breakOnBoundary(txt, lineLen).trim();
            }
            txt = txt.substring(brk.length()).trim();
            result.append(brk).append("\n");
        }

        return result.toString();
    }

    private String breakOnBoundary(String text, int pos ){

        //we've gone past the end
        if(pos >= text.length()) return text;
        //might be on a space - so thats ok
        if (text.charAt(pos -1 ) == ' '){
            return text.substring(0, pos);
        }
        //if not on a space recursively move along one char at a time until get space or done
        return text.substring(0, pos) + breakOnBoundary(text.substring(pos), 1);
    }


}
