package ooga.BackEnd.GameLogic;

import ooga.BackEnd.GameObjects.Tiles.EventTiles.*;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.RailRoad;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Utility;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author rodrigoaraujo handles parsing XML files. streetParser, railroadParser, utilityParser,
 * and eventTileParser could be combined into one method. However, that method would be unnecessarily
 * long. Structure was duplicated to keep organization.
 */

public class XMLParser {

    public ArrayList<Street> streets;
    public ArrayList<RailRoad> railroads;
    public ArrayList<Utility> utilities;
    public ArrayList<Property> properties;
    public ArrayList<Event> eventTiles;
    public ArrayList<Tile> allTiles;

    /**
     * Handles parsing of XML files into Lists of the boardgame with
     * appropriate values
     *
     * @param pathname url of XML file for game
     */

    public XMLParser(String pathname) throws FileNotFoundException, XMLStreamException {
        this.properties = propertySetUp(pathname);
        this.eventTiles = eventSetUp(pathname);
        this.allTiles = new ArrayList<>();
        this.allTiles.addAll(properties);
        this.allTiles.addAll(eventTiles);
        Collections.sort(allTiles, Comparator.comparingInt(Tile::getBoardIndex));
    }

    private ArrayList<Property> propertySetUp(String pathname) throws FileNotFoundException, XMLStreamException {
        this.streets = streetParser(pathname);
        this.railroads = railroadParser(pathname);
        this.utilities = utilityParser(pathname);
        ArrayList<Property> properties = new ArrayList<>();
        properties.addAll(streets);
        properties.addAll(railroads);
        properties.addAll(utilities);
        return properties;
    }

    private ArrayList<Event> eventSetUp(String pathname) throws FileNotFoundException, XMLStreamException {
        ArrayList<Event> eventTiles = eventTileParser(pathname);
        return eventTiles;
    }

