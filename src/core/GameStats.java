package core;

public class GameStats
{
    private int ticks;

    public GameStats()
    {
        ticks = 0;
    }

    public void incrementTicks()
    {
        ticks++;
    }

    public int getTicks()
    {
        return ticks;
    }

    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ticks:\t");
        stringBuilder.append(ticks);
        return stringBuilder.toString();
    }
}
