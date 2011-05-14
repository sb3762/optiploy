package com.optiploy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.EntityRef;
import org.jdom.JDOMException;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.optiploy.constants.Constants;


public class XmlHelper
{
    public static String getDynamicXmlMarkup(Element dynScript)
    {
        StringBuffer buff = new StringBuffer();

        List groups = new ArrayList();
        getAllElementsWithName(dynScript, "group", groups);

        if (groups.size() == 0)
            return null;

        buff.append("<table>\n");

        final int colwidth = 5;

        for (int i = 0; i < groups.size(); i++)
        {
            Element group = (Element) groups.get(i);
            String groupName = group.getAttributeValue("name");

            if (i % colwidth == 0)
            {
                buff.append("<tr>");
            }

            buff.append("<td><table width='100%' cellpadding='3' cellspacing='0' border='0'><tr><th align='left' bgcolor='aeaeae'>"
                    + groupName
                    + "</th></tr><tr><td>\n");

            List options = group.getChildren("option");
            for (int j = 0; j < options.size(); j++)
            {
                Element option = (Element) options.get(j);
                String optionName = option.getAttributeValue("name");

                buff.append("<input type='radio' name='"
                        + Constants.BUILD_ATTRIBUTE_START
                        + groupName
                        + "' value='"
                        + optionName
                        + "'"
                        + (j == 0 ? " checked='checked'" : "")
                        + "> "
                        + optionName
                        + "<br>\n");
            }

            buff.append("</td></tr></table></td>\n");

            if ((i + 1) % colwidth == 0)
            {
                buff.append("</tr>");
            }
        }
        buff.append("</table>");

        return buff.toString();
    }

    public static void getAllElementsWithName(Element root, String name, List list)
    {
        if (root.getName().equals(name))
        {
            list.add(root);
        }

        List children = root.getChildren();
        for (int i = 0; i < children.size(); i++)
        {
            getAllElementsWithName((Element) children.get(i), name, list);
        }
    }

    public static void getDynamicXML(Element dynScript, Map selections, StringBuffer buff)
    {
        List groups = new ArrayList();
        getAllElementsWithName(dynScript, "group", groups);

        if (groups.size() == 0)
        {
            buff.append(getElementString(dynScript));
        }

        else
        {
            buff.append("<" + dynScript.getName());
            List attributes = dynScript.getAttributes();
            for (int i = 0; i < attributes.size(); i++)
            {
                Attribute a = (Attribute) attributes.get(i);
                buff.append(" " + a.getName() + "=\"" + a.getValue() + "\"");
            }

            List content = dynScript.getContent();

            if (content.size() == 0)
            {
                buff.append("/>");
            }

            else
            {
                buff.append(">");

                for (int i = 0; i < content.size(); i++)
                {
                    Object contentVal = content.get(i);

                    if (contentVal instanceof Element)
                    {
                        Element child = (Element) content.get(i);

                        if (child.getName().equals("group"))
                        {
                            String groupName = child.getAttributeValue("name");
                            List options = child.getChildren("option");
                            String selectedOption = (String) selections.get(groupName);
                            Element selected = null;

                            for (int j = 0; j < options.size(); j++)
                            {
                                Element option = (Element) options.get(j);
                                String optionName = option.getAttributeValue("name");

                                if (optionName.equals(selectedOption))
                                    selected = option;
                            }

                            if (selected != null)
                                buff.append(getChildrenElementString(selected));
                        }

                        else
                        {
                            getDynamicXML(child, selections, buff);
                        }
                    }

                    else
                    {
                        buff.append(getElementString(contentVal));
                    }
                }

                buff.append("</" + dynScript.getName() + ">\n");
            }
        }
    }

    public static String getDynamicXML(Element dynScript, Map selections)
    {
        StringBuffer buff = new StringBuffer();
        buff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
        getDynamicXML(dynScript, selections, buff);
        return buff.toString();
    }

    public static String getFileString(File f) throws IOException
    {
        FileReader in = new FileReader(f);
        StringBuffer buff = new StringBuffer(10000);
        int inchar;
        while ((inchar = in.read()) != -1)
            buff.append((char) inchar);
        in.close();
        return buff.toString();
    }

    public static Element getRootElement(File f) throws JDOMException, IOException
    {
        return getRootElement(new FileInputStream(f));
    }

    public static Element getRootElement(URL f) throws JDOMException, IOException
    {
        return getRootElement(f.openStream());
    }

    public static Element getRootElement(InputStream f) throws JDOMException, IOException
    {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(f);
        return doc.getRootElement();
    }

    public static Element getRootElement(String xml) throws JDOMException, IOException
    {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new StringReader(xml));
        return doc.getRootElement();
    }

    public static Properties flatten(Element e)
    {
        Properties result = new Properties();
        flatten(e, "", result);
        return result;
    }

    private static void flatten(Element e, String view, Properties p)
    {
        Properties result = new Properties();
        String app = view + (view.equals("") ? "" : ".");
        List children = e.getChildren();

        if (e.getText() != null && children.size() == 0)
        {
            p.setProperty(view, e.getText());
        }

        for (int i = 0; i < children.size(); i++)
        {
            Element sube = (Element) children.get(i);
            flatten(sube, app + sube.getName(), p);
        }
    }

    public static String getChildrenElementString(Element e)
    {
        StringBuffer buff = new StringBuffer();
        List content = e.getContent();

        for (Iterator i = content.iterator(); i.hasNext();)
        {
            buff.append(getElementString(i.next()));
        }

        return buff.toString();
    }

    public static String getElementString(Object o)
    {
        XMLOutputter out = new XMLOutputter();

        if (o instanceof String)
            return (String) o;

        if (o instanceof Element)
        {
            return out.outputString((Element) o);
        }

        if (o instanceof Text)
        {
            return o.toString();
        }

        if (o instanceof Comment)
        {
            return out.outputString((Comment) o);
        }

        if (o instanceof ProcessingInstruction)
        {
            return out.outputString((ProcessingInstruction) o);
        }

        if (o instanceof CDATA)
        {
            return out.outputString((CDATA) o);
        }

        if (o instanceof EntityRef)
        {
            return out.outputString((EntityRef) o);
        }

        throw new IllegalArgumentException("Input type ("
                + o.getClass().getName()
                + ")must be one of the following types: Text, Element, Comment, "
                + "ProcessingInstruction, CDATA, and EntityRef");
    }
}
