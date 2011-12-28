package com.googlecode.usc.folder.compression.concrete.strategy;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.googlecode.usc.folder.compression.Strategy;
import com.googlecode.usc.folder.compression.utils.CompressionUtil;

/**
 *
 * @author ShunLi
 */
public class ZipStrategy extends Strategy {
    @Override
    public ArchiveOutputStream getArchiveOutputStream(FileOutputStream fos) throws IOException {
        ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(fos);
        return zipArchiveOutputStream;
    }

    @Override
    public ArchiveEntry getArchiveEntry(File inputFile, String entryName) {
        return new ZipArchiveEntry(inputFile, entryName);
    }

    @Override
    public void doCompress(File[] files, File out, List<String> excludedKeys) {
        System.out.println("Use custom method");
        Map<String, File> map = new HashMap<String, File>();
        String parent = FilenameUtils.getBaseName(out.getName());

        for (File f : files) {
            CompressionUtil.list(f, parent, map, excludedKeys);
        }

        if (!map.isEmpty()) {
            FileOutputStream fos = null;
            ArchiveOutputStream aos = null;
            BufferedInputStream fs = null;
            InputStream is = null;

            try {
                fos = new FileOutputStream(out);
                aos = getArchiveOutputStream(new FileOutputStream(out));
                String encoding = "UTF-8";

                for (Map.Entry<String, File> entry : map.entrySet()) {
                    File file = entry.getValue();

                    if (file.isFile()) {
                        fs = new BufferedInputStream(new FileInputStream(file));

                        ArchiveInputStream zs = new ZipArchiveInputStream(fs, encoding, true, true);

                        ArchiveEntry ae = null;
                        while ((ae = zs.getNextEntry()) != null) {
                            aos.putArchiveEntry(ae);
                        }

                        IOUtils.copy(is = new FileInputStream(file), aos);

                        IOUtils.closeQuietly(is);
                        IOUtils.closeQuietly(fs);
                        is = null;
                        fs = null;

                        aos.closeArchiveEntry();
                    }

                }

                aos.finish();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                IOUtils.closeQuietly(is);
                IOUtils.closeQuietly(aos);
                IOUtils.closeQuietly(fos);
            }
        }
    }

}
