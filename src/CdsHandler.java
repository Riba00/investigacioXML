import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CdsHandler extends DefaultHandler {

    // CDS LIST
    private List<CD> cdList = null;
    private CD cd = null;
    private StringBuilder data = null;

    // METHOD TO GET THE CDS
    public List<CD> getCdList() {
        return cdList;
    }

    boolean bTitle = false;
    boolean bArtist = false;
    boolean bCountry = false;
    boolean bCompany = false;
    boolean bPrice = false;
    boolean bYear = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("CD")) {
            cd = new CD();
            // LIST INIT
            if (cdList == null)
                cdList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("title")) {
            // IDENTIFY THE ELEMENTS
            bTitle = true;
        } else if (qName.equalsIgnoreCase("artist")) {
            bArtist = true;
        } else if (qName.equalsIgnoreCase("country")) {
            bCountry = true;
        } else if (qName.equalsIgnoreCase("company")) {
            bCompany = true;
        } else if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
        } else if (qName.equalsIgnoreCase("year")) {
            bYear = true;
        }
        // DATA CONTAINER
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bTitle) {
            cd.setTitle(data.toString());
            bTitle = false;
        } else if (bArtist) {
            cd.setArtist(data.toString());
            bArtist = false;
        } else if (bCompany) {
            cd.setCompany(data.toString());
            bCompany = false;
        } else if (bCountry) {
            cd.setCountry(data.toString());
            bCountry = false;
        } else if (bPrice) {
            cd.setPrice(Double.parseDouble(data.toString()));
            bCountry = false;
        } else if (bYear) {
            cd.setYear(Integer.parseInt(data.toString()));
            bCountry = false;
        }

        if (qName.equalsIgnoreCase("CD")) {
            // ADD CD TO LIST
            cdList.add(cd);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}