    private ArrayList<Street> streetParser(String pathname) throws FileNotFoundException, XMLStreamException {
        ArrayList<Street> streets = null;
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
                        streets = new ArrayList<>();
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    text = reader.getText().trim();
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    switch (reader.getLocalName()) {
                        case "street": {
                            streets.add(s);
                            break;
                        }
                        case "boardIndex": {
                            if (s.getBoardIndex() == 0) {s.setBoardIndex(Integer.parseInt(text));}
                            break;
                        }
                        case "title_deed": {
                            if (s.getTitle() == null) {s.setTitle(text);}
                            break;
                        }
                        case "cost": {
                            if (s.getCost() == 0) {s.setCost(Integer.parseInt(text));}
                            break;
                        }
                        case "color": {
                            if (s.getGroupColor() == null) {s.setGroupColor(text);}
                            break;
                        }
                        case "group_number": {
                            if (s.getGroupNumber() == 0) {s.setGroupNumber(Integer.parseInt(text));}
                            break;
                        }
                        case "base_rent": {
                            if (s.getBaseRent() == 0) {s.setBaseRent(Integer.parseInt(text));}
                            break;
                        }
                        case "monopoly_rent": {
                            if (s.getMonopolyRent() == 0) {s.setMonopolyRent(Integer.parseInt(text));}
                            break;
                        }
                        case "rent_one_house": {
                            if (s.getRent1H() == 0) {s.setRent1H(Integer.parseInt(text));}
                            break;
                        }
                        case "rent_two_house": {
                            if (s.getRent2H() == 0) {s.setRent2H(Integer.parseInt(text));}
                            break;
                        }
                        case "rent_three_house": {
                            if (s.getRent3H() == 0) {s.setRent3H(Integer.parseInt(text));}
                            break;
                        }
                        case "rent_four_house": {
                            if (s.getRent4H() == 0) {s.setRent4H(Integer.parseInt(text));}
                            break;
                        }
                        case "rent_hotel": {
                            if (s.getRentHotel() == 0) {s.setRentHotel(Integer.parseInt(text));}
                            break;
                        }
                        case "house_cost": {
                            if (s.getHouseCost() == 0) {s.setHouseCost(Integer.parseInt(text));}
                            break;
                        }
                    }
                    break;
                }
            }
        }

        return streets;
    }

    private ArrayList<RailRoad> railroadParser(String pathname) throws FileNotFoundException, XMLStreamException {
        ArrayList<RailRoad> railroads = null;
        RailRoad r = null;
        String text = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File(pathname)));

        while (reader.hasNext()) {
            int Event = reader.next();

            switch (Event) {
                case XMLStreamConstants.START_ELEMENT: {
                    if ("street".equals(reader.getLocalName()) || "railroad".equals(reader.getLocalName())) {
                        r = new RailRoad();
                        r.setTileID(reader.getAttributeValue(0));
                        break;
                    }
                    if ("properties".equals(reader.getLocalName()))
                        railroads = new ArrayList<>();
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    text = reader.getText().trim();
                    if (text.equals("O. Railroad")) { //ampersand causes xml to create new lines. #BANDAID
                        text = "B. & O. Railroad";
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    switch (reader.getLocalName()) {
                        case "railroad": {
                            r.setPathname("rail.gif");
                            railroads.add(r);
                            break;
                        }
                        case "boardIndex": {
                            if (r.getBoardIndex() == 0) {r.setBoardIndex(Integer.parseInt(text));}
                            break;
                        }
                        case "title_deed": {
                            if (r.getTitle() == null) {r.setTitle(text);}
                            break;
                        }
                        case "cost": {
                            if (r.getCost() == 0) {r.setCost(Integer.parseInt(text));}
                            break;
                        }
                        case "color": {
                            if (r.getGroupColor() == null) {r.setGroupColor(text);}
                            break;
                        }
                        case "group_number": {
                            if (r.getGroupNumber() == 0) {r.setGroupNumber(Integer.parseInt(text));}
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return railroads;
    }

    private ArrayList<Utility> utilityParser(String pathname) throws FileNotFoundException, XMLStreamException {
        ArrayList<Utility> utilities = null;
        Utility u = null;
        String text = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File(pathname)));

        while (reader.hasNext()) {
            int Event = reader.next();

            switch (Event) {
                case XMLStreamConstants.START_ELEMENT: {
                    if ("street".equals(reader.getLocalName()) || "railroad".equals(reader.getLocalName()) || "utility".equals(reader.getLocalName())) {
                        u = new Utility();
                        u.setTileID(reader.getAttributeValue(0));
                    }
                    if ("properties".equals(reader.getLocalName()))
                        utilities = new ArrayList<>();
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    text = reader.getText().trim();
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    switch (reader.getLocalName()) {
                        case "utility": {
                            if (u.getTitle().equals("Electric Company") || u.getTitle().equals("Telescope") || u.getTitle().equals("Solar Power")) {
                                u.setPathname("electric.png");
                            }
                            if (u.getTitle().equals("Water Works") || u.getTitle().equals("Astronauts") || u.getTitle().equals("Wind Power")) {
                                u.setPathname("waterworks.png");
                            }
                            utilities.add(u);
                            break;
                        }
                        case "boardIndex": {
                            if (u.getBoardIndex() == 0) {u.setBoardIndex(Integer.parseInt(text));}
                            break;
                        }
                        case "title_deed": {
                            if (u.getTitle() == null) {u.setTitle(text);}
                            break;
                        }
                        case "cost": {
                            if (u.getCost() == 0) {u.setCost(Integer.parseInt(text));}
                            break;
                        }
                        case "color": {
                            if (u.getGroupColor() == null) {u.setGroupColor(text);}
                            break;
                        }
                        case "group_number": {
                            if (u.getGroupNumber() == 0) {u.setGroupNumber(Integer.parseInt(text));}
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return utilities;
    }

    private ArrayList<Event> eventTileParser(String pathname) throws FileNotFoundException, XMLStreamException {
        ArrayList<Event> eventTiles = null;
        Go g = null;
        Jail j = null;
        Tax t = null;
        FreeParking fp = null;
        GoToJail gtj = null;
        CardTile ct = null;
        String text = null;
        String name = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File(pathname)));

        while (reader.hasNext()) {
            int Event = reader.next();
            switch (Event) {
                case XMLStreamConstants.START_ELEMENT: {
                    if (reader.getLocalName().equals("name")) {name = "name";}
                    if ("event".equals(reader.getLocalName()) || "properties".equals(reader.getLocalName()) || "street".equals(reader.getLocalName()) || "railroad".equals(reader.getLocalName()) || "utility".equals(reader.getLocalName())) {
                        g = new Go();
                        g.setTileID(reader.getAttributeValue(0));
                        j = new Jail();
                        j.setTileID(reader.getAttributeValue(0));
                        t = new Tax();
                        t.setTileID(reader.getAttributeValue(0));
                        fp = new FreeParking();
                        fp.setTileID(reader.getAttributeValue(0));
                        gtj = new GoToJail();
                        gtj.setTileID(reader.getAttributeValue(0));
                        ct = new CardTile();
                        ct.setTileID(reader.getAttributeValue(0));
                    }
                    if ("eventTiles".equals(reader.getLocalName()))
                        eventTiles = new ArrayList<>();
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    if (name == "name") {name = reader.getText().trim();}
                    text = reader.getText().trim();
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    switch (reader.getLocalName()) {
                        case "event": {
                            if (name.equals("go")) {
                                g.setPathname("go.png");
                                g.setBName("Go");
                                eventTiles.add(g);
                            }
                            if (name.equals("jail")) {
                                j.setPathname("jail.png");
                                j.setBName("Jail");
                                eventTiles.add(j);
                            }
                            if (name.equals("tax")) {
                                if (t.getTax() == 75) {
                                    t.setPathname("luxury.png");
                                    t.setBName("Luxury Tax");
                                }
                                else {
                                    t.setPathname("incometax.png");
                                    t.setBName("Income Tax");
                                }
                                eventTiles.add(t);
                            }
                            if (name.equals("free_parking")) {
                                fp.setPathname("freeparking.png");
                                fp.setBName("Free Parking");
                                eventTiles.add(fp);
                            }
                            if (name.equals("go_to_jail")) {
                                gtj.setPathname("gotojail.png");
                                gtj.setBName("Go To Jail");
                                eventTiles.add(gtj);
                            }
                            if (name.equals("card")) {
                                if (ct.getType().equals("Community")) {
                                    ct.setPathname("chest.png");
                                    ct.setBName("Community Chest");
                                }
                                if (ct.getType().equals("Chance")) {
                                    ct.setPathname("chance.png");
                                    ct.setBName("Chance");
                                }
                                eventTiles.add(ct);
                            }
                            name = null;
                            break;
                        }
                        case "boardIndex": {
                            if (g.getBoardIndex() == 0) {g.setBoardIndex(Integer.parseInt(text));}
                            if (j.getBoardIndex() == 0) {j.setBoardIndex(Integer.parseInt(text));}
                            if (t.getBoardIndex() == 0) {t.setBoardIndex(Integer.parseInt(text));}
                            if (fp.getBoardIndex() == 0) {fp.setBoardIndex(Integer.parseInt(text));}
                            if (gtj.getBoardIndex() == 0) {gtj.setBoardIndex(Integer.parseInt(text));}
                            if (ct.getBoardIndex() == 0) {ct.setBoardIndex(Integer.parseInt(text));}
                            break;
                        }
                        case "name": {
                            if (g.getName() == null) {g.setName(text);}
                            if (j.getName() == null) {j.setName(text);}
                            if (t.getName() == null) {t.setName(text);}
                            if (fp.getName() == null) {fp.setName(text);}
                            if (gtj.getName() == null) {gtj.setName(text);}
                            if (ct.getName() == null) {ct.setName(text);}
                            break;
                        }
                        case "tax_cost": {
                            if (t.getTax() == 0) {t.setTax(Integer.parseInt(text));}
                            break;
                        }
                        case "type": {
                            if (ct.getType() == null) {ct.setType(text);}
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return eventTiles;
    }

}