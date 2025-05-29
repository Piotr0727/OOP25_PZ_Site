package music;

public record Song(String artist, String title, int duration) {
    @Override
    public String artist() {
        return artist;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public int duration() {
        return duration;
    }
}
