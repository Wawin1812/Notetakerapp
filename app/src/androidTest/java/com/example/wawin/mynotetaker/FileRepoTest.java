package com.example.wawin.mynotetaker;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileFilter;

import static org.junit.Assert.*;

/**
 * Created by Wawin on 3/29/16.
 */
public class FileRepoTest extends ApplicationTestCase<Application> {
    private DBHelper db;
    //File file = new File();
    private FileRepo fp;

    public FileRepoTest() {
        super(Application.class);
    }


    @Before
    public void setUp() throws Exception {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DBHelper(context);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    @Test
    public void testInsert() throws Exception {
        File file = new File();
        file.file_ID = 12;
        file.name = "abc";
        Log.v(file.name+"lll","testing");
        System.out.print("lll"+file.name);
        int a = fp.insert(file);
     //   long a = fp.insert(file);
        assertEquals(a, -1);
        File f1 = fp.info(file.file_ID);
        assertEquals(f1.name,file.name);
    }

    @Test
    public void testInfo() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testGetFileList() throws Exception {

    }

    @Test
    public void testGetFIleById() throws Exception {

    }
}