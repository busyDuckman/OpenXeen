//package Toolbox;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * Created by duckman on 4/06/2016.
// */
//public class HumanOrderTest
//{
//    String[] expectedSet;
//
//    @Before
//    public void setUp() throws Exception
//    {
//        expectedSet = new String[] {
//                "",
//                " ",
//                "/*",
//                "_a",
//                "0",
//                "1",
//                "a",
//                "bar-0",
//                "bar-00000",
//                "bar-001",
//                "bar-002",
//                "bar-03",
//                "bar-0005",
//                "c;",
//                "cat.dog",
//                "catapult.dog",
//                "foo -6",
//                "foo -5",
//                "foo -4",
//                "foo-1",
//                "foo-2",
//                "foo-3",
//                "goldy -2.1",
//                "goldy -2.0",
//                "goldy -1.9",
//                "Heading 1.9.9",
//                "Heading 1.9.10",
//                "Heading 1.9.11",
//                "Heading 2",
//                "Heading 2.",
//                "Heading 2.0",
//                "Heading 2.0.1",
//                "spoon-1.9",
//                "spoon-2.0",
//                "Tee",
//                "tee",
//                "Teeth",
//                "Two",
//                "two"
//        };
//    }
//
//    @Test
//    public void compare() throws Exception
//    {
//        //Arrays.stream(expectedSet).
//        List<String> testSet = Arrays.asList(expectedSet);
//        Collections.reverse(testSet);
//
//        Collections.sort(testSet, new HumanOrder());
//        Assert.assertTrue(Arrays.equals(testSet.toArray(), expectedSet));
//
//        Collections.shuffle(testSet);
//        Collections.sort(testSet, new HumanOrder());
//        Assert.assertTrue(Arrays.equals(testSet.toArray(), expectedSet));
//    }
//
//    @Test
//    public void equals() throws Exception {
//
//    }
//
//}