package com.github.syndexmx.directoryutils;

import com.github.syndexmx.collector.DirectoryCollector;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.github.syndexmx.fileutils.FileLogger.prepareFileToLog;

public class DirectoryWalker {

    String prefix;
    File directoryName;
    static final List<String> DIR_IGNORE_LIST =  Arrays.asList(".git");

    static final List<String> FILES_PROCESSED_LIST =  Arrays.asList(
            ".java",
            ".html", ".css", ".js",
            ".xml",
            ".properties",
            ".txt", ".md");
    static final String DIR_INTRO_BEFORE_NAME="<li><details><summary>";
    static final String DIR_INTRO_AFTER_NAME="</summary><ul>";
    static final String DIR_OUTRO="</ul></details></li>";

    public DirectoryWalker(String prefix, File directoryName) {
        this.prefix = prefix;
        this.directoryName = directoryName;
    }

    public DirectoryCollector walkOverSubdirectories() throws Exception {
        StringBuilder addedDirectory = new StringBuilder(prefix + DIR_INTRO_BEFORE_NAME + "\n");
        addedDirectory.append(prefix + cutDirectoryName(directoryName.toString()));
        addedDirectory.append(DIR_INTRO_AFTER_NAME + "\n");
        StringBuilder addedFiles = new StringBuilder();
        for(File file : directoryName.listFiles()) {
            if(file.isDirectory()) {
                boolean isSkippable = false;
                for (String s : DIR_IGNORE_LIST){
                    if (s.equals(cutDirectoryName(file.toString()))) isSkippable = true;
                }
                if (!isSkippable) {
                    DirectoryWalker subWalker = new DirectoryWalker (prefix + "\t", file);
                    DirectoryCollector collector = subWalker.walkOverSubdirectories();
                    addedDirectory.append(collector.getDirectoryHtml() + "\n");
                    addedFiles.append(collector.getFilesHtml()+"\n");
                }
            }
            else if(file.isFile()) {
                boolean isProcessedType = false;
                for (String s : FILES_PROCESSED_LIST){
                    if (cutDirectoryName(file.toString()).contains(s)) isProcessedType = true;
                }
                if (isProcessedType) {
                    System.out.println(file.toString());
                }
                addedDirectory.append(prefix + "\t" + "<li");
                if (isProcessedType) {
                    String flattenedPath = flattenPath(file.toString());
                    addedDirectory.append(" class=\"viewable\" onclick=\"showIt('" +
                                          flattenedPath + "');\"");
                    addedFiles.append(prepareFileToLog(file, flattenedPath));
                }
                addedDirectory.append(">" + "\n");
                addedDirectory.append(prefix + "\t" + file.getName() + "\n");
                addedDirectory.append(prefix + "\t" + "</li>" + "\n");

            }
        }
        addedDirectory.append(prefix + DIR_OUTRO + "\n");
        return new DirectoryCollector(addedDirectory.toString(), addedFiles.toString());
    }

    public static String cutDirectoryName(String fullPath) {
        return fullPath.substring(fullPath.lastIndexOf(File.separator)+1);
    }

    public static String flattenPath(String fullPath) {
        return fullPath.replace(File.separator, "__")
                .replace(":","_")
                .replace(".","_");
    }

}
