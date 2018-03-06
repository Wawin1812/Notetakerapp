package com.example.wawin.mynotetaker;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Wawin on 3/28/16.
 */
public class FileRepoTest extends ApplicationTestCase<Application> {

    private FileRepo fp;

    public FileRepoTest() {
        super(Application.class);
    }

    @Before
    public void setUp() throws Exception {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        fp = new FileRepo(context);
    }

    @After
    public void tearDown() throws Exception {
        fp.close();
        super.tearDown();
    }

    @Test
    public void testInsert() throws Exception {
        File file = new File("test1", "test1values", 0, 0, "");
        fp.insert(fp, file);
        long a = fp.insert(fp, file);
        assertEquals(a, -1);
        Cursor cursor = fp.getInformation(fp);
        assertEquals(cursor.getCount(), 1);
        cursor.moveToFirst();
        assertTrue(cursor.getString(0).equals("test"));

    }

    @Test
    public void testInfo() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {
        File file = new File("test1", "test1values", 0, 0, "");
        fp.insert(fp, file);
        Cursor cursor = fp.getInformation(fp);
        assertEquals(cursor.getCount(), 1);
        File file1 = new File("test2" , "test2values" , 0 , 0 , "");
        fp.insert(fp, file1);
        Cursor cursor = fp.getInformation(fp);
        assertEquals(cursor.getCount(), 2);
        fp.delete(fp, file);
        cursor = fp.getInfomation(fp);
        assertEquals(cursor.getCount(), 1);
    }

    @Test
    public void testUpdate() throws Exception {
        File file = new File("test1", "test1values", 0, 0, "");
        fp.insert(fp, file);
        Cursor cursor = fp.getInformation(fp);
        assertEquals(cursor.getCount(), 1);
        cursor.moveToFirst();
        assertTrue(file.GetFileById().equals("test1values"));
        file.setFileById("editvalues");
        cursor = fp.GetInformation(fp);
        //Cursor cursor = fp.getInformation(fp);
        assertEquals(cursor.getCount(), 1);
        cursor.moveToFirst();
        assertTrue(file.GetFileById().equals("editedvalues"));

    }

    @Test
    public void testGetFileList() throws Exception {
        File file = new File("test1","test1values", 0 , 0 ,"");
        fp.insert(fp, file);
        Cursor cursor = fp.getFileList(fp, file);
        cursor.moveToFirst();
        assertTrue(cursor.getString(0).equals("test1values"));
    }

    @Test
    public void testGetFIleById() throws Exception {
        File file = new File("test1","test1values", 0 , 0 ,"");
        fp.insert(fp, file);
        Cursor cursor = fp.getFIleById(fp, file);
        cursor.moveToFirst();
        assertTrue(cursor.getString(0).equals("test1values"));

    }
}