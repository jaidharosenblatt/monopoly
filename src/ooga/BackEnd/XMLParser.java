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

    public void propertyParser(String pathname) throws FileNotFoundException, XMLStreamException {
        ArrayList<Property> properties = null;
        Property prop = null;
        String text = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File(pathname)));

        while (reader.hasNext()) {
            int Event = reader.next();

            switch (Event) {
                case XMLStreamConstants.START_ELEMENT: {
                    if ("streets".equals(reader.getLocalName())) {
                        prop = new Street();
//                        prop.setID(reader.getAttributeValue(0));
                    }
                    if ("railroads".equals(reader.getLocalName())) {
                        prop = new RailRoad();
//                        prop.setID(reader.getAttributeValue(0));
                    }
                    if ("utilities".equals(reader.getLocalName())) {
                        prop = new Utility();
//                        prop.setID(reader.getAttributeValue(0));
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
                        case "Employee": {
                            employees.add(empl);
                            break;
                        }
                        case "Firstname": {
                            empl.setFirstname(text);
                            break;
                        }
                        case "Lastname": {
                            empl.setLastname(text);
                            break;
                        }
                        case "Age": {
                            empl.setAge(Integer.parseInt(text));
                            break;
                        }
                        case "Salary": {
                            empl.setSalary(Double.parseDouble(text));
                            break;
                        }
                    }
                    break;
                }
            }
        }

        // Print all employees.
        for (Employee employee : employees)
            System.out.println(employee.toString());
    }
}