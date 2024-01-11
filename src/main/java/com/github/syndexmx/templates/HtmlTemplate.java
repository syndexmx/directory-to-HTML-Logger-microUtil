package com.github.syndexmx.templates;

public class HtmlTemplate {

    public static String HTML_START = "<html lang=\"en\">\n" +
                                      "<head>\n" +
                                      " <meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=\"utf-8\">\n" +
                                      " <title></title>\n" +
                                      "<style>\n" +
                                      "    body {\n" +
                                      "    background-color : #202020;\n" +
                                      "    color : darkgray;\n" +
                                      "    }\n" +
                                      "    .tree {\n" +
                                      "        color : lightslategray;\n" +
                                      "    }\n" +
                                      "    .filename {\n" +
                                      "        color : lime;\n" +
                                      "    }\n" +
                                      "    .viewable {\n" +
                                      "        color : lime;\n" +
                                      "    }\n" +
                                      "    .comments {\n" +
                                      "        color : yellow;\n" +
                                      "    }\n" +
                                      "    .danger {\n" +
                                      "        color : red;\n" +
                                      "    }\n" +
                                      "    .highlight {\n" +
                                      "        color : white;\n" +
                                      "        font-weight: bold;\n" +
                                      "    }\n" +
                                      "</style>\n" +
                                      " <script type=\"text/javascript\">\n" +
                                      "\n" +
                                      "     function showIt(id){\n" +
                                      "           var x = document.getElementById(id);\n" +
                                      "              if (x.style.display === \"none\") {\n" +
                                      "                x.style.display = \"block\";\n" +
                                      "              } else {\n" +
                                      "                x.style.display = \"none\";\n" +
                                      "              }\n" +
                                      "     };\n" +
                                      " </script>\n" +
                                      "</head>\n" +
                                      "<body>\n" +
                                      "<div style=\"width: 25%; float:left;\">\n";

    public static String HTML_MIDDLE = "</div>\n" +
                                       "<div style=\"width: 75%; float:right;\">  \n";

    public static String HTML_END = "</div>\n" +
                                    "    \n" +
                                    "</body>\n" +
                                    "</html>\n";

}
