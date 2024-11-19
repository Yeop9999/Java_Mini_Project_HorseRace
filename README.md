# Java_Mini_Project_HorseRace

  Java Swing을 이용한 경마 게임

  ## 프로젝트 소개
    Java와 Swing을 활용해 구현한 간단한 경마 게임입니다. 말들이 날씨 조건에 따라 속도가 달라지며 결승선을 향해 달립니다.

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

#### 랜덤 날씨 생성: 맑음, 비, 바람 등 다양한 날씨 상태가 랜덤으로 결정됩니다.
####    속도 조정: 각 날씨에 따라 경주마의 속도가 달라집니다.

public class Weather {
    private String condition;

    public Weather() {
        this.condition = getRandomWeather();
    }

    private String getRandomWeather() {
        String[] weathers = {"sunny", "rainy", "windy"};
        Random random = new Random();
        return weathers[random.nextInt(weathers.length)];
    }

    public int getSpeedModifier(int baseSpeed) {
        switch (condition) {
            case "sunny":
                return 0; 
            case "rainy":
                return -3; 
            case "windy":
                return (new Random().nextBoolean()) ? -2 : 2;
            default:
                return 0; 
        }
    }

    public String getCondition() {
        return condition;
    }
}






#### 경주마 속성
####     개별 속도: 모든 경주마는 서로 다른 초기 속도를 가지며, 고유한 속성을 반영합니다.
#### 실시간 속도 변화: 날씨와 경주 상황에 따라 각 말의 속도가 실시간으로 변경됩니다.

#### 순위 표시: 경주 종료 후 말의 도착 순위와 시간을 GUI 및 콘솔에 출력합니다.




#### 
  }


#### 


####

#### 게임 UI

---
## 클래스 UML
![HorseRace_UML(Class)](https://github.com/Yeop9999/Java_Mini_Project_HorseRace/blob/main/HorseRace_UML(class).drawio.png)

---
## 게임 화면

#### 게임 시작 전


#### 게임 플레이 중


#### 게임 종료 및 재시작


#### 





