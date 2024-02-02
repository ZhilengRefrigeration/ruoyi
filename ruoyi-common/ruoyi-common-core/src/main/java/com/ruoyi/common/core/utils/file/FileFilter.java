package com.ruoyi.common.core.utils.file;

import java.io.File;

/**
 * @author Alan Scipio
 * created on 2024/2/2
 */
@FunctionalInterface
public interface FileFilter {

    boolean accept(File file);

}
