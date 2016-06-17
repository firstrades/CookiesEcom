package ecom.Interface.Courier;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.xml.sax.SAXException;

public interface TrackByNumberInterface {

	public boolean getTrackingStatus(long orderTableId) throws UnsupportedOperationException, SOAPException, IOException, ParserConfigurationException, SAXException, ParseException;
}
