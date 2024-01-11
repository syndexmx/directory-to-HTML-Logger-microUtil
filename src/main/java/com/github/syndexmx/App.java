package com.github.syndexmx;

import com.github.syndexmx.collector.DirectoryCollector;
import com.github.syndexmx.directoryutils.DirectoryWalker;
import com.github.syndexmx.templates.HtmlTemplate;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import static com.github.syndexmx.directoryutils.DirectoryWalker.cutDirectoryName;


public class App 
{
    public static void main( String[] args )
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String chosenDirectory = chooser.getSelectedFile().getAbsolutePath();
            DirectoryCollector collector = new DirectoryCollector("", "");
            try {
                collector.directoryHtmlAppend("<ul>\n");
                DirectoryWalker walker = new DirectoryWalker("", new File(chosenDirectory));
                collector.append(walker.walkOverSubdirectories());
                collector.directoryHtmlAppend("\n</ul>");
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML page File","html");
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File("."));
                fileChooser.setSelectedFile(new File(cutDirectoryName(chosenDirectory) + ".html"));
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    FileWriter output = new FileWriter(file);
                    BufferedWriter writer = new BufferedWriter(output);
                    writer.write(HtmlTemplate.HTML_START);
                    writer.write(collector.getDirectoryHtml());
                    writer.write(HtmlTemplate.HTML_MIDDLE);
                    writer.write(collector.getFilesHtml());
                    writer.write(HtmlTemplate.HTML_END);
                    writer.close();
                };
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
