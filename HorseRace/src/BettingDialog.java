package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BettingDialog extends JDialog {
   private JComboBox<String>[] predictionBoxes;
   private int[] predictions;
   private boolean isCancelled = true;

   public BettingDialog(JFrame parent, Horse[] horses) { 
       super(parent, "경주 예측", true); 
       setSize(200, 300); 
       setLayout(new GridLayout(4, 2));

       predictionBoxes = new JComboBox[3]; 
       String[] horseNames = new String[horses.length]; 

       for (int i = 0; i < horses.length; i++) { 
           horseNames[i] = horses[i].getName(); 
       }

       for (int i = 0; i < 3; i++) { 
           add(new JLabel((i + 1) + "위 예측:")); 
           predictionBoxes[i] = new JComboBox<>(horseNames); 
           add(predictionBoxes[i]); 
       }

       JButton okButton = new JButton("확인"); 
       okButton.addActionListener(new ActionListener() { 
           @Override 
           public void actionPerformed(ActionEvent e) { 
               if (validateSelections()) { 
                   savePredictions(); 
                   isCancelled = false; 
                   dispose(); 
               } else { 
                   JOptionPane.showMessageDialog(BettingDialog.this, "중복된 선택이 있습니다. 다시 선택해주세요."); 
               } 
           } 
       });

       JButton cancelButton = new JButton("취소"); 
       cancelButton.addActionListener(new ActionListener() { 
           @Override 
           public void actionPerformed(ActionEvent e) { 
               dispose(); 
           } 
       });

       add(okButton); 
       add(cancelButton); 
   }

   private boolean validateSelections() {  
       for (int i = 0; i < predictionBoxes.length; i++) {  
           for (int j = i + 1; j < predictionBoxes.length; j++) {  
               if (predictionBoxes[i].getSelectedIndex() == predictionBoxes[j].getSelectedIndex()) {  
                   return false;  
               }  
           }  
       }  
       return true;  
   }

   private void savePredictions() {  
       predictions = new int[3];  
       for (int i = 0; i < 3; i++) {  
           predictions[i] = predictionBoxes[i].getSelectedIndex();  
       }  
   }

   public int[] getPredictions() {  
       return isCancelled ? null : predictions;  
   }  
}