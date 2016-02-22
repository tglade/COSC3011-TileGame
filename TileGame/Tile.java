package TileGame;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Tile extends JLabel {

    public static final int TILE_W = 60;    //square tile image width
    public int position;            		//the numeric rank of the tile position
    private GameWindow gameWin;          	//GameWindow object in which this tile is part of
    public static final String TILE_IMAGE = "/TileImages/";
    
    public Tile(int pos, GameWindow gameW){
    	//BufferedImage bufImg = ImageIO.read(new File(TILE_IMAGE + "hexagonPattern.jpg");
        super(new ImageIcon(JPanel.class.getResource(GameWindow.TILE_IMAGE+"gold.gif")));
        MouseHandler listener = new MouseHandler();
        this.addMouseListener(listener);        //add mouseListeners to the tile
        this.addMouseMotionListener(listener);
        position = pos;
        gameWin = gameW;
    }
    
    private class MouseHandler extends MouseInputAdapter {
        private int clickDisX;      //displacement when clicking on the Tile
        private int clickDisY;
        private Point origin;       //the point where the Tile is located
        public void mouseDragged(MouseEvent e) {
            Tile Tile = (Tile) e.getComponent();
            JPanel panel = (JPanel) Tile.getParent();
            //here we check that the Tile is not dragged off the panel
            //by resetting the Tile's coordinates if it starts to move off
            int newX = Math.max(Tile.getX() + e.getX() - clickDisX, 0);
            newX = Math.min(newX, panel.getWidth() - TILE_W);
            int newY = Math.max(Tile.getY() + e.getY() - clickDisY, 0);
            newY = Math.min(newY, panel.getHeight() - TILE_W);
            //update the Tile's new locations
            Tile.setLocation(newX, newY);
        }
        public void mousePressed(MouseEvent e) {
            Tile Tile = (Tile) e.getComponent();
            JPanel panel = (JPanel) Tile.getParent();
            //save the original location of the Tile
            origin = Tile.getLocation();
            clickDisX = e.getX();
            clickDisY = e.getY();
            //move the Tile to the top of the panel
            //and repaint it so it appears above all others
            panel.setComponentZOrder(Tile, 0);
            panel.repaint();
        }
        public void mouseReleased(MouseEvent e) {
            Tile Tile = (Tile) e.getComponent();
            JPanel panel = (JPanel) Tile.getParent();
            //assume the move is not legal
            boolean legalRelease = false;
            //make sure the mouse is release inside the JPanel
            if(panel.getMousePosition() != null){
                //search all the free spots for this mouse coordinate
                for (Rectangle free : gameWin.freeSpots) {
                    //if we have found the correct free spot...
                    if(free.contains(panel.getMousePosition())) {
                        //update the Tile's location to the free spot
                        Tile.setLocation(free.getLocation());
                        //update free spot's location to the Tile's origin
                        free.setLocation(origin);
                        //final Point move = free.getLocation();
                        //set flag to show a valid move
                        legalRelease = true;
                    }
                }
            }
            // if the move was not valid, snap back to original spot and beep
            if(!legalRelease) {
                Tile.setLocation(origin);
                java.awt.Toolkit.getDefaultToolkit().beep();
            }
            
            // ask the game window if the game was won
            if(gameWin.isGameWon()) {
                JOptionPane.showMessageDialog(panel.getParent(),
                        "You Win");
            }
        }
    }
}


