package Toolbox;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by duckman on 30/05/2016.
 */
public class FileHelpersTest {
    @Test
    public void getParentDirectory() throws Exception
    {
        assertEquals("c:\\foo", FileHelpers.getParentDirectory("c:\\foo\\bar"));
        assertEquals(FileHelpers.getParentDirectory("c:/foo/bar"), "c:/foo");
        assertEquals("c:\\foo", FileHelpers.getParentDirectory("c:\\foo\\bar\\"));
        assertEquals("c:/foo", FileHelpers.getParentDirectory("c:/foo/bar/"));
        assertEquals("c:\\foo", FileHelpers.getParentDirectory("c:\\foo\\bar.txt"));
        assertEquals("c:/foo", FileHelpers.getParentDirectory("c:/foo/bar.txt"));
        assertEquals("", FileHelpers.getParentDirectory("\\foo"));
        assertEquals("", FileHelpers.getParentDirectory("/foo"));
        assertEquals("", FileHelpers.getParentDirectory("foo"));
        assertEquals("", FileHelpers.getParentDirectory(null));
        assertEquals("", FileHelpers.getParentDirectory("a"));
        assertEquals("a", FileHelpers.getParentDirectory("a\\b"));
    }

    @Test
    public void getFileName() throws Exception
    {
        assertEquals("bar", FileHelpers.getFileName("c:\\foo\\bar"));
        assertEquals("bar.txt", FileHelpers.getFileName("c:\\foo\\bar.txt"));
        assertEquals("", FileHelpers.getFileName(null));
        assertEquals("bar.txt", FileHelpers.getFileName("bar.txt"));
        assertEquals("", FileHelpers.getFileName("c:\\foo\\"));

        assertEquals("bar", FileHelpers.getFileName("c:/foo/bar"));
        assertEquals("bar.txt", FileHelpers.getFileName("c:/foo/bar.txt"));
        assertEquals("bar.txt", FileHelpers.getFileName("/bar.txt"));
        assertEquals("", FileHelpers.getFileName("c:/foo/"));
        assertEquals("bar.spoon.txt", FileHelpers.getFileName("c:/foo/bar.spoon.txt"));
    }

    @Test
    public void getFileExtension() throws Exception
    {
        assertEquals("txt", FileHelpers.getFileExtension("c:\\foo\\bar.txt"));
        assertEquals("txt", FileHelpers.getFileExtension("c:/foo/bar.txt"));
        assertEquals("t", FileHelpers.getFileExtension("c:\\foo\\bar.t"));
        assertEquals("", FileHelpers.getFileExtension("c:\\foo\\bar."));
        assertEquals("", FileHelpers.getFileExtension("c:\\foo\\bar"));
        assertEquals("txt", FileHelpers.getFileExtension("c:\\foo\\.txt"));

        assertEquals("", FileHelpers.getFileExtension(null));
        assertEquals("", FileHelpers.getFileExtension(""));
        assertEquals("", FileHelpers.getFileExtension("c:\\foo\\"));
        assertEquals("", FileHelpers.getFileExtension("c:\\foo.dir\\"));
        assertEquals("txt", FileHelpers.getFileExtension("foo.txt"));

        assertEquals("txt", FileHelpers.getFileExtension("foo.bar.txt"));
        assertEquals("txt", FileHelpers.getFileExtension("c:\\foo\\bar.spoon.txt"));
    }

    @Test
    public void hasExtension() throws Exception
    {

    }

    @Test
    public void getFileNameNoExtension() throws Exception {
        assertEquals("bar", FileHelpers.getFileNameNoExtension("c:\\foo\\bar"));
        assertEquals("", FileHelpers.getFileNameNoExtension("c:\\foo\\.bar"));
        assertEquals("bar", FileHelpers.getFileNameNoExtension("c:\\foo\\bar.txt"));
        assertEquals("bar", FileHelpers.getFileNameNoExtension("c:\\foo\\bar"));
        assertEquals("", FileHelpers.getFileNameNoExtension(null));
        assertEquals("bar", FileHelpers.getFileNameNoExtension("bar.txt"));
        assertEquals("", FileHelpers.getFileNameNoExtension("c:\\foo\\"));

        assertEquals("bar", FileHelpers.getFileNameNoExtension("c:/foo/bar"));
        assertEquals("bar", FileHelpers.getFileNameNoExtension("c:/foo/bar.txt"));
        assertEquals("bar", FileHelpers.getFileNameNoExtension("/bar.txt"));
        assertEquals("", FileHelpers.getFileNameNoExtension("c:/foo/"));
        assertEquals("bar.spoon", FileHelpers.getFileNameNoExtension("c:/foo/bar.spoon.txt"));
    }

