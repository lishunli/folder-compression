package com.googlecode.usc.folder.compression.concrete.strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

import com.googlecode.usc.folder.compression.Strategy;

/**
 *
 * @author ShunLi
 */
public class JarStrategy extends Strategy {
    @Override
    public ArchiveOutputStream getArchiveOutputStream(FileOutputStream fos) throws IOException {
        return new JarArchiveOutputStream(fos);
    }

    @Override
    public ArchiveEntry getArchiveEntry(File inputFile, String entryName) {
        return new ZipArchiveEntry(inputFile, entryName);
    }

}
