package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RaceController {
    private Horse[] horses;
    private List<Horse> finishedHorses;
    private Weather currentWeather;

    public RaceController(Horse[] horses) {
        this.horses = horses;
        this.finishedHorses = new ArrayList<>();
    }

    public void resetHorses() {
        for (Horse horse : horses) {
            horse.reset();
        }
        finishedHorses.clear();
    }

    public void moveHorses() {
        Random random = new Random();

        for (Horse horse : horses) {
            if (!finishedHorses.contains(horse)) {
                int move = random.nextInt(10) + 1;
                switch (currentWeather) {
                    case SUNNY:
                        move += 2;
                        break;
                    case RAINY:
                        move -= 2;
                        break;
                    case WINDY:
                        move += random.nextInt(5) - 2;
                        break;
                }
                horse.move(move);

                if (horse.getPosition() <= 0) {
                    finishedHorses.add(horse);
                }
            }
        }
    }

    public boolean isRaceFinished() {
        return finishedHorses.size() >= 3;
    }

    public void setWeather(Weather weather) {
        this.currentWeather = weather;
    }

    public List<Horse> getFinishedHorses() {
        return finishedHorses;
    }
}