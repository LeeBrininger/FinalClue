package clue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DetectiveNotesDialog extends JDialog {

	private JComboBox<String> playerCombobox, weaponCombobox, roomCombobox;
	
	public DetectiveNotesDialog() {
		setTitle("Detective Notes");
		setSize(500, 500);
		
		setLayout(new GridLayout(3,2));
		CheckboxPanel players = new CheckboxPanel("Players");
		add(players);
		players.addCheckbox("Frodo", "player");
		players.addCheckbox("Sam", "player");
		players.addCheckbox("Legolas", "player");
		players.addCheckbox("Aragorn", "player");
		players.addCheckbox("Gandalf", "player");
		players.addCheckbox("Gollum", "player");
		
		playerCombobox = new JComboBox<String>();
		playerCombobox.setBorder(new TitledBorder(new EtchedBorder(), "Player Guess"));
		for (JCheckBox j : players.getCheckboxes()) playerCombobox.addItem(j.getText());
		
		add(playerCombobox);
		
		CheckboxPanel weapons = new CheckboxPanel("Weapons");
		add(weapons);
		weapons.addCheckbox("Dagger","weapon");
		weapons.addCheckbox("Battleaxe","weapon");
		weapons.addCheckbox("Longsword","weapon");
		weapons.addCheckbox("Wizard Staff", "weapon");
		weapons.addCheckbox("Bow", "weapon");
		weapons.addCheckbox("The Ring", "weapon");
		
		weaponCombobox = new JComboBox<String>();
		weaponCombobox.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		for (JCheckBox j : weapons.getCheckboxes()) weaponCombobox.addItem(j.getText());
		
		add(weaponCombobox);
		
		CheckboxPanel rooms = new CheckboxPanel("Rooms");
		add(rooms);
		rooms.addCheckbox("Rhun", "room");
		rooms.addCheckbox("Rohan", "room");
		rooms.addCheckbox("The Shire", "room");
		rooms.addCheckbox("Gondor", "room");
		rooms.addCheckbox("Dunland", "room");
		rooms.addCheckbox("Mirkwood", "room");
		rooms.addCheckbox("Rivendell","room");
		rooms.addCheckbox("Ash Mountains","room");
		
		roomCombobox = new JComboBox<String>();
		roomCombobox.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		for (JCheckBox j : rooms.getCheckboxes()) roomCombobox.addItem(j.getText());
		
		add(roomCombobox);
		
	}
	
	class CheckboxPanel extends JPanel {
		
		private ArrayList<JCheckBox> checkboxes;
		
		public CheckboxPanel(String name) {
			setLayout(new GridLayout(0,2));
			checkboxes = new ArrayList<JCheckBox>();
			setBorder(new TitledBorder(new EtchedBorder(), name));
			
		}
		
		public void addCheckbox(String name, String type) {
			JCheckBox checkbox = new JCheckBox(name);
			checkbox.addActionListener(new CheckboxListener(type));
			add(checkbox);
			checkboxes.add(checkbox);
		}
		
		public ArrayList<JCheckBox> getCheckboxes() {
			return checkboxes;
		}
		
	}
	
	class CheckboxListener implements ActionListener {
		String type;
		public CheckboxListener(String type) {
			this.type = type;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox<String> combobox = null;
			switch (type) {
				case "player":
					combobox = playerCombobox;
					break;
				case "weapon":
					combobox = weaponCombobox;
					break;
				case "room":
					combobox = roomCombobox;
					break;
			}
			if (((JCheckBox) arg0.getSource()).isSelected()) combobox.removeItem(((JCheckBox)arg0.getSource()).getText());
			else {
				combobox.addItem(((JCheckBox)arg0.getSource()).getText());
		
			}
			
		}
		
	}

}
