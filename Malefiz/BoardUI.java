import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
/**
 * A subclass of JPanel representing the board of Malefiz.
 * @author Cassandra Clowe-Coish & Abdul Shaji
 *
 */
public class BoardUI extends JPanel implements java.io.Serializable {
	
	private final Board board;
	private ArrayList<PawnUI> pawnUIs;
	
	private ArrayList<PawnHomeUI> pawnHomeUIs;
	private ArrayList<BarricadeUI> barricadeUIs;

	private JFrame win;
	private JPanel mainBoardPanel, homePanel, infoPanel, winPanel;

	private JButton dieButton, helpButton, saveButton, quitButton;
	private JLabel infoLabel;
	
	private ArrayList<SpaceUI> spaceUIs;
	
	private PawnUI selectedPawn;
	
	private int rolled;
	
	private int barricadeSelection = 0;
	private BarricadeUI selectedBarricade;
	private SpaceUI barrSpaceUI;
	
	private transient final GraphicsHandler graphicsHandler;
	
	public BoardUI(Board board)
	{
		this.setPreferredSize(new Dimension(880, 910));
		this.board = board;
		this.graphicsHandler = new GraphicsHandler();
		this.pawnUIs = new ArrayList<>();
		this.spaceUIs = new ArrayList<>();
		this.pawnHomeUIs = new ArrayList<>();
		
		this.mainBoardPanel = new JPanel();
		this.homePanel = new JPanel();
		this.infoPanel = new JPanel();
		
		this.infoLabel = new JLabel();
		
		this.dieButton = new JButton(getGraphicsHandler().getDiemap().get(1));
		dieButton.addActionListener(s -> rollDie());
		dieButton.setBorder(null);
		
		//infoPanel.setLayout();
		infoPanel.add(dieButton);
		infoPanel.add(infoLabel);
		
		mainBoardPanel.setLayout(new GridLayout(Board.getNumrows(), Board.getNumcols()));
		homePanel.setLayout(new FlowLayout());
		
		this.setLayout(new BorderLayout());
		this.add(homePanel, BorderLayout.CENTER);
		this.add(mainBoardPanel, BorderLayout.NORTH);
		this.add(infoPanel, BorderLayout.SOUTH);
		
		JPanel[][] gridholder = new JPanel[Board.getNumrows()][Board.getNumcols()];
		
		//create a grid for the main spaces to go on
		for (int i=0; i<gridholder.length; i++) {
			for (int j=0; j<gridholder[0].length; j++) {
				gridholder[i][j] = new JPanel();
				gridholder[i][j].setLayout(new BorderLayout());
				gridholder[i][j].setPreferredSize(new Dimension(40,40));
				mainBoardPanel.add(gridholder[i][j]);
			}
		}
		
		makePawnHomeUIs();
		
		//set up all the main spaces on the board
		for (int i=0; i<Board.getNumrows(); i++) {
			for (int j=0; j<Board.getNumcols(); j++) {
				if (getBoard().getSpaces()[i][j] != null) {
					/*SpaceUI spaceUI = new SpaceUI(getBoard().getSpaces()[i][j]);
					spaceUI.addActionListener(s -> takeTurn(spaceUI));*/
					SpaceUI spaceUI = new SpaceUI(getBoard().getSpaces()[i][j]);
					if (i != 13) {
						spaceUI.addActionListener(s -> takeTurn(spaceUI));
					}
					else {
						spaceUI.addActionListener(s -> winGame(spaceUI));
					}
					spaceUI.setEnabled(false);
					spaceUIs.add(spaceUI);
					gridholder[Board.getNumrows() - i-1][j].add(spaceUI, BorderLayout.CENTER);
					
					Integer[] upspaces = {2,6,10,14};
					for(int k: upspaces) {
						if (j==k && i==0) {
							spaceUI.setText("O");
						}
					}
					
					if (i==13) {
						Icon finishGraphic = getGraphicsHandler().getAuxGraphicsMap().get("Finish");
						spaceUI.setIcon(finishGraphic);
						spaceUI.setDisabledIcon(finishGraphic);
					}
					
					if(spaceUI.getContentOfSpace() instanceof Barricade) {
						BarricadeUI barricadeUI = new BarricadeUI((Barricade) spaceUI.getContentOfSpace(), getGraphicsHandler().getAuxGraphicsMap().get("Barricade"));
						barricadeUI.setSpaceUI(spaceUI);
						spaceUI.setBarricadeUI(barricadeUI);
						spaceUI.getBarricadeUI().getBarricade().setSpace(spaceUI.getSpace());
						barricadeUI.addActionListener(s -> { 
							selectedBarricade = barricadeUI;
							displaceBarricadeSpaces(barricadeUI.getSpaceUI());
						
						});
						barricadeUI.setEnabled(false);
					}

					if (spaceUI.getContentOfSpace() instanceof Pawn) {
						System.out.println(spaceUI.getContentOfSpace().getPlayer().getName());
						for (PawnUI pawnUI : pawnUIs) {
							if (spaceUI.getContentOfSpace() == pawnUI.getPawn()) {
								pawnUI.move(spaceUI);
							}
						}
					}
				}
			}
		}
		
		if (getBoard().getCurrentPlayer() == null) {
			getBoard().randomisePlayers();
		}
		
		else {
			getBoard().setPlayerIterator(getBoard().getPlayers().iterator());
			getBoard().setCurrentPlayer(getBoard().getCurrentPlayer());
		}
		
		showNextPlayer();
	}
	
