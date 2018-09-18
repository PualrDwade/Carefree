package com.csu.carefree.Util;

/**
 * 实现HTML标签转译
 * */
public class HtmlEncode {

    public String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
            case '<':
                buffer.append("&lt;");
                break;
            case '>':
                buffer.append("&gt;");
                break;
            case '&':
                buffer.append("&amp;");
                break;
            case '"':
                buffer.append("&quot;");
                break;
            case 13:
                break;
            default:
                buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }
}