/**
 * 
 */
package com.troh.aware.eca;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.troh.aware.net.SocketGroup;
import com.troh.aware.util.TagsContainer;
import com.troh.aware.util.XMLUtility;

/**
 * @author tom
 *
 */
public class DefaultActionPoster implements ActionPoster {
	private XMLUtility xmlUtility;
	private TagsContainer tagsContainer;
	private SocketGroup socketGroup;
	private ActionExecutor actionExecutor;
	
	public DefaultActionPoster(XMLUtility xmlUtility, TagsContainer tagsContainer,
							SocketGroup socketGroup, ActionExecutor actionExecutor) {
		this.xmlUtility = xmlUtility;
		this.tagsContainer = tagsContainer;
		this.socketGroup = socketGroup;
		this.actionExecutor = actionExecutor;
	}
	
	/* (non-Javadoc)
	 * @see com.troh.aware.eca.ActionPoster#post(org.w3c.dom.Document, java.lang.Object[])
	 */
	@Override
	public void post(Document document, Object[] data) {
		try {
			NodeList sendToNodes = retrieveSendToElements(document);
			List<String> recipients = getSendToContent(sendToNodes);
			xmlUtility.removeAllNodes(sendToNodes);
			String action = xmlUtility.transformToString(document);
			for (String recipient: recipients) {
				if (recipient.equals(tagsContainer.getSuperPeerName())) {
					actionExecutor.execute(action, data);
				} else {
					socketGroup.send(recipient, action, data);
				}
			}
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}
	}
	
	private List<String> getSendToContent(NodeList sendToNodes) {
		List<String> contents = new ArrayList<>();
		for (int i = 0; i < sendToNodes.getLength(); i++) {
			contents.add(sendToNodes.item(i).getTextContent());
		}
		return contents;
	}
	
	private NodeList retrieveSendToElements(Document document) {
		return document.getElementsByTagName(tagsContainer.getSendToTag());
	}

}
