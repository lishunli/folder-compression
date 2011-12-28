package com.googlecode.usc.folder.compression;

/**
 *
 * @author ShunLi
 *
 *         http://commons.apache.org/compress/examples.html
 */
public enum CompressionType {
    ZIP(".zip"),
    CPIO(".cpio"),
    TAR(".tar"),
    JAR(".jar");

    private String suffix;

    private CompressionType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
