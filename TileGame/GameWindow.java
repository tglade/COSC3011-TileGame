// Tyler Brutsman
// Thomas Glade
// Alanna Larson
// Danny Radosevich
// Chris Ruiz
// Group F


package TileGame;

import java.util.*;
import java.util.List;

import javax.swing.*;

import java.awt.*;

import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow implements ActionListener {
    
    public static final int BEVEL = 2;
    public static final Dimension PANEL_SIZE = new Dimension(900,1000);
    public static final String TILE_IMAGE = "/TileImages/";
    public final List<Rectangle> freeSpots = new ArrayList<Rectangle>(16);
    private JPanel panel;
    private JFrame window;
    protected static JButton newGameButton, resetButton, quitButton;

    /**
     * Create the GUI objects and display the game
     */
    public  GameWindow() 
    {
        // Create the window and panel
        window = new JFrame("Group F Maze");
        panel = new JPanel(null);
        // Call to create a new game
        newGame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBackground(Color.cyan);
        window.setResizable(false);
        window.setVisible(true);
        window.add(panel);
        window.pack();
        
    }
    
    /**
     * Clear the JPanel and freeSpots list and set up a new game
     */
    private void newGame() 
    {
    	
        //clear the board, clear the free spots
        //create a new deck of cards and shuffle it
        panel.removeAll();
        freeSpots.clear();
        int buttonCent = PANEL_SIZE.width/2 -160;
        int gridCent = PANEL_SIZE.width/2 -2*Tile.TILE_W - BEVEL;
        int tileStart = PANEL_SIZE.width/2 ;
        
        Rectangle newGameBounds = new Rectangle(buttonCent , 50, 100, 50);
        newGameButton = new JButton("New Game");
        newGameButton.setVerticalTextPosition(AbstractButton.CENTER);
		newGameButton.setHorizontalTextPosition(AbstractButton.CENTER);
		newGameButton.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent e) 
				{
					 newGame();
				}          
			}
		);
		newGameButton.setBounds(newGameBounds);
		panel.add(newGameButton);
        
		Rectangle resetBounds = new Rectangle(buttonCent+110, 50, 100, 50);
        resetButton = new JButton("Reset");
        resetButton.setVerticalTextPosition(AbstractButton.CENTER);
		resetButton.setHorizontalTextPosition(AbstractButton.CENTER);
		resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 reset();
				}          
			}
		);
		resetButton.setBounds(resetBounds);
		panel.add(resetButton);
		
		Rectangle quitBounds = new Rectangle(buttonCent+220, 50, 100, 50);
        quitButton = new JButton("Quit");
        quitButton.setVerticalTextPosition(AbstractButton.CENTER);
		quitButton.setHorizontalTextPosition(AbstractButton.CENTER);
		quitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 window.dispose();
				}          
			}
		);
		quitButton.setBounds(quitBounds);
		panel.add(quitButton);
		
		
        //shuffleLimit = 2;
        List<Tile> tileList = new ArrayList<Tile>(16);
        for (int i = 1; i <= 16; i++){
            tileList.add(new Tile(i, this));
        }
        
        Collections.shuffle(tileList);
        
        
        // Paint the 16 free spots in the center as red
        for (int i = 0; i < 16; i++) 
        {    
            Rectangle bounds = new Rectangle(gridCent +(i%4)*(Tile.TILE_W+BEVEL), 297 + BEVEL+(i/4)*(Tile.TILE_W+BEVEL), Tile.TILE_W, Tile.TILE_W); // Order they display changed by Danny
            freeSpots.add(bounds);
            JLabel emptyTile = new JLabel(Integer.toString(i), new ImageIcon(JPanel.class.getResource(GameWindow.TILE_IMAGE+"red.gif")), JLabel.CENTER);
            emptyTile.setIconTextGap(-Tile.TILE_W);
            emptyTile.setBounds(bounds);
           
            panel.add(emptyTile);
        }
        
        // Paint the 16 tiles that will be moved
        for (int j = 0; j < 16; j++) // display order changed by Danny
        {
        	if(j < 4)
        	{
        		Rectangle tileBounds = new Rectangle(tileStart - 5*Tile.TILE_W, 420 + BEVEL+(j-4)*(Tile.TILE_W+BEVEL), 
						 Tile.TILE_W, 
						 Tile.TILE_W);

            	tileList.get(j).setBounds(tileBounds);
        	}
        	else if(j < 8)
        	{
        		Rectangle tileBounds  = new Rectangle(tileStart - 5*Tile.TILE_W, 420 + BEVEL+(j-4)*(Tile.TILE_W+BEVEL), Tile.TILE_W,  Tile.TILE_W);

            	tileList.get(j).setBounds(tileBounds);
        	}
        	else if(j < 12)
        	{
        		Rectangle tileBounds  = new Rectangle(tileStart+ 4*Tile.TILE_W, 420 + BEVEL+(j-12)*(Tile.TILE_W+BEVEL), 
						 Tile.TILE_W, 
						 Tile.TILE_W);

        		tileList.get(j).setBounds(tileBounds);
        	}
        	else
        	{
        		Rectangle tileBounds  = new Rectangle(tileStart+ 4*Tile.TILE_W, 420 + BEVEL+(j-12)*(Tile.TILE_W+BEVEL), 
						 Tile.TILE_W, 
						 Tile.TILE_W);

        		tileList.get(j).setBounds(tileBounds);
        	}
        	tileList.get(j).setIconTextGap(-Tile.TILE_W);
        	tileList.get(j).setText(Integer.toString(j));
    		panel.add(tileList.get(j));
        	
        }
        
        panel.setPreferredSize(PANEL_SIZE);   
        panel.setBackground(Color.cyan);
        panel.repaint();        
    }
    
    /**
     * resets the game board when reset button is clicked
     */
    private void reset()
    { 
    	// not finished
    	
    }
    
    /**
     * tests if the game has been won
     * @return true if the game has been won, else returns false
     */
    public boolean isGameWon() {
    	// Will check that each tile is in its correct position
	    // Game must be won if we reach this return statement
    	
	    //return true;
    	// not finished
    	return false;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}  
}
