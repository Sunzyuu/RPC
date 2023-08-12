package com.github.sunzy.compress;

import com.github.sunzy.extension.SPI;

@SPI
public interface Compress {
    byte[] compress(byte[] bytes);
    byte[] decompress(byte[] bytes);
}
