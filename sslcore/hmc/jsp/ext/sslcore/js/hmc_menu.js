//This Menu function will hide "AcceleratorProductBOGOFPromotion" and "Buy X get Y free" from create promotion menu.
/*
	The Menu object represents the "outer hull" of one menu instance.
	It contains menu and sub menu entries which will be rendered in this menu.
	So the Menu object is responsible for the actual rendering of all the DIVs, TABLEs etc. and the correct
	(absolute) positioning the whole lot.
	Also you can control the appearance of the menu (colors etc.) by changing some of the Menu member variables.
	
	Parameters:
	
	menuEntries		-	An array containing all the entries for this menu.
	sourceEvent 	-	For all non-IE browsers this is the means to provide the event that triggered the menu creation. In IE this would
							be undefined because it provides a global 'event' variable.
	sourceElement 	-	This may contain the html element which has been clicked to show this menu. If 'sourceElement' contains an element
							it will be used for a relative positioning of this menu below the given element. In essence this results in
							a drop down menu.
	title				-	If title contains a string it will be shown as the menu's title.
	options			-	An associative array which may contain one or more parameter with which you can alter the menu's look.
*/
function Menu(menuEntries, sourceEvent, sourceElement, title, options)
{
	if( options == null )
	{
		// create an empty array to make things easier during the following parameter settings
		options = { };
	}
	
	// options
	this.width = options.width != null ? options.width : "150px";														// width of the menu
	this.rightAlign = options.rightAlign != null ? options.rightAlign : false;										// set this to true to get a right-aligned menu (default is left-aligned)
	this.className = options.className != null ? options.className : "dropdown-main";							// name of the css class to use for this menu
	this.uniqueName = options.uniqueName;																						// the uniqueName (of the parent's webhchip) should help for selenium recordings (making the clickable entries uniquely identifyable)
		
	this.menuEntries = menuEntries;			// these are the menu entry objects
	this.title = title;							// the title of this menu (may be null or empty)
														
	this.theMenuElement = null;				// this will eventually contain the actual <div> element containing the menu which is later shown on screen
	this.theIframeElement = null;				// this will contain the iframe element which is used as a hack to show this menu on top of select boxes
	this.canRemove = false;						// this is used to decide if the menu may be removed in hide() because the onclick event handler is used
														// to remove the menu, but it is also invoked when showing the menu initially

	this.subMenus = new Array();				// will contain all sub menu objects
	this.ID = idCounter++;						// is used to put all (sub-)menus into an array to have a unique reference using an index

	
	this.sourceEvent = sourceEvent;			// contains the source event, see parameter description above
	this.sourceElement = sourceElement;		// contains the source element, see parameter description above

	/*
		createMenu() creates the actual div which contains the menu shown on the sceen.
		
		Parameters:
		
		sourceEvent 	-	For all non-IE browsers this is the means to provide the event that triggered the menu creation. In IE this would
								be undefined because it provides a global 'event' variable.
		sourceElement 	-	This may contain the html element which has been clicked to show this menu. If 'sourceElement' contains an element
								it will be used for a relative positioning of this menu below the given element. In essence this results in
								a drop down menu.
	*/
	this.createMenu = function(sourceEvent, sourceElement)
		{
			var menuElement;
			var menuHTML = "";
			var entries = this.menuEntries;
				
			menuHTML += "<table style=\"width: " + this.width + ";\">";
			if( this.title && this.title != "" )
			{
				// show menu title
				menuHTML += "<tr><td class=\"title\">" + this.title + "</td></tr>";
			}
		
			for( var i = 0; i < entries.length; i++ )
			{
				var isSplitter = entries[i].isSplitter;
				var showIcon = entries[i].hasIcon();
				var icon = showIcon ? entries[i].icon : null;
				var disabled = !entries[i].enabled;
				var hasSubMenu = entries[i].hasSubMenu();
				var bottomAlign = entries[i].isSubMenuBottomAlign;
				var hasConfirmMessage = entries[i].hasConfirmMessage();
				var confirmMessage = entries[i].confirmMessage;
				var entryEvent = entries[i].entryEvent;
				var entryValue = entries[i].entryValue;
				var entryName = entries[i].name;
				var elementID = (this.uniqueName != null ? this.uniqueName : "") + "_" + y_getEventName(entryEvent) + "_" + entryValue;
				
				if(entryName.indexOf("AcceleratorProductBOGOFPromotion") >= 0 || entryName.indexOf("Buy X get Y free") >= 0) {
					continue;
				}
				if(entryName.indexOf("SSL BOGO Promotion") >= 0 && entryName.indexOf("&nbsp;&nbsp;") == 0) {
					entryName = entryName.substring(12);
				}
								
				// create sub menu if necessary
				var subMenuID = -1;
				if( hasSubMenu )
				{
					var subMenu = this.createSubMenu(entries[i].menuEntries, bottomAlign);
					subMenuID = subMenu.ID;
				}

				if( !disabled )
				{
					menuHTML += "<tr";
					menuHTML += " id=\"" + elementID + "_tr\"";
					menuHTML += " onmouseover=\"Element.addClassName(this, 'highlight');";
					if( hasSubMenu )
					{
						// show the selected submenu, SubMenu.show() will implicitly hide all other submenus of this menu
						menuHTML += "menus[" + subMenuID + "].show(this);";
					}
					else
					{
						// attempt to hide all submenus of this menu
						menuHTML += "menus[" + this.ID + "].hideSubMenus();";
					}
					menuHTML += "\"";
					menuHTML += " onmouseout=\"Element.removeClassName(this, 'highlight');\"";
					if( !hasSubMenu )
					{
						if( !hasConfirmMessage )
						{
							menuHTML += " onclick=\"document.editorForm.elements['" + entryEvent + "'].value='" + entryValue + "'; setScrollAndSubmit(); return false;\"";
						}
						else
						{
							// show confirmation message
							menuHTML += " onclick=\"if( confirm('" + confirmMessage + "') ) { document.editorForm.elements['" + entryEvent + "'].value='" + entryValue + "'; setScrollAndSubmit(); return false;}\"";
						}
					}
					else
					{
						// add empty onclick handler for selenium tests
						menuHTML += " onclick=\"\" ";
					}

					menuHTML += ">";
				}
				else
				{
					menuHTML += "<tr >";
				}

				if( isSplitter )
				{
					menuHTML += "<td colspan=\"3\">" + entryName + "</td>";
				}
				else
				{
					menuHTML += "<td class=\"icon\" id=\"" + elementID + "_icon_td\">";
					if( showIcon )
					{
						menuHTML += "<img src=\"" + icon + "\" id=\"" + elementID + "_img\">";
					}
					if( !disabled )
					{
						if( !document.editorForm.elements[entryEvent] )
						{
							inputElement = document.createElement("div");
							inputElement.innerHTML = "<input type=\"hidden\" name=\"" + entryEvent + "\" value=\"\">";
							document.editorForm.appendChild(inputElement);
						}
						
					}
					
					menuHTML += "</td>";
					menuHTML += "<td ";
					menuHTML += "class=\"name " + (disabled ? "disabled" : "") + "\" ";
					menuHTML += "style=\"text-align:" + (this.rightAlign ? "right" : "left") + ";\" id=\"" + elementID + "_label\">";
					menuHTML += entryName;
					menuHTML += "</td>";
					menuHTML += "<td>";
					
					if( hasSubMenu )
					{
						if( !disabled )
						{
							menuHTML += "<div style=\"width:8px;\"><img src=\"images/icons/submenu_arrow.gif\" /></div>";
						}
						else
						{
							menuHTML += "<div style=\"width:8px;\"><img src=\"images/icons/submenu_arrow_disabled.gif\" /></div>";
						}
					}
					
					menuHTML += "</td>";
				}

				menuHTML += "</tr>";				
			}

			menuHTML += "</table>";
			
			menuElement = document.createElement("div");
			menuElement.className = this.className;

			menuElement.style.visibility = "hidden";
			menuElement.style.position = "absolute";
			menuElement.style.top = -1000;				// though setting visibility to 'hidden' mozilla tends to show the menu for a fraction of a second
			menuElement.style.left = -1000;				// before placing it in the correct position. With these values for top and left
																	// the menu flicker will appear beyond the visible area and can no longer bother anyone.
			menuElement.style.zIndex = "20";

			document.getElementsByTagName("body")[0].appendChild(menuElement);

			menuElement.innerHTML = menuHTML;
			
			// find correct top-left coordinates
			var coordinates = y_getCoordinates(sourceElement, sourceEvent, menuElement, this.rightAlign);
			
			menuElement.style.top = coordinates.top;
			menuElement.style.left = coordinates.left;

			// create iframe which will later be shown behind the menu element to force the ie to show the menu on top of select boxes
			this.theIframeElement = y_createUnderlyingIframe(menuElement);				

			return menuElement;
		}

	/*
		Menu.show() will show this menu (which is already rendered when creating the menu but set invisible).
		All the submenus are also rendered during the creation of this menu but they will not visible until the appropriate
		menu entry is highlighted.
	*/
	this.show = function(event)
		{
			if( this.theMenuElement == null )
			{
				// create the hidden div containing the menu and create all submenus
				this.theMenuElement = this.createMenu(event != null ? event : this.sourceEvent, this.sourceElement);
			}

			if( menu_timeout != null)
			{
				// prevent multiple menus from opening at the same time (could happen, if an element with a contextmenu contains another element with a contextmenu, for example)
				return;
			}

			// remove possible previous menus
			menu_hide();

			if( event != null )
			{
				// find correct top-left coordinates
				var coordinates = y_getCoordinates(null, event, this.theMenuElement, this.rightAlign);
				
				this.theMenuElement.style.top = coordinates.top;
				this.theMenuElement.style.left = coordinates.left;

				if( this.theIframeElement != null )
				{
					this.theIframeElement.style.top = coordinates.top;
					this.theIframeElement.style.left = coordinates.left;
				}
			}

			this.theMenuElement.style.visibility = "visible";
			
			if( this.theIframeElement != null )
			{
				this.theIframeElement.style.visibility = "visible";
			}
			
			currentMainMenu = this;
			this.canRemove = false;
			
			menu_timeout = setTimeout(menu_removeTimeout, 300);			
		}
					
	/*
		Menu.hide() will hide this menu and all its containing submenus.
	*/
	this.hide = function()
	{
		if( this.canRemove )
		{
			// hide this menu element
			if( this.theMenuElement )
			{
				this.theMenuElement.style.visibility = "hidden";
				currentMainMenu = null;
				this.hideSubMenus();
			}
			
			if( this.theIframeElement != null )
			{
				this.theIframeElement.style.visibility = "hidden";
			}
		}
		else
		{
			// hide() was called by the initial onclick event which has just created the menu. the menu can be removed the next time hide() is called.
			this.canRemove = true;
		}
	}
					
	this.hideSubMenus = function()
	{
		for( var x = 0; x < this.subMenus.length; x++ )
		{
			this.subMenus[x].hide();
		}
	}				
	
	this.createSubMenu = function(menuEntries, bottomAlign)
	{
		return new SubMenu(menuEntries, this, bottomAlign, this.uniqueName);
	}
	
	/*
		Menu.setOpacity() sets the opacity of this menu.
		This is just a convenience method to set the two values 'filter' and 'opacity' which are responsible for the opacity 
		in the IE or in Mozilla browsers respectively.
		
		Parameter:
		
		opacityInPercent - the opacity in percent, i.e. using '100' will yield a menu which is fully opaque and '50' will be fifty percent transparent etc.
	*/
	this.setOpacity = function(opacityInPercent)
		{
			this.filter = "alpha(opacity=" + opacityInPercent + ")";
			this.opacity = opacityInPercent / 100;
		}

		
// ----- 'constructor' of Menu
	
	// store this menu in the global menu array
	menus[this.ID] = this;	
}