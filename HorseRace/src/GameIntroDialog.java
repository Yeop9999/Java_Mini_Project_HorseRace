package src;

import javax.swing.*;
import java.awt.*;

public class GameIntroDialog extends JDialog {
    public GameIntroDialog(JFrame parent) {
        super(parent, "게임 소개", true);
        setPreferredSize(new Dimension(400, 300)); // 원하는 크기로 설정
        setLocationRelativeTo(parent); // 부모 프레임 중앙에 위치

        String message = "안녕하세요, 경주에 오신 것을 환영합니다!\n\n"
                + "이 게임에서는 6마리의 말이 경주를 합니다. 각 말은 날씨에 따라 속도가 달라지며, "
                + "플레이어는 각 말의 성능을 예측해야 합니다.\n\n"
                + "날씨 효과:\n"
                + "- 맑은 날 (Sunny): 말들이 더 빠르게 달립니다! 기본 속도에 2의 추가 속도가 더해집니다.\n"
                + "- 비 오는 날 (Rainy): 말들이 미끄러워져서 속도가 줄어듭니다. 기본 속도에서 2가 감소합니다.\n"
                + "- 바람 부는 날 (Windy): 바람의 영향을 받아 말들이 랜덤(-2~+2)하게 속도가 변합니다.\n\n"
                + "속도 범위:\n"
                + "각 말은 매 턴마다 0에서 10 사이의 랜덤 속도로 달립니다.\n\n"
                + "목표:\n"
                + "각 경주에서 1,2,3위로  결승선에 도착하는 말을 예측하세요!";

        JTextArea textArea = new JTextArea(message);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setFocusable(false);

        // JScrollPane을 사용하여 스크롤 가능하게 설정
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(380, 250)); // 스크롤 패널 크기 설정
        add(scrollPane);

        pack(); // 다이얼로그 크기를 내용에 맞게 조정
    }
}