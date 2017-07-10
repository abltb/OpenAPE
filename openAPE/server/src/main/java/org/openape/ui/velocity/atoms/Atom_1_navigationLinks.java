package org.openape.ui.velocity.atoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.bouncycastle.crypto.engines.NaccacheSternEngine;
import org.openape.ui.velocity.models.Navigation;

public class Atom_1_navigationLinks {
	public LinkedHashMap<String, String> generateTopNavigationLinks(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
	
		map.put("GettingStarted", "Getting Started");
		map.put("Tutorials", "Tutorials");
		map.put("Downloads", "Downloads");
		map.put("Context", "Context");
		map.put("Contact", "Contact");
	
		return map;
	}
	
	public LinkedHashMap<String, String> generateSubNavigationLinks(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
		map.put("MyProfile", "My Profile");
		map.put("MyContexts", "My Contexts");
		map.put("MyResources", "My Resources");
		map.put("MyGroups", "My Groups");
		map.put("Administration", "Administration");
	
		return map;
	}
	
	public ArrayList<String> generateUserSectionLinksLoggedOut(){
		ArrayList<String> listLinks = new ArrayList<>();
		listLinks.add("<a href='#' id='signin'> <div class='glyphicon glyphicon-log-in'></div> Sign in</a>");
		listLinks.add("<a href='#' id='signup'><div class='glyphicon glyphicon-ok'></div> Sign up</a>");	
		return listLinks;
	}
}