	public void takeTurn(SpaceUI spaceUI) {
		if (barricadeSelection == 1) {
			displaceBarricade(spaceUI);
		}
	
		else if (spaceUI.getContentOfSpace() instanceof Barricade) {
			selectedBarricade = spaceUI.getBarricadeUI();
			displaceBarricadeSpaces(spaceUI);
		
		}
		else {
			movePawn(spaceUI);
		}
	}


	public GraphicsHandler getGraphicsHandler() {
		return graphicsHandler;
	}

	public ArrayList<PawnUI> getPawnUIs() {
		return pawnUIs;
	}


	public void setPawnUIs(ArrayList<PawnUI> pawnUIs) {
		this.pawnUIs = pawnUIs;
	}


	public ArrayList<PawnHomeUI> getPawnHomeUIs() {
		return pawnHomeUIs;
	}


	public void setPawnHomeUIs(ArrayList<PawnHomeUI> pawnHomeUIs) {
		this.pawnHomeUIs = pawnHomeUIs;
	}


	public JPanel getHomePanel() {
		return homePanel;
	}


	public void setHomePanel(JPanel homePanel) {
		this.homePanel = homePanel;
	}


	public Board getBoard() {
		return board;
	}
	
	public PawnUI getSelectedPawn() {
		return selectedPawn;
	}


	public void setSelectedPawn(PawnUI selectedPawn) {
		this.selectedPawn = selectedPawn;
	}

	public void enablePawnsOfPlayer(Player player, Boolean enabled) {
		for (PawnUI pawnUI : getPawnUIs()) {
			if (Arrays.asList(player.getPawns()).contains(pawnUI.getPawn())) {
				pawnUI.setEnabled(enabled);
			}
		}
	}
	
	
	private void makePawnHomeUIs() {
		for (PawnHome pawnHome : getBoard().getHomes()) {
			PawnHomeUI pawnHomeUI = new PawnHomeUI(pawnHome);
			getHomePanel().add(pawnHomeUI);
			getPawnHomeUIs().add(pawnHomeUI);
			Iterator<SpaceUI> hSpaceUIIter =  pawnHomeUI.getSpaceUIs().iterator();
			for (Pawn pawn : pawnHomeUI.getPawnHome().getPawnsOfPlayer()) {
				PawnUI pawnUI = new PawnUI(pawn, getGraphicsHandler().getPawnMap().get(pawn.getColor()));
				pawnUI.addActionListener(s -> {
					if (rolled == 0 ) {
						JOptionPane.showMessageDialog(this, "Roll the Die to Move your Pawn!", "Alert", JOptionPane.WARNING_MESSAGE);
					}
					if (selectedPawn != null && pawnUI.getPawn().getPlayer() != selectedPawn.getPawn().getPlayer()) {
						movePawn(pawnUI.getSpaceUI());
					}
					else {
						selectPawn(pawnUI);
					}
				});
				if (hSpaceUIIter.hasNext()) {
					pawnUI.setSpaceUI(hSpaceUIIter.next()); // add a pawn ui to each homespace
					getPawnUIs().add(pawnUI);
				}
			}
		}
		
	}

	
	public void selectPawn(PawnUI pawnUI)
	{	
		for (int i = 0; i < spaceUIs.size(); i++) {
			spaceUIs.get(i).setEnabled(false);
			spaceUIs.get(i).setBackground(Color.white);
			}
	    Space[] spaces;
	    setSelectedPawn(pawnUI);
	    spaces = getBoard().moveCalculate(pawnUI.getSpaceUI().getSpace(), rolled);
	    
	    
	    for (int i = 0; i < spaceUIs.size(); i++ ) {
	        for (int j = 0; j < spaces.length; j++) {
	            if (spaceUIs.get(i).getSpace() == spaces[j]) {
	            	if (spaceUIs.get(i).getContentOfSpace() instanceof Barricade) {
	            		spaceUIs.get(i).getBarricadeUI().setEnabled(true);
	            	}
	            	if (spaceUIs.get(i).getContentOfSpace() instanceof Pawn && spaceUIs.get(i).getContentOfSpace().getPlayer() != selectedPawn.getPawn().getPlayer()) {
	            		spaceUIs.get(i).getPawnUI().setEnabled(true);
	            	}
	                spaceUIs.get(i).setEnabled(true);
	                spaceUIs.get(i).setBackground(Color.gray);
	            }
	        }
	    }
	}
	
