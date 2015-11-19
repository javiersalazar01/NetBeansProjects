package xmldemo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

public class Echo01 extends DefaultHandler {


    public static void main(String argv[]) {
      


        // Use an instance of ourselves as the SAX event handler
        DefaultHandler handler = new Echo01();
        // Use the default (non-validating) parser
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            // Set up output stream
            out = new OutputStreamWriter(System.out, "UTF8");

            // Parse the input
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(new File("MLB.xml"), handler);


        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.exit(0);
    }

    static private Writer out;

    //===========================================================
    // SAX DocumentHandler methods

    // Se Ejecuta al iniciar a leer el documento.    
    //===========================================================
    public void startDocument()
            throws SAXException {

        emit("<?xml version='1.0' encoding='UTF-8'?>");
        nl();
    }

    public void endDocument()
            throws SAXException {

        try {
            nl();
            out.flush();
        } catch (IOException e) {
            throw new SAXException("I/O error", e);
        }
    }

    
    private boolean isPlayer = false;
    private boolean isTeamName = false;
    private boolean isTeam = false;
    private boolean isMyTeam = false;
    private String teamToSearch = "Yankees";
    private String estadistica = "STRUCK_OUT_BATTER";
    private boolean factorFlag = false;
    private ArrayList<Integer> runList = new ArrayList<Integer>();
    
    
    //metodo que se manda a invocar cuando el se detecta una etiquta de inico de xml, ejemplo: <item>
    public void startElement(String namespaceURI,
            String lName, // local name
            String qName, // qualified name
            Attributes attrs)
            throws SAXException {

        String eName = lName; // element name
        if ("".equals(eName)) {
            eName = qName; // namespaceAware = false
        }
        

        if (eName.equalsIgnoreCase("TEAM")) {
            isTeam= true;
        }
        
        if (eName.equalsIgnoreCase("TEAM_NAME")) {
            isTeamName = true;
        }
        
        
        if (eName.equalsIgnoreCase("player")) {
            isPlayer = true;
        }
        
        if (eName.equalsIgnoreCase(estadistica)) {
            factorFlag = true;
        }
    }
    
    
    public double average (Integer data[]){
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum = sum + data[i];
        }
        return sum/data.length;
    }
    
    public double desvicionEst(Integer data[],double prom){
        double sum = 0;
        double resta;
        for (int i = 0; i < 10; i++) {
            resta = data[i] - prom;
            sum = sum +  Math.pow(resta, 2.0);
            
        }
        return sum / data.length;
    }
    
    
    //metodo que se manda a invocar cuando el se detecta una etiquta de inico de xml, ejemplo: <item/>
    public void endElement(String namespaceURI,
            String sName, // simple name
            String qName // qualified name
    )
            throws SAXException {
        
        if (qName.equalsIgnoreCase("TEAM")) {
            
            isTeam= false;
            
            if (isMyTeam) {
                Object [] arrRuns  = runList.toArray();
            
                double prom = 0,dE = 0;

                Integer[] integerArray = Arrays.copyOf(arrRuns, arrRuns.length, Integer[].class);

                prom = average(integerArray);

                dE = desvicionEst(integerArray, prom);

                emit("Promedio de " + estadistica + ": " + prom);
                nl();
                emit("Desviacion estandar de " + estadistica + ": " + Math.sqrt(dE));
            }
            
            isMyTeam = false;
        }
        
        if (qName.equalsIgnoreCase("TEAM_NAME")) {
            isTeamName = false;
        }
        
        
        if (qName.equalsIgnoreCase("PLAYER")) {
            isPlayer = false;
            if (isMyTeam) {
                nl();
            }
            
        }
        
        if (qName.equalsIgnoreCase(estadistica)) {
            factorFlag = false;
        }
        
        
       
    }

    //metodo que se manda a invocar al detectar el contenido dentro de una etiquet xml.
    public void characters(char buf[], int offset, int len)
            throws SAXException {
        String s = new String(buf, offset, len);
        
        
        if (isTeamName && s.equalsIgnoreCase(teamToSearch)) {
            isMyTeam = true;
            
            emit(s);
            nl();
        }
        
        if (isMyTeam && isPlayer ) {
            emit(s + "\t");
            if (factorFlag) {
                runList.add(Integer.parseInt(s));
            }
        } 
        
        

    }

    //===========================================================
    // Utility Methods ...
    //===========================================================
    // Wrap I/O exceptions in SAX exceptions, to
    // suit handler signature requirements

    private void emit(String s)
            throws SAXException {

        try {
            out.write(s);
            out.flush();
        } catch (IOException e) {
            throw new SAXException("I/O error", e);
        }
    }

    // Start a new line

    private void nl()
            throws SAXException {

        String lineEnd = System.getProperty("line.separator");
        try {
            out.write(lineEnd);
        } catch (IOException e) {
            throw new SAXException("I/O error", e);
        }
    }
}