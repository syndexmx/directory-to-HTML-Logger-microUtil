package com.github.syndexmx.collector;

public class DirectoryCollector {

    protected String directoryHtml = "";
    protected String filesHtml = "";

    public DirectoryCollector(String directoryHtml, String filesHtml) {
        this.directoryHtml = directoryHtml;
        this.filesHtml = filesHtml;
    }

    public void directoryHtmlAppend(String appended) {
        directoryHtml += appended;
    }

    public void filesHtmlAppend(String appended) {
        filesHtml += appended;
    }

    public void append(DirectoryCollector collector) {
        directoryHtml += collector.getDirectoryHtml();
        filesHtml += collector.getFilesHtml();
    }

    public String getDirectoryHtml() {
        return directoryHtml;
    }

    public void setDirectoryHtml(String directoryHtml) {
        this.directoryHtml = directoryHtml;
    }

    public String getFilesHtml() {
        return filesHtml;
    }

    public void setFilesHtml(String filesHtml) {
        this.filesHtml = filesHtml;
    }
}
