package shalygin;

import shalygin.board.Board;
import shalygin.board.Move;
import shalygin.board.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Gui {

    private Board board;
    private Tile destTile;

    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension (600, 600);
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    private static final Dimension PANEL_DIMENSION = new Dimension(10,10);

    Gui() {
        JFrame gameFrame = new JFrame("Reversi");
        final JMenuBar menuBar = createMenuBar();
        gameFrame.setJMenuBar(menuBar);
        gameFrame.setSize(OUTER_FRAME_DIMENSION);
        gameFrame.setLayout(new BorderLayout());
        this.board = Board.createStandardBoard();
        BoardPanel boardPanel = new BoardPanel();
        gameFrame.add(boardPanel, BorderLayout.CENTER);
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        return menuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenu);
        return fileMenu;
    }

    private class BoardPanel extends JPanel {
        final ArrayList<TilePanel> boardTiles;

        BoardPanel() {
            super(new GridLayout(8, 8));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < 64; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        void drawBoard(final Board board) {
            removeAll();
            for (final TilePanel boardTile : (boardTiles)) {
                boardTile.drawTile(board);
                add(boardTile);
            }
            validate();
            repaint();
        }
    }

    private class TilePanel extends JPanel {
        private final int tileID;

        TilePanel(final BoardPanel boardPanel, final int tileID) {
            super(new GridBagLayout());
            this.tileID = tileID;
            setPreferredSize(PANEL_DIMENSION);
            paintTile();
            assignTilePieceIcon(board);

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    destTile = board.getTile(tileID);
                    int count = 0;

                    if (board.getDestTiles().contains(destTile.getTileCoord())) {
                        List<Move> movesToMake = new ArrayList<>();
                        for (Move move: board.getMoves()) if (move.getDestTile() == tileID) movesToMake.add(move);
                        for (Move move: movesToMake) {
                            board = Move.execute(board, move);
                            count++;
                        }
                        if (count % 2 == 0) board = board.changePlayer();
                        System.out.println(board);
                        System.out.println("placed Piece at " + tileID);
                        if (board.getMoves().isEmpty()) { //checking for endgame
                            board = board.changePlayer();
                            if (board.getMoves().isEmpty()) {
                                boardPanel.drawBoard(board);
                                Move.endGame(board);
                            }
                        }
                        if (board.getCurrentPlayer().getTeam().isBlack()) {
                            System.out.println("Black Turn");
                        } else System.out.println("Red Turn");
                        boardPanel.drawBoard(board);

                    }
                    else System.out.println("This is not a possible move!");
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        private void assignTilePieceIcon(Board board) {
            this.removeAll();
            if (board.getTile(this.tileID).isOccupied()) {
                try {
                    final BufferedImage image = ImageIO.read(new File("png/" +
                            board.getTile(this.tileID).getPiece().getTeam().toString() + ".png"));
                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void paintTile() {
            setBackground(Color.lightGray);
            setBorder(BorderFactory.createSoftBevelBorder(0, Color.gray, Color.darkGray));
            if (board.getDestTiles().contains(tileID)) {
                setBackground(new Color(0, 160, 0));
            }
        }

        void drawTile(final Board board) {
            assignTilePieceIcon(board);
            paintTile();
            validate();
            repaint();
        }
    }

    public static void endGameWindow(final Board board) {
        JFrame frame = new JFrame("The match has ended!");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel label;
        int x = Move.scoreX(board);
        int y = Move.scoreY(board);
        if (x > y) {
            label = new JLabel("Team Red won!" + " Team Black: " + y + " Team Red: " + x);
        } else if (x < y) {
            label = new JLabel("Team Black won!" +
                    " Team Black: " + y +
                    " Team Red: " + x);
        } else {
            label = new JLabel("It's a Draw!" + " Team Black: " + y + " Team Red: " + x);
        }
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        frame.add(label);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
