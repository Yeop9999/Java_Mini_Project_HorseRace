package src;

public class Horse {
    private String name;
    private int position;

    public Horse(String name, int index) {
        this.name = name;
        this.position = 600; // 초기 위치 (오른쪽 끝)
    }

    public void move(int distance) {
        position -= distance; // 왼쪽으로 이동
    }

    public void reset() {
        position = 600; // 초기 위치로 리셋
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}