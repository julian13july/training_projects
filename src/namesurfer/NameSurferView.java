package namesurfer;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * File: NameSurferView.java
 * ---------------------------
 * This class represents a View for data display according to the MVC design model. This class is responsible
 * for updating (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */
public class NameSurferView extends JPanel implements NameSurferConstants {

    private LinkedHashMap<String, int[]> entryList;
    private float frameHeight;
    private float graphHeight;
    private int decadeWidth;

    protected NameSurferView(JFrame frame) {
        frame.add(this);
    }

    protected void draw(NameSurferEntry entry) {
        this.entryList = entry.getEntryList();
        repaint();
    }

    protected void clear(NameSurferEntry entry) {
        entry.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        frameHeight = getHeight() - GRAPH_MARGIN_SIZE * 2;
        graphHeight = getHeight() - GRAPH_MARGIN_SIZE;
        decadeWidth = getWidth() / NDECADES;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawGrid(g);
        if (entryList != null) {
            drawGraph(entryList, g);
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < NDECADES; i++) {
            g.drawLine(decadeWidth * i, 0, decadeWidth * i, getHeight());
            g.drawString(Integer.toString(START_DECADE + i * SIZE_DECADE), decadeWidth * i, getHeight());
        }
        g.drawLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        g.drawLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
    }

    private void drawGraph(LinkedHashMap<String, int[]> entryList, Graphics g) {
        int color = 0;
        for (Map.Entry<String, int[]> entry : entryList.entrySet()) {
            for (int i = 0; i < NDECADES; i++) {
                int nextDecade = i + 1;
                if (i == NDECADES - 1) {
                    nextDecade = i;
                }
                int rank = entry.getValue()[i];
                int rankNext = entry.getValue()[nextDecade];
                String name = entry.getKey();
                String rankString = Integer.toString(rank);
                if (rank == 0) {
                    rankString = "*";
                }
                setColor(color, g);
                g.drawLine(decadeWidth * i,
                        (int) (graphHeight - (frameHeight / MAX_RANK) * rank),
                        decadeWidth * nextDecade,
                        (int) (graphHeight - (frameHeight / MAX_RANK) * rankNext));
                g.drawString(name + " " + rankString, decadeWidth * i,
                        (int) (graphHeight - (frameHeight / MAX_RANK) * rank));
            }
            color++;
        }
    }

    private void setColor(int color, Graphics g) {
        switch (color % 4) {
            case 0:
                g.setColor(Color.BLUE);
                break;
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.MAGENTA);
                break;
            case 3:
                g.setColor(Color.GREEN);
                break;
        }
    }
}

