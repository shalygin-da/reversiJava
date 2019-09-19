package shalygin;

import shalygin.board.Board;
import shalygin.board.Move;
import shalygin.board.Tile;
import shalygin.piece.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Gui {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private Board board;
    private Tile destTile;
    private Piece userMovedPiece;

    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension (600, 600);
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    private static final Dimension PANEL_DIMENSION = new Dimension(10,10);

    public Gui() {
        this.gameFrame = new JFrame("Reversi");
        final JMenuBar menuBar = createMenuBar();
        this.gameFrame.setJMenuBar(menuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);
        this.gameFrame.setLayout(new BorderLayout());
        this.board = Board.createStandardBoard();
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        return menuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenu);
        return fileMenu;
    }

    private class BoardPanel extends JPanel {
        final ArrayList<TilePanel> boardTiles;

        BoardPanel() {
            super(new GridLayout(8, 8));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < 63; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        public void drawBoard(final Board board) {
            removeAll();
            for (final TilePanel tilePanel: boardTiles) {
                tilePanel.drawTile(board);
                add(tilePanel);
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
                    if (!destTile.isOccupied()) {
                        Move.execute(); // TODO: 9/20/2019 wtf is that
                    }
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



        }

        private void paintTile() {
            setBackground(Color.lightGray);

        }

        public void drawTile(final Board board) {
            paintTile();
            assignTilePieceIcon(board);
            validate();
            repaint();
        }
    }

}
