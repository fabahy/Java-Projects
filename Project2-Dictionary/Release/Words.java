import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Words {
    public static HashMap <String, String> listEngVie = new HashMap<String, String>();
    public static HashMap <String, String> listVieEng = new HashMap<String, String>();
    public static ArrayList<String> listKeyEngVie = new ArrayList<String>();
    public static ArrayList<String> listKeyVieEng = new ArrayList<String>();
    public static ArrayList<String> listFav = new ArrayList<String>();
    public static ArrayList<History> historyList = new ArrayList<History>();
    public static final int MAX = 20;

    public void readEngVie() {
        try{
            File fileEngVie = new File(".\\resources\\Anh_Viet.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileEngVie);
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("record");
            for(int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elm = (Element)node;
                    String keyword = elm.getElementsByTagName("word").item(0).getTextContent();
                    listKeyEngVie.add(keyword);
                    String meaning = elm.getElementsByTagName("meaning").item(0).getTextContent();
                    listEngVie.put(keyword, meaning);
                }
            }
        }
        catch(Exception e){
            System.out.println("Read data from file failure !!!");
        }
    }

    public void readVieEng() {
        try {
            File fileVieEng = new File(".\\resources\\Viet_Anh.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc  = dBuilder.parse(fileVieEng);
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("record");
            for(int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elm = (Element)node;
                    String keyword;
                    keyword = elm.getElementsByTagName("word").item(0).getTextContent();
                    listKeyVieEng.add(keyword);
                    String meaning = elm.getElementsByTagName("meaning").item(0).getTextContent();
                    listVieEng.put(keyword, meaning);
                }
            }
        }
        catch(Exception e){
            System.out.println("Read data from file failure !!!");
        }
    }

    public void saveEngVieToXML(){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("dictionary");
            doc.appendChild(rootElement);


            for (HashMap.Entry<String, String> set :
                    listEngVie.entrySet()) {

                // record elements
                Element record = doc.createElement("record");
                rootElement.appendChild(record);

                // word elements
                Element word = doc.createElement("word");
                word.appendChild(doc.createTextNode(set.getKey()));
                record.appendChild(word);

                // meaning elements
                Element meaning = doc.createElement("meaning");
                meaning.appendChild(doc.createTextNode(set.getValue()));
                record.appendChild(meaning);
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(".\\resources\\Anh_Viet.xml"));
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveVieEngToXML(){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("dictionary");
            doc.appendChild(rootElement);


            for (HashMap.Entry<String, String> set :
                    listVieEng.entrySet()) {

                // record elements
                Element record = doc.createElement("record");
                rootElement.appendChild(record);

                // word elements
                Element word = doc.createElement("word");
                word.appendChild(doc.createTextNode(set.getKey()));
                record.appendChild(word);

                // meaning elements
                Element meaning = doc.createElement("meaning");
                meaning.appendChild(doc.createTextNode(set.getValue()));
                record.appendChild(meaning);
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(".\\resources\\Viet_Anh.xml"));
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Look up English -> Vietnamese
    public String lookupEngVie(String Key){
        return listEngVie.get(Key);
    }
    // Look up Vietnamese -> English
    public String lookupVieEng(String Key){
        return listVieEng.get(Key);
    }
    public void saveListFavouritesToFile(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".\\resources\\favourites.txt")));
            for (String fav : listFav) {
                bufferedWriter.write(fav);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readListFavouritesFromFile() {
        listFav.clear();
        String line = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\resources\\favourites.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                listFav.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveHistoryToFile(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".\\resources\\history.txt")));
            for (History history : historyList) {
                String oneLine = history.getKey() +
                        "," +
                        history.getDay() +
                        "," +
                        history.getMonth() +
                        "," +
                        history.getYear();
                bufferedWriter.write(oneLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readHistoryFromFile() {
        historyList.clear();
        String line = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\resources\\history.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                int day = Integer.parseInt(values[1]);
                int month = Integer.parseInt(values[2]);
                int year = Integer.parseInt(values[3]);
                History history = new History(values[0], day, month, year);
                historyList.add(history);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

