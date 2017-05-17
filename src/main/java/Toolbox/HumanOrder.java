//package Toolbox;
//
//import java.util.Comparator;
//
///**
// * Created by duckman on 4/06/2016.
// *
// * Motivation:
// *   I didn't like the natural order stuff, that I found, floating around on the net.
// *   (Bugs and lack of testing).
// *
// *   So I groaned the usual, "why doesn't java have what I want?" and set about making my own.
// *   Figured I may need this on bigish data one day so performance is being considered.
// *
// *   Two approaches came to mind:
// *     - recursive front of string tokeniser
// *        - Will need to resolve recursion, or it could be an excuse to spin the wheels of the new
// *          lamda tail end optimisation.
// *     - Normalise & transform into something that runs in standard string compare.
// *        - Clearly problematic (consider upper/lower case)
// *        - Allows for memoizeing the comparable string and thus decoration of large datasets
// *
// *   Knock them both out and compare I suppose...
// */
//public class HumanOrder implements Comparator<String>
//{
//    @Override
//    public int compare(String a, String b) {
//        char ca = a.charAt(0);
//        char cb = b.charAt(0);
//
//        if(Character.isLetter(ca
//
//        return 0;
//    }
//
//    enum TokenType
//    {
//        LETTER(8),
//        NUMBER(5),
//        DOT(3),
//        PUNCTUATION(4),
//        WHITE_SPACE(2),
//        MISC(9);
//
//        int priority;
//
//        TokenType(int priority) {
//            this.priority = priority;
//        }
//
//        public int compare(TokenType a, TokenType b) { return Integer.compare(a.priority, b.priority); }
//
//        public static TokenType fromChar(char c)
//        {
//            if(Character.isLetter(return c)
//        }
//    }
//
//
//
//
//
////    @Override
////    public boolean equals(Object obj) {
////        return false;
////    }
//}
