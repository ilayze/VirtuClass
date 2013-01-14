package guiLayer;

public interface ListChanger {
	public String getSelectedValue();
	public int getSelectedIndex();
	public void insertElementAt(int index);
	public void setSelectedIndex(int index);
	public void ensureIndexIsVisible(int index);
	public String getNewClassroomName();
	public void setNewClassroomNameClean();
	public void giveFocusToNewClassroomText();
	public void selectNewClassroomText();
	public boolean listContains(Object obj);
	public void setNewClassroomsList(String classroomsNames);
}
