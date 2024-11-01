package src;

import javax.swing.*;
import java.awt.*;

public class RaceTrack extends JPanel {
    private Horse[] horses;
    private Weather currentWeather;

    public RaceTrack(Horse[] horses) {
        this.horses = horses;
        setPreferredSize(new Dimension(600, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 트랙 그리기
        int laneHeight = getHeight() / horses.length;
        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < horses.length; i++) {
            g.drawLine(0, i * laneHeight + laneHeight / 2, getWidth(), i * laneHeight + laneHeight / 2);
        }

        // 말 이모티콘과 번호 그리기
        String horseEmoji = "🐎"; 
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));

        for (int i = 0; i < horses.length; i++) {
            int xPosition = horses[i].getPosition();
            int yPosition = i * laneHeight + laneHeight / 2 - 20;

            g2d.drawString(horseEmoji, xPosition, yPosition);
            g2d.drawString(String.valueOf(i + 1), xPosition, yPosition - 40);
            
            // 날씨 효과 그리기
            if (currentWeather == Weather.RAINY) {
                drawRainyEffect(g2d, xPosition, yPosition);
            } else if (currentWeather == Weather.WINDY) {
                drawWindyEffect(g2d, xPosition, yPosition);
            }
        }

        // 결승선 그리기
        g.setColor(Color.RED);
        g.drawLine(50, 0, 50, getHeight());
    }

    private void drawRainyEffect(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(0, 0, 255, 100));
        for (int i = 0; i < 5; i++) {
            int dropX = x + (int) (Math.random() * 40) - 20;
            int dropY = y + (int) (Math.random() * 40) - 20;
            g2d.fillOval(dropX, dropY, 3, 5);
        }
    }

    private void drawWindyEffect(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < 3; i++) {
            int startX = x + 40;  // 말 이모티콘 오른쪽에서 시작
            int startY = y - 10 + i * 10;
            int endX = startX + 30;
            int endY = startY + (int)(Math.random() * 10) - 5;  
            g2d.drawLine(startX, startY, endX, endY);
        }
    }

    public void updatePositions(int[] newPositions) {
        for (int i = 0; i < horses.length; i++) {
            horses[i].setPosition(newPositions[i]);
        }
        repaint();
    }

    public void setWeather(Weather weather) {
        this.currentWeather = weather;
        repaint();
    }
}
