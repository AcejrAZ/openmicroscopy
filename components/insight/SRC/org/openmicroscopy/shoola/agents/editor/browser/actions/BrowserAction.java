 /*
 * org.openmicroscopy.shoola.agents.editor.browser.actions.BrowserAction 
 *
 *------------------------------------------------------------------------------
 *  Copyright (C) 2006-2008 University of Dundee. All rights reserved.
 *
 *
 * 	This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 *------------------------------------------------------------------------------
 */
package org.openmicroscopy.shoola.agents.editor.browser.actions;

//Java imports

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Third-party libraries

//Application-internal dependencies

import org.openmicroscopy.shoola.agents.editor.IconManager;
import org.openmicroscopy.shoola.agents.editor.browser.Browser;
import org.openmicroscopy.shoola.util.ui.UIUtilities;

/** 
 * Top abstract class that each action should extend.
 * Subclasses must implement 
 * {@link ActionListener#actionPerformed(ActionEvent)}
 * and {@link #onStateChange()}, called when the status of {@link Browser}
 * changes.
 *
 * @author  Jean-Marie Burel &nbsp;&nbsp;&nbsp;&nbsp;
 * <a href="mailto:j.burel@dundee.ac.uk">j.burel@dundee.ac.uk</a>
 * @author Donald MacDonald &nbsp;&nbsp;&nbsp;&nbsp;
 * <a href="mailto:donald@lifesci.dundee.ac.uk">donald@lifesci.dundee.ac.uk</a>
 * @version 3.0
 * <small>
 * (<b>Internal version:</b> $Revision: $Date: $)
 * </small>
 * @since 3.0-Beta3
 */
public abstract class BrowserAction 
	extends AbstractAction
	implements ChangeListener
{

    /** A reference to the Model. */
    protected Browser    model;
    
    /**
     * Callback to notify that a new browser is selected.
     * Subclasses implement the method.
     */
    protected abstract void onStateChange();
    
    /**
     * Creates a new instance.
     * 
     * @param model Reference to the Model. Mustn't be <code>null</code>.
     */
    public BrowserAction(Browser model)
    {
        super();
        setEnabled(false);
        if (model == null) throw new IllegalArgumentException("No Browser");
        this.model = model;
        model.addChangeListener(this);
    }
    
    
    
    /**
     * Sets the name of the Action. 
     * 
     * @param name	 see above.
     */
    protected void setName(String name) 
    {
    	putValue(Action.NAME, name);
    }
    
    /**
     * Sets the Description of the Action. 
     * 
     * @param description	 see above.
     */
    protected void setDescription(String description) 
    {
    	putValue(Action.SHORT_DESCRIPTION, 
                UIUtilities.formatToolTipText(description));
    }
    
    /**
     * Sets the Small Icon of the Action. 
     * 
     * @param iconID	 The IconManager static ID for the Icon. 
     * 
     * @see IconManager.getIcon(int)
     */
    protected void setIcon(int iconID) 
    {
    	IconManager im = IconManager.getInstance();
    	putValue(Action.SMALL_ICON, im.getIcon(iconID));
    }

    /** 
     * Subclasses implement the method.
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    public abstract void actionPerformed(ActionEvent e);
    
    /** 
     * Reacts to state changes in the {@link Editor}. 
     * @see ChangeListener#stateChanged(ChangeEvent)
     */
    public void stateChanged(ChangeEvent e)
    {
        onStateChange();
    }
    
}
