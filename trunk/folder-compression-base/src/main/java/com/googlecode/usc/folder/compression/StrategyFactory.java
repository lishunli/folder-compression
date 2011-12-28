package com.googlecode.usc.folder.compression;

import com.googlecode.usc.folder.compression.concrete.strategy.CpioStrategy;
import com.googlecode.usc.folder.compression.concrete.strategy.TarStrategy;
import com.googlecode.usc.folder.compression.concrete.strategy.ZipStrategy;

/**
 *
 * @author ShunLi
 */
public class StrategyFactory {
    public Strategy createStrategy(CompressionType compressionType) {
        Strategy strategy = new ZipStrategy();// default strategy

        if (CompressionType.ZIP == compressionType) {
            // no-op = default
        } else if (CompressionType.CPIO == compressionType) {
             strategy = new CpioStrategy();
        }else if (CompressionType.TAR == compressionType) {
             strategy = new TarStrategy();
        }

        return strategy;
    }
}
