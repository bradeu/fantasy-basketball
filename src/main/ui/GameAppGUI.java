package ui;

import model.Game;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GameAppGUI extends JFrame implements ActionListener {
    private Game game;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/game.json";
    private JLabel label;
    private CardLayout cardLayout;
    private JPanel cards;
    private String teamName;
    private int numPlayer;

    // EFFECTS : Create Game Application Graphical User Interface
    public GameAppGUI() {
        super("Fantasy Basketball Game App");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initGame();
        initGUI();
    }

    // EFFECTS : Initializes Game
    private void initGame() {
        game = new Game();
    }

    // EFFECTS : Initializes Graphical User Interface
    private void initGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1080, 1080));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        getContentPane().setBackground(Color.CYAN);
        setLayout(new FlowLayout());

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(createWelcomePanel(), "Welcome Panel");
        cards.add(createAddTeamPanel(), "Add Team");
        cards.add(createShowTeamsPanel(), "Show Teams");
        cards.add(createShowPLayersPanel(), "Show Players");
        cards.add(createLoadGamePanel(), "Load Game");
        cards.add(createSaveGamePanel(), "Save Game");
        cards.add(createPlayGamePanel(), "Play Game");

        getContentPane().add(cards, BorderLayout.CENTER);
        setMenu();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS : Create a new panel for Welcome page
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel welcomeLabel = new JLabel("Welcome to the Fantasy Basketball Game App!", JLabel.CENTER);
        ImageIcon imageIcon = new ImageIcon("./static/Dunk.png");

        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);

        JLabel label = new JLabel(imageIcon);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 17));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(welcomeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(label);
        return panel;
    }

    // MODIFIES: game
    // EFFECTS : Create a new panel for adding teams with team name and number of players
    private JPanel createAddTeamPanel() {
        JPanel panel = createPanelWithBoxLayout(BoxLayout.Y_AXIS);
        JTextField nameField = addLabelAndTextField(panel, "Enter team name:");
        JTextField numField = addLabelAndTextField(panel, "Enter number of players:");
        JButton enterButton = createEnterButton(nameField, numField);
        panel.add(enterButton);
        return panel;
    }

    // EFFECTS: Creates a panel with a specified BoxLayout orientation
    private JPanel createPanelWithBoxLayout(int axis) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, axis));
        return panel;
    }

    // EFFECTS: Adds a label and a text field to a panel, and returns the text field
    private JTextField addLabelAndTextField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField(10);
        panel.add(label);
        panel.add(textField);
        return textField;
    }

    // EFFECTS: Creates and returns an enter button with action listener
    private JButton createEnterButton(JTextField nameField, JTextField numField) {
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> handleEnterAction(nameField, numField));
        return enterButton;
    }

    // MODIFIES: this
