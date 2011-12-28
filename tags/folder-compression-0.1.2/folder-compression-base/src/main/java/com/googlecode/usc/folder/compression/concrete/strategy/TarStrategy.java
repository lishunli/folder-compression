package com.googlecode.usc.folder.compression.concrete.strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import com.googlecode.usc.folder.compression.Strategy;

/**
 *
 * @author ShunLi
 */
public class TarStrategy extends Strategy {
    @Override
    public ArchiveOutputStream getArchiveOutputStream(FileOutputStream fos) throws IOException {
        return new TarArchiveOutputStream(fos);
    }

    @Override
    public ArchiveEntry getArchiveEntry(File inputFile, String entryName) {
        return new TarArchiveEntry(inputFile, entryName);
    }
}
