/*
 * org.openmicroscopy.shoola.agents.datamng.editors.DatasetOwnerPane
 *
 *------------------------------------------------------------------------------
 *
 *  Copyright (C) 2004 Open Microscopy Environment
 *      Massachusetts Institute of Technology,
 *      National Institutes of Health,
 *      University of Dundee
 *
 *
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *------------------------------------------------------------------------------
 */

package org.openmicroscopy.shoola.agents.datamng.editors;

//Java imports
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

//Third-party libraries

//Application-internal dependencies
import org.openmicroscopy.shoola.env.data.model.DatasetData;

/** 
 * 
 *
 * @author  Jean-Marie Burel &nbsp;&nbsp;&nbsp;&nbsp;
 * 				<a href="mailto:j.burel@dundee.ac.uk">j.burel@dundee.ac.uk</a>
 * @author  <br>Andrea Falconi &nbsp;&nbsp;&nbsp;&nbsp;
 * 				<a href="mailto:a.falconi@dundee.ac.uk">
 * 					a.falconi@dundee.ac.uk</a>
 * @version 2.2 
 * <small>
 * (<b>Internal version:</b> $Revision$ $Date$)
 * </small>
 * @since OME2.2
 */
class DatasetOwnerPane
	extends JPanel
{

	private DatasetEditorManager 	manager;
	
	DatasetOwnerPane(DatasetEditorManager manager)
	{
		this.manager = manager;
		buildGUI();
	}
	
	/** Build and lay out the GUI. */
	private void buildGUI()
	{
		setLayout(new GridLayout(1, 1));
		add(buildSummaryPanel());
		Border b = BorderFactory.createEmptyBorder(0, 0, 10, 10);
		setBorder(b);
	}

	private JPanel buildSummaryPanel()
	{
		JPanel  p = new JPanel();
		//summary table
		OwnerTableModel ownerTM = new OwnerTableModel();
		JTable t = new JTable(ownerTM);
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t.setTableHeader(null);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(t);
		return p;
	}
	
	/** 
	 * A <code>7x2</code> table model to view owner summary.
	 * The first column contains the property names 
	 * (id, first name, last name, e-mail, institution, group id, group name)
	 * and the second column holds the corresponding values. 
	 * For the time being, values aren't marked as editable. 
	 */
	private class OwnerTableModel 
		extends AbstractTableModel 
	{
		private final String[]    
		fieldNames = {" ID", " First Name", " Last Name", " E-mail", 
						" Institution", " Group ID", " Group Name"};
						
		DatasetData dd = manager.getDatasetData();
		private Object[] data = {""+dd.getOwnerID(), dd.getOwnerFirstName(),
								dd.getOwnerLastName(), dd.getOwnerEmail(),
								dd.getOwnerInstitution(),
								""+dd.getOwnerGroupID(), dd.getOwnerGroupName()
								};
								
		private OwnerTableModel() {}
	
		public int getColumnCount() { return 2; }
	
		public int getRowCount() { return 7; }
	
		public Object getValueAt(int row, int col) 
		{
			Object val = null;
			if (col == 0)  val = fieldNames[row];
			else val = data[row];
			return val;
		}
	
		//entries in the value column can be edited
		public boolean isCellEditable(int row, int col) { return false; }
	}
	
}