// EFFECTS: Handles the action for the enter button
    private void handleEnterAction(JTextField nameField, JTextField numField) {
        teamName = nameField.getText();
        try {
            numPlayer = Integer.parseInt(numField.getText());
            cards.add(createAddTeamExtendedPanel(), "Add Team Extended");
            cardLayout.show(cards, "Add Team Extended");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number of players.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // MODIFIES: game
    // EFFECTS: Create a new panel for adding teams with player names
    private JPanel createAddTeamExtendedPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Enter players name:");
        panel.add(label);

        ArrayList<JTextField> textFieldList = new ArrayList<>();
        for (int i = 0; i < numPlayer; i++) {
            JTextField textField = new JTextField(10);
            panel.add(textField);
            textFieldList.add(textField);
        }
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> {
            Team team = new Team(teamName);
            for (JTextField textField : textFieldList) {
                team.addPlayer(textField.getText());
            }
            game.addTeam(team);
            cards.add(createAddTeamExtendedPanelPartTwo(), "Add Team Extended Part Two");
            cardLayout.show(cards, "Add Team Extended Part Two");
        });
        panel.add(enterButton);
        return panel;
    }

    // MODIFIES: game
    // EFFECTS: Create a new panel for after adding team
    private JPanel createAddTeamExtendedPanelPartTwo() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Successfully Created!", JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 15));
        label.setVerticalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    // EFFECTS: Create a panel for showing teams
    private JPanel createShowTeamsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(15, 30);
        textArea.setEditable(false);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            ArrayList<Team> teams = game.getTeamList();
            textArea.setText("");
            for (Team team : teams) {
                textArea.append(team.getName() + "\n");
            }
        });

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createShowPLayersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(15, 30);
        textArea.setEditable(false);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Enter team name:");
        JTextField textField = new JTextField(10);
        inputPanel.add(label);
        inputPanel.add(textField);

        JButton showButton = new JButton("Show");

        showButton.addActionListener(e -> {
            String teamName = textField.getText();
            Team teamFound = null;
            for (Team team : game.getTeamList()) {
                if (team.getName().equals(teamName)) {
                    teamFound = team;
                }
            }
            textArea.setText("");
            if (teamFound == null) {
                textArea.append("Not Found!");
            } else {
                ArrayList<Player> players = teamFound.getPlayerList();

                for (Player player : players) {
                    textArea.append(player.getName() + "\n");
                }
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(showButton, BorderLayout.SOUTH);

        return panel;
    }

    // EFFECTS: Create a panel for Load game
    private JPanel createLoadGamePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton loadButton = new JButton("Load Game Data");
        loadButton.setVerticalAlignment(JButton.CENTER);
        loadButton.addActionListener(e -> {
            try {
                game = jsonReader.read();
                JOptionPane.showMessageDialog(this,
                        "Game loaded successfully.", "Load Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this,
                        "Failed to load game data.", "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(loadButton);
        return panel;
    }

    // MODIFIES: JSON_STORE
    // EFFECTS: Create a panel for Save game
    private JPanel createSaveGamePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton saveButton = new JButton("Save Game Data");
        saveButton.setVerticalAlignment(JButton.CENTER);
        saveButton.addActionListener(e -> {
            try {
                jsonWriter.open();
                jsonWriter.write(game);
                jsonWriter.close();
                JOptionPane.showMessageDialog(this,
                        "Game saved successfully.", "Save Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(this,
                        "Failed to save game data.", "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(saveButton);
        return panel;
    }

    // EFFECTS: Create a panel to play the game
    private JPanel createPlayGamePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton playButton = new JButton("Play Game");
        label = new JLabel("Click the button below to get prediction!");
        label.setHorizontalAlignment(JLabel.CENTER);
        playButton.addActionListener(e -> updateGamePrediction());
        panel.add(label, BorderLayout.CENTER);
        panel.add(playButton, BorderLayout.SOUTH);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Updates game prediction based on team ratings and updates the label
    private void updateGamePrediction() {
        Team winner = determineWinner(game.getTeamList());
        String message = (winner != null) ? winner.getName() + " wins the game!" : "Not Enough Teams!";
        label.setText(message);
    }

    // EFFECTS: Determines the winning team based on ratings, returns null if not enough teams
    private Team determineWinner(ArrayList<Team> teamList) {
        if (teamList.size() < 2) {
            return null;
        }
        Team winner = teamList.get(0);
        for (int i = 1; i < teamList.size(); i++) {
            Team team = teamList.get(i);
            if (team.getRating() > winner.getRating()) {
                winner = team;
            }
        }
        return winner;
    }


    // EFFECTS: Quits app
    private void quitApp() {
        System.exit(0);
    }

    // EFFECTS: Creates a menu bar with menu items
    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = createMenu(menuBar, "Menu");
        addMenuItem(menu, "Play Game", "playGame");
        addMenuItem(menu, "Show Teams", "showTeams");
        addMenuItem(menu, "Show Players", "showPlayers");
        addMenuItem(menu, "Add Team", "addTeam");
        addMenuItem(menu, "Load Game", "loadGame");
        addMenuItem(menu, "Save Game", "saveGame");
        addMenuItem(menu, "Quit", "quitApp");
        setJMenuBar(menuBar);
    }

    // EFFECTS: Creates and returns a new JMenu added to the menuBar
    private JMenu createMenu(JMenuBar menuBar, String title) {
        JMenu menu = new JMenu(title);
        menuBar.add(menu);
        return menu;
    }

    // EFFECTS: Creates a JMenuItem and adds it to the menu
    private void addMenuItem(JMenu menu, String title, String actionCommand) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setActionCommand(actionCommand);
        menuItem.addActionListener(this);
        menu.add(menuItem);
    }


    // EFFECTS: Shows cards based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "playGame":
                cardLayout.show(cards, "Play Game");
                break;
            case "showTeams":
                cardLayout.show(cards, "Show Teams");
                break;
            case "showPlayers":
                cardLayout.show(cards, "Show Players");
                break;
            case "addTeam":
                cardLayout.show(cards, "Add Team");
                break;
            case "loadGame":
                cardLayout.show(cards, "Load Game");
                break;
            case "saveGame":
                cardLayout.show(cards, "Save Game");
                break;
            case "quitApp":
                quitApp();
                break;
            default :
                cardLayout.show(cards, "Welcome Panel");
        }
    }

    // EFFECTS: Starts game graphical user interface
    public static void main(String[] args) {
        new GameAppGUI();
    }
}

