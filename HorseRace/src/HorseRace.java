package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HorseRace extends JFrame {
   private Horse[] horses;
   private RaceTrack raceTrackPanel;
   private JButton startButton;
   private JLabel weatherLabel;
   private Timer timer;
   private RaceController raceController;

   public HorseRace() {
       setTitle("Horse Race Game");
       setSize(600, 1000);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(new BorderLayout());

       initializeComponents();
       layoutComponents();

       setVisible(true);
   }

   private void initializeComponents() {
       horses = new Horse[6];
       for (int i = 0; i < horses.length; i++) {
           horses[i] = new Horse("말" + (i + 1), i);
       }

      raceTrackPanel = new RaceTrack(horses);
      raceController = new RaceController(horses);

      startButton = new JButton("Start Race");
      startButton.addActionListener(e -> {
          // 게임 소개 다이얼로그 표시
          GameIntroDialog introDialog = new GameIntroDialog(this);
          introDialog.setVisible(true);

          // 예측 입력 대화 상자 호출
          getBettingPredictions();
      });

      weatherLabel = new JLabel();
      weatherLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
      
      updateWeather();
   }

   private void layoutComponents() {
      add(raceTrackPanel, BorderLayout.CENTER);
      add(startButton, BorderLayout.SOUTH);
      add(weatherLabel, BorderLayout.NORTH);
   }

   private void getBettingPredictions() {
      BettingDialog dialog = new BettingDialog(this, horses);
      dialog.setVisible(true);

      int[] userPredictions = dialog.getPredictions();

      if (userPredictions != null) {
          startRace(userPredictions);
      }
   }

   private void startRace(int[] userPredictions) {
      raceController.resetHorses();

      updateWeather();

      timer = new Timer(100, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              raceController.moveHorses();
              raceTrackPanel.updatePositions(getHorsePositions());

              if (raceController.isRaceFinished()) {
                  timer.stop();
                  showResults(userPredictions);
              }
          }
      });

      timer.start();
   }

   private int[] getHorsePositions() {
      int[] positions = new int[horses.length];

      for (int i = 0; i < horses.length; i++) {
          positions[i] = horses[i].getPosition();
      }

      return positions;
   }

   private void showResults(int[] userPredictions) {
      StringBuilder message = new StringBuilder("경주 결과:\n");

      for (int i = 0; i < Math.min(3, raceController.getFinishedHorses().size()); i++) {
          message.append((i + 1)).append("위: ").append(raceController.getFinishedHorses().get(i).getName()).append("\n");
      }

      message.append("\n당신의 예측:\n");

      for (int i = 0; i < userPredictions.length && userPredictions[i] < horses.length; i++) {
          message.append((i + 1)).append("위 예측: ").append(horses[userPredictions[i]].getName()).append("\n");
      }

      // 맞춘 개수 계산
      int correctPredictions = 0;
      for (int i = 0; i < Math.min(3, raceController.getFinishedHorses().size()); i++) {
          if (raceController.getFinishedHorses().get(i).getName().equals(horses[userPredictions[i]].getName())) {
              correctPredictions++;
          }
      }

      message.append("\n맞춘 개수: ").append(correctPredictions);

      JOptionPane.showMessageDialog(this, message.toString());
   }

   private void updateWeather() { 
      Weather currentWeather = Weather.values()[new Random().nextInt(Weather.values().length)];
      
      String weatherEmoji;

      switch (currentWeather) { 
          case SUNNY: 
              weatherEmoji = "☀️"; 
              break; 
          case RAINY: 
              weatherEmoji = "🌧️"; 
              break; 
          case WINDY: 
              weatherEmoji = "💨"; 
              break; 
          default:
              weatherEmoji ="";
              break;
      } 

     weatherLabel.setText("날씨: " + weatherEmoji + " " + currentWeather);
     raceController.setWeather(currentWeather);
     raceTrackPanel.setWeather(currentWeather); 
   }

   public static void main(String[] args) { 
     SwingUtilities.invokeLater(() -> new HorseRace()); 
   } 
}