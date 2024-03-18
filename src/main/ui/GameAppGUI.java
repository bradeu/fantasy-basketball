package ui;

import model.Game;
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

    public GameAppGUI() {
        super("Basketball Game App");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initGame();
        initGUI();
    }

    private void initGame() {
        game = new Game();
    }

    private void initGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1080, 1080));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(createWelcomePanel(), "Welcome Panel");
        cards.add(createAddTeamPanel(), "Add Team");
        cards.add(createShowTeamsPanel(), "Show Teams");
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

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to the Basketball Game App!"));
        return panel;
    }

    private JPanel createAddTeamPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Enter team name:");
        JTextField textField = new JTextField(10);
        JButton addButton = new JButton("Add Team");
        addButton.addActionListener(e -> {
            String teamName = textField.getText();
            if (!teamName.isEmpty()) {
                Team team = new Team(teamName);
                game.addTeam(team);
                textField.setText("");
                JOptionPane.showMessageDialog(this, "Team added: " + teamName);
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(addButton);

        return panel;
    }

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

    public JPanel createLoadGamePanel() {
        JPanel panel = new JPanel();
        JButton loadButton = new JButton("Load Game Data");
        loadButton.addActionListener(e -> {
            try {
                game = jsonReader.read();
                JOptionPane.showMessageDialog(this, "Game loaded successfully.", "Load Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this, "Failed to load game data.", "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(loadButton);
        return panel;
    }

    public JPanel createSaveGamePanel() {
        JPanel panel = new JPanel();
        JButton saveButton = new JButton("Save Game Data");
        saveButton.addActionListener(e -> {
            try {
                jsonWriter.open();
                jsonWriter.write(game);
                jsonWriter.close();
                JOptionPane.showMessageDialog(this, "Game saved successfully.", "Save Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(this, "Failed to save game data.", "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(saveButton);
        return panel;
    }

    public JPanel createPlayGamePanel() {
        ArrayList<Team> teamList = game.getTeamList();
        Team winner = null;

        if (teamList.size() >= 2) {
            winner = teamList.get(0);
            for (int i = 1; i < teamList.size(); i++) {
                Team team = teamList.get(i);
                if (team.getRating() > winner.getRating()) {
                    winner = team;
                }
            }
        }

        JPanel panel = new JPanel();
        if (winner != null) {
            label = new JLabel(winner.getName() + " wins the game!");
        } else {
            label = new JLabel("Not Enough Teams!");
        }
        panel.add(label);
        return panel;
    }

    public void quitApp() {
        System.exit(0);
    }

    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem playGame = new JMenuItem("Play Game");
        JMenuItem showTeams = new JMenuItem("Show Teams");
        JMenuItem addTeam = new JMenuItem("Add Team");
        JMenuItem loadGame = new JMenuItem("Load Game");
        JMenuItem saveGame = new JMenuItem("Save Game");
        JMenuItem quitApp = new JMenuItem("Quit");

        menu.add(playGame);
        menu.add(showTeams);
        menu.add(addTeam);
        menu.add(loadGame);
        menu.add(saveGame);
        menu.add(quitApp);

        playGame.setActionCommand("playGame");
        playGame.addActionListener(this);
        showTeams.setActionCommand("showTeams");
        showTeams.addActionListener(this);
        addTeam.setActionCommand("addTeam");
        addTeam.addActionListener(this);
        loadGame.setActionCommand("loadGame");
        loadGame.addActionListener(this);
        saveGame.setActionCommand("saveGame");
        saveGame.addActionListener(this);
        quitApp.setActionCommand("quitApp");
        quitApp.addActionListener(this);
    }

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

    public static void main(String[] args) {
        new GameAppGUI();
    }
}

