package com.googlecode.usc.folder.compression.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.googlecode.usc.folder.compression.CompressionType;

/**
 *
 * @author ShunLi
 */
public class CompressionUtilTest {
    @Test
    public void testIsYes() {
        assertTrue(CompressionUtil.isYes("Y"));
        assertTrue(CompressionUtil.isYes("y"));
        assertTrue(CompressionUtil.isYes("true"));
        assertTrue(CompressionUtil.isYes("是"));
        assertFalse(CompressionUtil.isYes("N"));
        assertFalse(CompressionUtil.isYes("n"));
    }

    @Test
    public void testInitExcludedKeys() {
        String excludedWords = ".svn | target";
        List<String> excludedKeys = CompressionUtil.initExcludedKeys(excludedWords);
        assertNotNull(excludedKeys);
        assertEquals(3, excludedKeys.size());
        assertEquals(".svn", excludedKeys.get(0));
        assertEquals("|", excludedKeys.get(1));
        assertEquals("target", excludedKeys.get(2));
    }

    @Test
    public void testListFileNames() {
        Map<String, File> fileNameMaps = new HashMap<String, File>();
        String baseDir = System.getProperty("user.dir");
        CompressionUtil.list(new File(baseDir), null, fileNameMaps, Arrays.asList(".svn", "target"));

        assertTrue(fileNameMaps.size() > 0);
        assertTrue(fileNameMaps.containsKey("folder-compression-base/src/main/java/com/googlecode/usc/folder/compression/Strategy.java"));
        assertFalse(fileNameMaps.containsKey("folder-compression-base/.svn/tmp/text-base")); // exculde .svn\
        assertTrue(fileNameMaps.containsValue(new File(baseDir, "src/main/java/com/googlecode/usc/folder/compression/Strategy.java")));
    }

    @Test
    public void testGetEnumFromString() {
        CompressionType type = CompressionUtil.getEnumFromString(CompressionType.class, "zip");
        assertNotNull(type);
        assertEquals(CompressionType.ZIP, type);
    }
}
