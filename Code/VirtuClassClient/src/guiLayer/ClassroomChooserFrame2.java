package guiLayer;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.border.LineBorder;
//import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//import java.awt.FlowLayout;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class ClassroomChooserFrame2 extends JFrame implements ListSelectionListener {

	
/**
*
*/
private static final long serialVersionUID = 1L;
private JPanel contentPane;
private JList list;
private JButton btnConnect;
private JTextField textFieldNewClassroomName;
private DefaultListModel listModel;
public static String NEW_CLASSROOM = "new classroom";
public static String REFRESH = "refresh";
public static String CONNECT = "connect";
public ListChanger l;
/**
* Launch the application.
*/


/**
* Create the frame.
*/
public ClassroomChooserFrame2(ActionListener m) {
setTitle("Open Rooms");
//setAlwaysOnTop(true);
setBackground(new Color(153, 204, 204));
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 708, 515);
contentPane = new JPanel();
contentPane.setBackground(new Color(153, 204, 204));
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);

JPanel panel = new JPanel();
panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Open Rooms", TitledBorder.LEADING, TitledBorder.TOP, null, null));
panel.setBounds(6, 83, 551, 344);
contentPane.add(panel);
panel.setLayout(new BorderLayout(0, 0));

listModel = new DefaultListModel();
list = new JList(listModel);
list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
list.setSelectedIndex(-1);
list.addListSelectionListener(this);
list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
panel.add(list);

JPanel panel_1 = new JPanel();
panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Currently Online", TitledBorder.LEADING, TitledBorder.TOP, null, null));
panel_1.setBounds(564, 85, 130, 339);
contentPane.add(panel_1);
panel_1.setLayout(new BorderLayout(0, 0));

JScrollPane scrollPane = new JScrollPane();
panel_1.add(scrollPane);

btnConnect = new JButton("Connect");
btnConnect.addActionListener(m);
btnConnect.setActionCommand(CONNECT);
btnConnect.setEnabled(false);
btnConnect.setBounds(16, 439, 201, 30);
contentPane.add(btnConnect);

JButton btnNewButton = new JButton("Refresh");
btnNewButton.addActionListener(m);
btnNewButton.setActionCommand(REFRESH);
btnNewButton.setBounds(219, 439, 230, 30);
contentPane.add(btnNewButton);

JButton btnNewButton_1 = new JButton("Exit");
btnNewButton_1.setBounds(461, 439, 233, 30);
contentPane.add(btnNewButton_1);
JButton btnCreateNewClassroom = new JButton("Create New Classroom");
btnCreateNewClassroom.addActionListener(m);
btnCreateNewClassroom.setActionCommand(NEW_CLASSROOM);
btnCreateNewClassroom.setBounds(27, 33, 170, 23);
contentPane.add(btnCreateNewClassroom);
textFieldNewClassroomName = new JTextField();
textFieldNewClassroomName.setBounds(207, 34, 130, 20);
contentPane.add(textFieldNewClassroomName);
textFieldNewClassroomName.setColumns(10);

l=new ListChanger() {
	
	@Override
	public void setNewClassroomNameClean() {
		textFieldNewClassroomName.setText("");
	}
	
	@Override
	public void setSelectedIndex(int index) {
		list.setSelectedIndex(index);
	}
	
	@Override
	public void selectNewClassroomText() {
		textFieldNewClassroomName.selectAll();		
	}
	
	@Override
	public void insertElementAt(int index) {
		listModel.insertElementAt(textFieldNewClassroomName.getText(), index);
	}
	
	@Override
	public void giveFocusToNewClassroomText() {
		textFieldNewClassroomName.requestFocusInWindow();
	}
	
	@Override
	public String getSelectedValue() {
		return (String)list.getSelectedValue();
	}
	
	@Override
	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}
	
	@Override
	public String getNewClassroomName() {
		return textFieldNewClassroomName.getText();
	}
	
	@Override
	public void ensureIndexIsVisible(int index) {
		list.ensureIndexIsVisible(index);
	}

	@Override
	public boolean listContains(Object obj) {
		return listModel.contains(obj);
	}

	@Override
	public void setNewClassroomsList(String classroomsNames) {
		
		listModel.clear();
		
		int nextSeparator=0;
		while(nextSeparator>=0 && classroomsNames.length()>0)
		{
			nextSeparator = classroomsNames.indexOf(':');
			if(nextSeparator<0)
				continue;
			if(nextSeparator>0)
			{
				String newClassroomName = classroomsNames.substring(0, nextSeparator);
				insertElement(newClassroomName);
			}
			if(nextSeparator+1<classroomsNames.length())
			{
				classroomsNames = classroomsNames.substring(nextSeparator+1,classroomsNames.length());
			}
			else
			{
				classroomsNames="";
			}
		}
		if(classroomsNames.length()>0)
		{
			insertElement(classroomsNames);
		}
		
	}
};
}

private void insertElement(Object obj)
{
    int index = list.getSelectedIndex(); //get selected index
    if (index == -1) { //no selection, so insert at beginning
        index = 0;
    } else {           //add after the selected item
        index++;
    }

    listModel.insertElementAt(obj, index);
    //If we just wanted to add to the end, we'd do this:
    //listModel.addElement(employeeName.getText());

    //Select the new item and make it visible.
    list.setSelectedIndex(index);
    list.ensureIndexIsVisible(index);
}

@Override
public void valueChanged(ListSelectionEvent e) {
    if (e.getValueIsAdjusting() == false) {

        if (list.getSelectedIndex() == -1) {
        //No selection, disable fire button.
            btnConnect.setEnabled(false);

        } else {
        //Selection, enable the fire button.
            btnConnect.setEnabled(true);
        }
    }
}
}