    @Test
    public void join() throws Exception
    {
        assertEquals("", FileHelpers.join("", ""));
        assertEquals("", FileHelpers.join(null, null));

        assertEquals("foo", FileHelpers.join("", "foo"));
        assertEquals("/foo", FileHelpers.join(null, "/foo"));
        assertEquals("foo", FileHelpers.join("foo", ""));
        assertEquals("/foo", FileHelpers.join("/foo", null));

        assertEquals("c:\\foo\\bar.txt", FileHelpers.join("c:\\foo", "bar.txt"));
        assertEquals("c:/foo/bar.txt", FileHelpers.join("c:/foo", "bar.txt"));

        assertEquals("c:\\foo\\bar.txt", FileHelpers.join("c:\\foo\\", "bar.txt"));
        assertEquals("c:/foo/bar.txt", FileHelpers.join("c:/foo/", "bar.txt"));

        assertEquals("c:\\foo\\bar.txt", FileHelpers.join("c:", "\\foo\\bar.txt"));
        assertEquals("c:/foo/bar.txt", FileHelpers.join("c:/", "foo/bar.txt"));

        assertEquals("c:\\foo\\bar.txt", FileHelpers.join("c:\\foo\\", "\\bar.txt"));
        assertEquals("c:/foo/bar.txt", FileHelpers.join("c:/foo/", "/bar.txt"));

        //Mixed / \ tests
        assertEquals("c:\\foo\\bar.txt", FileHelpers.join("c:\\foo\\", "/bar.txt"));
        assertEquals("c:/foo/bar.txt", FileHelpers.join("c:/foo/", "\\bar.txt"));

        assertEquals("c:\\foo\\spoon/bar.txt", FileHelpers.join("c:\\foo\\", "spoon/bar.txt"));
        assertEquals("c:\\foo\\spoon/bar.txt", FileHelpers.join("c:\\foo\\", "/spoon/bar.txt"));
    }

    @Test
    public void systemPathSeperator() throws Exception {
        //if this is not true, some of the code will have a headache
        assertTrue("/\\".contains(FileHelpers.systemPathSeperator()));
    }

    @Test
    public void getFileNameTillFirstDot() throws Exception
    {
        assertEquals("bar", FileHelpers.getFileNameTillFirstDot("c:\\foo\\bar"));
        assertEquals("bar", FileHelpers.getFileNameTillFirstDot("c:\\foo\\bar.txt"));
        assertEquals("bar", FileHelpers.getFileNameTillFirstDot("c:\\foo\\bar."));
        assertEquals("",    FileHelpers.getFileNameTillFirstDot("c:\\foo\\."));
        assertEquals("",    FileHelpers.getFileNameTillFirstDot(null));
        assertEquals("bar", FileHelpers.getFileNameTillFirstDot("bar.txt"));
        assertEquals("",    FileHelpers.getFileNameTillFirstDot("c:\\foo\\"));

        assertEquals("bar", FileHelpers.getFileNameTillFirstDot("c:/foo/bar"));
        assertEquals("bar", FileHelpers.getFileNameTillFirstDot("c:/foo/bar.txt"));
        assertEquals("bar", FileHelpers.getFileNameTillFirstDot("/bar.txt"));
        assertEquals("",    FileHelpers.getFileNameTillFirstDot("c:/foo/"));
        assertEquals("bar", FileHelpers.getFileNameTillFirstDot("c:/foo/bar.spoon.txt"));
    }

    @Test
    public void getFileNameTillFirst() throws Exception {
        //getFileNameTillFirstDot calls this, so our testing is done.
    }

    @Test
    public void getAbsolutePath() throws Exception {

    }

    @Test
    public void changeExtesion() throws Exception
    {
        //this ends up calling many other ares of FileHelpers, its a good overall test.
//        assertEquals("c:\\foo\\bar.new", FileHelpers.changeExtesion("c:\\foo\\bar.txt", "new"));
//        assertEquals("c:/foo/bar.new", FileHelpers.changeExtesion("c:/foo/bar.txt", "new"));
//        assertEquals("c:\\foo\\bar.new", FileHelpers.changeExtesion("c:\\foo\\bar.t", "new"));
//        assertEquals("c:\\foo\\bar.new", FileHelpers.changeExtesion("c:\\foo\\bar.", "new"));
//        assertEquals("c:\\foo\\bar.new", FileHelpers.changeExtesion("c:\\foo\\bar", "new"));
//
//        assertEquals("", FileHelpers.changeExtesion(null, "new"));
//        assertEquals("", FileHelpers.changeExtesion("", "new"));
//        assertEquals("c:\\foo\\", FileHelpers.changeExtesion("c:\\foo\\", "new"));
//        assertEquals("c:\\foo.dir\\", FileHelpers.changeExtesion("c:\\foo.dir\\", "new"));
//        assertEquals("foo.new", FileHelpers.changeExtesion("foo.txt", "new"));
//
//        assertEquals("foo.bar.new", FileHelpers.changeExtesion("foo.bar.txt", "new"));
//        assertEquals("c:\\foo\\bar..new", FileHelpers.changeExtesion("c:\\foo\\bar..txt", "new"));
    }

}