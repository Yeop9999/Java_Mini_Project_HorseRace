# Java_Mini_Project_HorseRace

  Java Swing을 이용한 경마 게임

  ## 프로젝트 소개
    Java와 Swing을 활용해 구현한 간단한 경마 게임입니다. 1,2,3위를 예측하여 입력하고 날씨에 따라 속도가 달라지는 말들이 결승을 향해 달립니다.

  ## 개발 기간

  - 24.10.25(월) ~ 24.11.01(금)
---
### 인원

개인프로젝트였습니다

### 개발환경
- 언어 : Java 17
- IDE : Eclipse
- 운영 체제 : Windows
- Java SDK : Oracle JDK 17

---

## 주요 기능

#### 날씨 랜덤 생성 및 속도 변화 
- 랜덤 날씨 생성: 맑음, 비, 바람 세 가지 날씨 상태가 랜덤으로 결정됩니다.
- 속도 조정: 맑음,비,바람 날씨에 따라 경주마의 속도가 달라집니다.
- 
public void moveHorses() {
    Random random = new Random();

    for (Horse horse : horses) {
        if (!finishedHorses.contains(horse)) {
            int move = random.nextInt(10) + 1;  // 기본 이동 거리

            switch (currentWeather) {
                case SUNNY:
                    move += 2;  // 맑은 날씨일 경우 속도 증가
                    break;
                case RAINY:
                    move -= 3;  // 비오는 날씨일 경우 속도 감소
                    break;
                case WINDY:
                    move += random.nextInt(5) - 2; // 바람이 부는 날씨일 경우 -2 ~ +2 범위로 속도 변화
                    break;
            }

            horse.move(move);  // 말의 위치 갱신

            if (horse.getPosition() <= 0) {
                finishedHorses.add(horse);  
            }
        }
    }
}

#### 경주마 이동 및  결승선 도달 체크
- 경주마 이동:move(int distance) 메서드는 경주마의 위치를 600에서부터 왼쪽으로 이동시키는 방식으로 경주마의 이동을 처리합니다.
  
 public class Horse {
    private String name;
    private int position;

    public Horse(String name) {
        this.name = name;
        this.position = 600; // 초기 위치
    }

    public void move(int distance) {
        position -= distance; // 왼쪽으로 이동
    }

    public int getPosition() {
        return position;
    }
}

- 결승선 도달 체크: 경주마가 결승선(50)에 도달하면, 그 경주마는 finishedHorses 목록에 추가됩니다.

    ''' 
                if (horse.getPosition() <= 50 && !finishedHorses.contains(horse)) {
                    finishedHorses.add(horse);
                }
            }
        }
    }

    public boolean isRaceFinished() {
        return finishedHorses.size() >= 3; // 결승선에 도달한 경주마 3명
    }
  
 public List<Horse> getFinishedHorses() {
        return finishedHorses;
    }
}


#### 경주마 예측 순위 입력
- 1,2,3위를 예측하여 입력
  
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

- 중복입력 방지

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


#### 게임 UI
- 예측 입력 및 확인, 취소 버튼
- public BettingDialog(JFrame parent, Horse[] horses) {
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

        // 확인 버튼
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

        // 취소 버튼
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
  
- 
---
## 클래스 UML
![HorseRace_UML(Class)](https://github.com/Yeop9999/Java_Mini_Project_HorseRace/blob/main/HorseRace_UML(class).drawio.png)

---
## 게임 화면

#### 게임 시작 전


#### 게임 플레이 중


#### 게임 종료 및 재시작


#### 





