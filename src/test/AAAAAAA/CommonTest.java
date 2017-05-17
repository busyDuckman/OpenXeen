package AAAAAAA;

import org.junit.Assert;

/**
 * Created by duckman on 13/06/2016.
 *
 * I know all the arguments not to do this. But... I don't care.
 * I just refuse to create myself a nasty mess of copy pasted code.
 *
 * NB: Class name is awkward, because of the ending the class name in Test stuff.
 */
public abstract class CommonTest extends org.junit.runners.model.TestClass
{
    public CommonTest(Class<?> clazz) {
        super(clazz);
    }

    @FunctionalInterface
    public interface IThrowException//<E extends Exception>
    {
        void doStuff() throws Exception;
    }
    public static void assertThrown(IThrowException test, Class<?> expected)
    {
        try {
            test.doStuff();
            Assert.fail("Expecting an exception.");
        }
        catch (Exception ex) {
            Assert.assertTrue(expected.isAssignableFrom(ex.getClass()));
        }

    }
}