	public void movePawn(SpaceUI spaceUI) {
   
        if (!(getSelectedPawn().equals(null))) {
        	if (spaceUI.getPawnUI() != null) {
        		PawnUI p = spaceUI.getPawnUI();
        		for (PawnHomeUI ph : getPawnHomeUIs()) {
        			for (SpaceUI su : ph.getSpaceUIs()) {
        				if (su.getSpace().equals(p.getPawn().getHomeSpace())) {
        					p.move(su);
        				}
        			}
        		}
        	}
            getSelectedPawn().move((spaceUI));
        }
        
        for (int i = 0; i < spaceUIs.size(); i++) {
            spaceUIs.get(i).setEnabled(true);
            spaceUIs.get(i).setEnabled(false);
            spaceUIs.get(i).setBackground(Color.white);

        }
        selectedPawn = null;
        finishTurn();
    }

	
	public void finishTurn() {
        setRolled(0);
		showNextPlayer();
	}


    public void displaceBarricadeSpaces(SpaceUI spaceUI) { 
    	for (PawnUI pawnUI : getPawnUIs()) {
    		if (pawnUI.getPawn().getPlayer().equals(getSelectedPawn().getPawn().getPlayer())) {
    			pawnUI.setEnabled(false);
    		}
    	}
        barricadeSelection = 1;
        selectedBarricade = spaceUI.getBarricadeUI();
        for (int i = 0; i < spaceUIs.size(); i++) {
        	spaceUIs.get(i).setBackground(Color.white);
        	if (spaceUIs.get(i).getContentOfSpace() instanceof Barricade) {
				spaceUIs.get(i).setEnabled(false);
        		spaceUIs.get(i).getBarricadeUI().setEnabled(false);
        	}
            if (spaceUIs.get(i).getContentOfSpace() == null && (spaceUIs.get(i).getSpace().getY() != 0) && (spaceUIs.get(i).getSpace().getY() != 13)) {
                spaceUIs.get(i).setEnabled(true);
                spaceUIs.get(i).setBackground(Color.cyan.darker());
                getInfoLabel().setText("Choose a space to place the barricade.");
            }
        }
    }

    public void displaceBarricade(SpaceUI spaceUI){
    	barrSpaceUI = selectedBarricade.getSpaceUI();
        selectedBarricade.move(spaceUI);
        movePawn(barrSpaceUI);
        barrSpaceUI = null;
        barricadeSelection = 0;
        selectedBarricade = null;
        
        for (int i = 0; i < spaceUIs.size(); i++) {
        	if (spaceUIs.get(i).getContentOfSpace() instanceof Barricade) {
        		spaceUIs.get(i).getBarricadeUI().setEnabled(false);
        	}
            spaceUIs.get(i).setEnabled(true);
            spaceUIs.get(i).setEnabled(false);
            spaceUIs.get(i).setBackground(Color.white);
        }
    }
    
	public void rollDie()
	{
	    int rolled = getBoard().rollDie();
	    setRolled(rolled);
	    getInfoLabel().setText("You rolled: " + rolled);
	    getDieButton().setIcon(getGraphicsHandler().getDiemap().get(rolled));
	    getDieButton().setEnabled(false);
	}
	
    public JButton getDieButton() {
		return dieButton;
	}

	public void setDieButton(JButton dieButton) {
		this.dieButton = dieButton;
	}

	public JLabel getInfoLabel() {
		return infoLabel;
	}


	public void setInfoLabel(JLabel infoLabel) {
		this.infoLabel = infoLabel;
	}


	public void setRolled(int rolledNum) {
        rolled = rolledNum;
    }
	
	/**
	 * Displays the current player and enables their pawns, and disables the pawns of other players
	 */
	public void showNextPlayer() {
		getBoard().nextPlayer();
		Player player = getBoard().getCurrentPlayer();
		for(Player p : getBoard().getPlayers()) {
			enablePawnsOfPlayer(p, (p.equals(player)));
			
		getInfoLabel().setText(player.getName() + "'s Turn!");
		getDieButton().setEnabled(true);
		}
	}

	
	public void winGame(SpaceUI spaceUI) {
		for (PawnUI pawnUI : pawnUIs) {
			pawnUI.setEnabled(false);
		}
		for (SpaceUI space : spaceUIs) {
			space.setEnabled(false);
			space.setBackground(Color.white);
		}
		
		String winner = selectedPawn.getPawn().getPlayer().getName() + " is the Winner!!\n\n";
		JOptionPane.showMessageDialog(this,winner + "Select New Game in Main Menu to Play Again");	
	}
}
