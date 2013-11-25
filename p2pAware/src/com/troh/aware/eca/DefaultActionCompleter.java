/**
 * 
 */
package com.troh.aware.eca;

import java.io.IOException;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.model.Model;
import com.troh.aware.util.TagsContainer;
import com.troh.aware.util.XMLUtility;

/**
 * @author tom
 *
 */
public class DefaultActionCompleter extends AbstractActionCompleter {
	private TagsContainer tagsContainer;
	private XMLUtility xmlUtility;
	private ActionPoster actionPoster;
	
	public DefaultActionCompleter(ActionPoster actionPoster, XMLUtility xmlUtility, TagsContainer tagsContainer) {
		this.tagsContainer = tagsContainer;
		this.xmlUtility = xmlUtility;
		this.actionPoster = actionPoster;
	}

	@Override
	public void completeAction(Model event, List<String> actionConditions) {
		completeAction(event,null, actionConditions);
	}

	@Override
	public void completeAction(Model event, Object[] data, List<String> actionConditions) {
		for (String each: actionConditions) {
			try {
				Document incompleteDocument = xmlUtility.buildDocument(each);
				NodeList handlers = findHandlerElements(incompleteDocument);
				if (completeWithHandlers(event,incompleteDocument, data, handlers)) {
					xmlUtility.removeAllNodes(handlers);
					actionPoster.post(incompleteDocument, data);
				}
			} catch (SAXException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private boolean completeWithHandlers(Model event,Document incompleteDocument, 
											Object[] data, NodeList handlers) {
		for (int i = 0; i < handlers.getLength(); i++) {
			ActionCompleterComponent currentComponent = getComponentForName(getHandlerName(handlers, i));
			if (!currentComponent.completeAction(event, data, incompleteDocument))
				return false;
		}
		return true;
	}

	private String getHandlerName(NodeList handlers, int index) {
		return handlers.item(index).getTextContent();
	}

	private NodeList findHandlerElements(Document incompleteDocument) {
		return incompleteDocument.getElementsByTagName(tagsContainer.getHandlerTag());
	}


}
