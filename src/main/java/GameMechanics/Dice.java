package GameMechanics;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by duckman on 14/08/2016.
 *
 * The basic dice of many board games.
 */
public class Dice implements Comparable<Dice>
{
    public static class Tally
    {
        private final Dice[] dices;
        private final int plus;
        public Tally(Dice[] dices, int plus)
        {
            this.dices = (dices == null) ? new Dice[]{} : dices;
            this.plus = plus;
            Arrays.sort(this.dices, Comparator.reverseOrder());
        }

        public static Tally fromString(String s)
        {
            if(s != null) {
                List<Dice> dices = new ArrayList<>();
                //hide the "-" as a "!" and rephrase as "+" for splitting purposes
                String[] tokens = s.replace("-", "+!").split("\\+");

                //dice
                Arrays.stream(tokens)
                        .filter(S -> S.toUpperCase().contains("D"))
                        .map(Tally::parseDiceToken)
                        .forEach(dices::addAll);

                //plus
                int plus = Arrays.stream(tokens)
                        .filter(S -> !S.toUpperCase().contains("D"))
                        .filter(S -> S.length() > 0)
                        .map(S -> S.replaceAll("![ ]*", "-")) //was there a "-" hidden as a "!"
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .sum();

                return new Tally(dices.stream().toArray(Dice[]::new), plus);
            }
            return null;
        }

        protected static List<Dice> parseDiceToken(String token) {
            String[] tokens = token.split(":");
            List<Dice> dices = new ArrayList<>();
            if(tokens.length == 2) {
                Dice d = Dice.fromString(tokens[1].trim());
                if(d != null) {
                    for (int i = 0; i <Integer.parseInt(tokens[0].trim()); i++) {
                        dices.add(d);
                    }
                }
            }
            return dices;
        }

        @Override
        public String toString() {
            if(dices.length == 0) {
                return ""+plus;
            }
            StringBuilder sb = new StringBuilder();

//            Arrays.stream(dices)
//                    .collect(Collectors.groupingBy(Dice::toString, Collectors.counting()))
//                    .entrySet().stream().sorted((A, B) -> A.getKey().)
//                    .forEach((D, C) -> sb.append(C).append(":").append(D).append(" "));

            Arrays.stream(dices)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((A, B) ->  B.getKey().compareTo(A.getKey())) //reverse list
                    .forEach(E -> sb.append(E.getValue()).append(":").append(E.getKey().toString()).append(" "));

            String s = sb.toString().trim().replaceAll(" ", " + ");
            //finish off string (it already ends with a space)
            if(plus < 0) {
                return s + " - " +  Math.abs(plus);
            } else if(plus == 0) {
                return s;
            } else {
                return s + " + " + plus;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            //This is more sensible than it first appears.
            Tally tally = (Tally) o;
            return this.toString().equals(tally.toString());
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(dices);
            result = 31 * result + plus;
            return result;
        }

        public int roll() { return Math.max(0, Arrays.stream(dices).mapToInt(Dice::roll).sum() + plus); }
    }

    protected final static Random rnd = new Random();
    public static final Dice D6 = new Dice(6);
    public static final Dice D2 = new Dice(2);
    public static final Dice D4 = new Dice(4);
    public static final Dice D8 = new Dice(8);
    public static final Dice D10 = new Dice(10);
    public static final Dice D12 = new Dice(12);
    public static final Dice D16 = new Dice(16);

    private final int max;

    public Dice(int max) { this.max = max; }
    public static Dice fromString(String s)
    {
        if(s != null)  {
            String token = s.trim();
            if(token.startsWith("D") && (token.length() > 1))  {
                int max = Integer.parseInt(token.substring(1));
                return new Dice(max);
            }
        }
        return null;
    }

    public final int getMax() { return max; }
    public final int roll() {  return rnd.nextInt(max)+1; }

    @Override public String toString() { return "D" + max; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dice dice = (Dice) o;
        return max == dice.max;
    }

    @Override public int hashCode() {return max;}

    @Override
    public int compareTo(Dice d) {
        return Integer.compare(max, d.max);
    }

    /**
     * For those "2*D6 + 8" type rules.
     */
    public Tally tally(int n, int plus)
    {
        Dice[] dices = new Dice[n];
        for (int i = 0; i < dices.length; i++) {
            Dice dice = this;
        }
        return new Tally(dices, plus);
    }
}
