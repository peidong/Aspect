package com.xqc.xplan.xspart.excel2sql.service;

import java.io.*;

/**
 * write data to destination.
 * <p>
 * attention: print is informal,must be log.
 */
public class SaveDataToFile {
    public void appendFile(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件,如果为 true，则将字节写入文件末尾处，而不是写入文件开始处
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}