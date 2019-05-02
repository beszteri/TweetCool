package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLService {

    private SimpleDateFormat simpleDateFormat;
    private String filePath;
    private File file;



    public XMLService() {
        this.simpleDateFormat = new SimpleDateFormat();
        this.filePath = System.getenv("HOME") + "/Dokumentumok/Codecool/web/tweet/Tweets.xml";
        this.file = new File(filePath);

    }

    public void writeTweet(Tweet tweet) {
        try {
            List<Tweet> tweetList = readTweets();
            tweetList.add(tweet);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("tweets");
            document.appendChild(rootElement);

            for (Tweet t : tweetList) {
                Element newTweet = document.createElement("tweet");
                rootElement.appendChild(newTweet);

                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(String.valueOf(t.getId())));
                newTweet.appendChild(id);

                Element poster = document.createElement("poster");
                poster.appendChild(document.createTextNode(t.getPoster()));
                newTweet.appendChild(poster);

                Element content = document.createElement("content");
                content.appendChild(document.createTextNode(t.getContent()));
                newTweet.appendChild(content);

                Element timeStamp = document.createElement("timeStamp");
                timeStamp.appendChild(document.createTextNode(simpleDateFormat.format(t.getTimeStamp())));
                newTweet.appendChild(timeStamp);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(this.file);
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

        }
    }


    public List<Tweet> readTweets() {
        List<Tweet> tweetList = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filePath);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("tweet");

            for(int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String poster = element.getElementsByTagName("poster").item(0).getTextContent();
                    String content = element.getElementsByTagName("content").item(0).getTextContent();
                    Date timeStamp = simpleDateFormat.parse(element.getElementsByTagName("timeStamp").item(0).getTextContent());
                    Tweet tweet = new Tweet(id, poster, content, timeStamp);
                    tweetList.add(tweet);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweetList;
    }
}
