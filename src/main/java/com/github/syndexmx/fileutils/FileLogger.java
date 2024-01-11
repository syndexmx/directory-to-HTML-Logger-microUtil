package com.github.syndexmx.fileutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileLogger {

    public static String prepareFileToLog(File file, String name) {
        StringBuilder htmlLog = new StringBuilder();
        htmlLog.append("<div name=\"");
        htmlLog.append(name);
        htmlLog.append("\" id=\"");
        htmlLog.append(name);
        htmlLog.append("\" style=\"display: none;\">");
        htmlLog.append("<h4 class=\"filename\">");
        htmlLog.append(file.toString());
        htmlLog.append("<span class=\"danger\" onclick=\"showIt('" +
                       name + "');\">x</span></h4>");
        htmlLog.append("<hr/>\n");
        htmlLog.append("<pre>\n");

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            String line = bReader.readLine();
            while (line != null) {
                htmlLog.append(htmlFriendyFlatten(line));
                htmlLog.append("\n");
                line = bReader.readLine();
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        htmlLog.append("\n</pre><hr/></div>\n");
        return htmlLog.toString();
    }

    private static String htmlFriendyFlatten(String line) {
        return line.replace("<","&lt;")
                .replace(">","&gt;");
    }
}
