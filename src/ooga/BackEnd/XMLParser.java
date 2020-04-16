package ooga.BackEnd;

import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.RailRoad;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XMLParser {

    public XMLParser() {}

    public void streetParser(String pathname) throws FileNotFoundException, XMLStreamException {
        ArrayList<Property> properties = null;
        Street s = null;
        String text = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File(pathname)));

        while (reader.hasNext()) {
            int Event = reader.next();

            switch (Event) {
                case XMLStreamConstants.START_ELEMENT: {
                    if ("street".equals(reader.getLocalName())) {
                        s = new Street();
                        s.setTileID(reader.getAttributeValue(0));
                    }
                    if ("properties".equals(reader.getLocalName()))
                        properties = new ArrayList<>();
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    text = reader.getText().trim();
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    switch (reader.getLocalName()) {
                        case "street": {
                            properties.add(s);
                            break;
                        }
                        case "boardIndex": {
                            s.setBoardIndex(Integer.parseInt(text));
                            break;
                        }
                        case "title_deed": {
                            s.setTitle(text);
                            break;
                        }
                        case "cost": {
                            s.setCost(Integer.parseInt(text));
                            break;
                        }
                        case "color": {
                            s.setGroupColor(text);
                            break;
                        }
                        case "group_number": {
                            s.setGroupNumber(Integer.parseInt(text));
                            break;
                        }
                        case "base_rent": {
                            s.setBaseRent(Integer.parseInt(text));
                        }
                        case "monopoly_rent": {
                            s.setMonopolyRent(Integer.parseInt(text));
                        }
                        case "rent_one_house": {
                            s.setRent1H(Integer.parseInt(text));
                        }
                        case "rent_two_house": {
                            s.setRent2H(Integer.parseInt(text));
                        }
                        case "rent_three_house": {
                            s.setRent3H(Integer.parseInt(text));
                        }
                        case "rent_four_house": {
                            s.setRent4H(Integer.parseInt(text));
                        }
                        case "rent_hotel": {
                            s.setRentHotel(Integer.parseInt(text));
                        }
                        case "house_cost": {
                            s.setHouseCost(Integer.parseInt(text));
                        }
                    }
                    break;
                }
            }
        }

        for (Property p : properties) {
            System.out.println(p.toString());
        }
    }
}